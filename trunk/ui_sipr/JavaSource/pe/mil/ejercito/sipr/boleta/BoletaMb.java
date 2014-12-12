package pe.mil.ejercito.sipr.boleta;



import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import pe.mil.ejercito.sipr.commons.ConexionORCL;
import pe.mil.ejercito.sipr.commons.ConfiguracionDefault;
import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.Correo;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UDate;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejbremote.PlanillaDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBoletaCabecera;
import pe.mil.ejercito.sipr.model.SipreBoletaCabeceraPK;
import pe.mil.ejercito.sipr.model.SipreBoletaDetalle;
import pe.mil.ejercito.sipr.model.SipreBoletaDetallePK;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaDescuento;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SipreUsuario;



@ManagedBean(name = "boletaMb")
@ViewScoped
public class BoletaMb extends MainContext implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	private Date fechaProceso;
	private StreamedContent file;
	private String nameBoleta;
	private PlanillaEjbRemote ejbPlanilla;
	private PlanillaDescuentoEjbRemote ejbPlanillaDescuento;
	private PlanillaDetalleEjbRemote ejbPlanillaDetalle;
   
	
	private String nroAdm;
	private List<SelectItem> lstNames;
	private boolean seEnvia;//true 
	
	private String mes;
	private String anio;
	private List<SelectItem> lstAnios;
	private List<SelectItem> lstMeses;
	
	 
	
	public BoletaMb(){
		try{
			setNameBoleta(ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+"boleta.pdf");
			setSeEnvia(true);
			ejbPlanilla = (PlanillaEjbRemote) findServiceRemote(PlanillaEjbRemote.class);
				
			 if (!ConexionORCL.isInstance()) {
	                new ConexionORCL().setValores(ConfiguracionDefault.IP, ConfiguracionDefault.INSTANCIA, ConfiguracionDefault.PUERTO, ConfiguracionDefault.USUARIO, ConfiguracionDefault.PASSWORD);
	           }
			 loadPersonal();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void loadPersonal(){
		List<SiprePlanilla> lstPlanilla=ejbPlanilla.getListPlanillaByNroAdm(null);
		lstNames=new ArrayList<SelectItem>();
		if(lstPlanilla!=null & !lstPlanilla.isEmpty()){
			for(SiprePlanilla pl:lstPlanilla){
				SelectItem si=new SelectItem();
				si.setLabel(pl.getVplanillaApeNom());
				si.setValue(pl.getId().getCpersonaNroAdm());
				lstNames.add(si);
			}
		}
		
	}
	
	@PostConstruct
	public void loadAnioMes(){
		String[] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
		
		lstAnios=new ArrayList<SelectItem>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		for(int i=0;i<10;i++){
			SelectItem si=new SelectItem();
			si.setLabel((year+i)+"");
			si.setValue((year+i));
			lstAnios.add(si);
		}
		
		lstMeses=new ArrayList<SelectItem>();
		for(int i=0;i<meses.length;i++){
			SelectItem si=new SelectItem();
			si.setLabel(meses[i]);
			si.setValue(i+1);
			lstMeses.add(si);
		}
		anio=year+"";
		System.out.println("mes preselccionado::"+month);
		mes=(month+1)+"";
		
		
		
	}
	
	public void procesarBoleta(){
		List<SiprePlanilla> lstPlanilla=ejbPlanilla.getListPlanillaByNroAdm(anio+""+mes);
		List<SiprePlanillaDetalle> lstIngresos=ejbPlanillaDetalle.getListPlanillaDetalle(anio+""+mes);
		List<SiprePlanillaDescuento> lstDescLeyJud=ejbPlanillaDescuento.getListPlanillaDescuento(anio+""+mes, ConstantesUtil.TIPO_LEY_JUDICIAL);
		List<SiprePlanillaDescuento> lstDescOtros=ejbPlanillaDescuento.getListPlanillaDescuento(anio+""+mes, ConstantesUtil.TIPO_GENERAL);
		List<SipreBoletaDetalle> lstBoletaDet=null;
		List<SipreBoletaCabecera> lstBoletaCab=null;
		if(lstPlanilla!=null && !lstPlanilla.isEmpty() ){
			for(SiprePlanilla plnPers:lstPlanilla){
				BigDecimal totalIng=new BigDecimal(0);
				BigDecimal totalDesLeyJud=new BigDecimal(0);
				BigDecimal neto=new BigDecimal(0);
				for(int i=0 ;i<lstIngresos.size();i++){
					if(lstIngresos.get(i).getSiprePlanilla().getId().getCpersonaNroAdm().equalsIgnoreCase(plnPers.getId().getCpersonaNroAdm())){
						totalIng.add(lstIngresos.get(i).getNpdMtoConcepto());
					}
				}
                for(int i=0 ;i<lstDescLeyJud.size();i++){
                	if(lstDescLeyJud.get(i).getSiprePlanilla().getId().getCpersonaNroAdm().equalsIgnoreCase(plnPers.getId().getCpersonaNroAdm())){
                		totalDesLeyJud.add(lstDescLeyJud.get(i).getNpdMtoEmpleado());
					}
				}
                neto=totalIng.subtract(totalDesLeyJud);
                
				SipreBoletaCabecera cb=new SipreBoletaCabecera();
				SipreBoletaCabeceraPK pkCb=new SipreBoletaCabeceraPK();
				pkCb.setCbcMesProceso(anio+""+mes);
				pkCb.setCbcNroAdm(plnPers.getId().getCpersonaNroAdm());
				pkCb.setNbcNumProceso(plnPers.getId().getNplanillaNumProceso());
				cb.setSipreBoletaCabeceraPK(pkCb);
				
				cb.setCbcCodGraEfec(plnPers.getSipreCargo().getCcargoCodigo());
				//cb.setCbcCodGraPens(plnPers.get);
				cb.setCbcCodUnidad(plnPers.getSipreUnidad().getCunidadCodigo());
				cb.setCbcDni(plnPers.getCplanillaDni());
				//cb.setCbcIndActPens(cbcIndActPens);
				cb.setDbcFecReg(new Date());
				cb.setNbcMtoEgreso(totalDesLeyJud);
				cb.setNbcMtoIngreso(neto);
				//cb.setNbcNumBoleta(nbcNumBoleta);
				//cb.setSipreBoletaDetalleList(sipreBoletaDetalleList);
				cb.setSipreUsuario(new SipreUsuario());
				//cb.setTcbTipPersona(tcbTipPersona);
				cb.setVbcDesBanco(plnPers.getSipreBanco().getVbancoDsc());
				//cb.setVbcDesGraEfec(vbcDesGraEfec);
				//cb.setVbcDesGraPens(vbcDesGraPens);
				cb.setVbcDesUnidad(plnPers.getSipreUnidad().getVunidadDscCorta());
				//cb.setVbcLugar(plnPers.);
				//cb.setVbcRegPens(vbcRegPens);
				//cb.setVbcRegRemun(vbcRegRemun);
				List<SipreBoletaDetalle> lstDetalle=new ArrayList<SipreBoletaDetalle>();
				 for(int i=0 ;i<lstDescOtros.size();i++){
					 SipreBoletaDetalle dtl=new SipreBoletaDetalle();
	                	if(lstDescOtros.get(i).getSiprePlanilla().getId().getCpersonaNroAdm().equalsIgnoreCase(plnPers.getId().getCpersonaNroAdm())){
	                		if(neto.compareTo(lstDescOtros.get(i).getNpdMtoEmpleado())== 1){
	                		   neto.subtract(lstDescOtros.get(i).getNpdMtoEmpleado());
	                		   SipreBoletaDetallePK pk =new SipreBoletaDetallePK();
	                		   pk.setCbcMesProceso(anio+""+mes);
	                		   pk.setCbcNroAdm(plnPers.getId().getCpersonaNroAdm());
	                		   pk.setNbcNumProceso(plnPers.getId().getNplanillaNumProceso());
	                		   pk.setCbdTipPlanilla(lstDescOtros.get(i).getSipreTipoPlanilla().getCtpCodigo());
	                		   dtl.setSipreBoletaDetallePK(pk);
	                		 /*  dtl.setCbdCodIngDesc(lstDescOtros.get(i).get);
	                		   dtl.setCbdCodMef(cbdCodMef);
	                		   dtl.setCbdIndSubtitulo(cbdIndSubtitulo);
	                		   dtl.setCbdTipConcpto(cbdTipConcpto);
	                		   dtl.setNbdMonto(nbdMonto);
	                		   dtl.setNbdNumCuoPagada(nbdNumCuoPagada);
	                		   dtl.setNbdNumCuoTotal(nbdNumCuoTotal);
	                		   dtl.setVbdDscIngDesc(vbdDscIngDesc);
	                		   dtl.setSipreBoletaCabecera(cb);*/
	                		   lstDetalle.add(dtl);
	                		}
						}
					}
				
			}
		}
		
		
		System.out.println("Procesando ....");
	}
	
	public String generarRpt() throws FileNotFoundException {
		 try {
        	System.out.println("persona cod:"+nroAdm);
        	System.out.println("fecha cod:"+fechaProceso+"::::-::::"+UDate.toStringfecha(fechaProceso, UDate.FORMATO_AA_MM));
        	System.out.println("se envia:"+seEnvia);
        	
        	 String path = ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_BOLETA);
        	 nameBoleta="boleta"+"_"+nroAdm+".pdf";
        	 if (ConexionORCL.conectar()) {
        		
             Map<String,Object> parametro = new HashMap<String, Object>();
             parametro.put("ANO_MES_PROCESO",(UDate.toStringfecha(fechaProceso, UDate.FORMATO_AA_MM)).toString().trim());
             parametro.put("LOGO_EJERCITO",ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.LOGO));
             parametro.put("NRO_ADM",nroAdm.toString().trim());
             parametro.put("SUB_RPT_DESCUENTO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_DESCUENTO));
             parametro.put("SUB_RPT_PERCIBO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_INGRESO));
             parametro.put("SUB_RPT_APORTE", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_APORTE));
             
              try{
            	  
            	 String print= JasperFillManager.fillReportToFile(path,parametro,ConexionORCL.getConexion()); 
            	JasperExportManager.exportReportToPdfFile(print, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+nameBoleta);
            	setNameBoleta(nameBoleta);
            	if(seEnvia){
            	   String cuerpoMensaje=ConfiguracionDefault.CUERPO_MENSAJE;
                   ArrayList<String[]> lista = new ArrayList<>();
                   Correo.enviarCorreo( lista,ConfiguracionDefault.TO_EMAIL , ConfiguracionDefault.TITULO_MENSAJE, cuerpoMensaje, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+nameBoleta,nameBoleta); 
               }
            	
            	RequestContext.getCurrentInstance().execute("PF('wvviewpdf').show()");
                RequestContext.getCurrentInstance().update("frm:dlgviewpdf");
              }catch(JRException ex){
            	ex.printStackTrace(); 
             }finally{
             	ConexionORCL.cerrarConexion();
     		}
             
         }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return "";
    }

   

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getNameBoleta() {
		return nameBoleta;
	}

	public void setNameBoleta(String nameBoleta) {
		this.nameBoleta = nameBoleta;
	}

	public String getNroAdm() {
		return nroAdm;
	}

	public void setNroAdm(String nroAdm) {
		this.nroAdm = nroAdm;
	}

	public List<SelectItem> getLstNames() {
		return lstNames;
	}

	public void setLstNames(List<SelectItem> lstNames) {
		this.lstNames = lstNames;
	}

	public boolean isSeEnvia() {
		return seEnvia;
	}

	public void setSeEnvia(boolean seEnvia) {
		this.seEnvia = seEnvia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public List<SelectItem> getLstAnios() {
		return lstAnios;
	}

	public void setLstAnios(List<SelectItem> lstAnios) {
		this.lstAnios = lstAnios;
	}

	public List<SelectItem> getLstMeses() {
		return lstMeses;
	}

	public void setLstMeses(List<SelectItem> lstMeses) {
		this.lstMeses = lstMeses;
	}
    
    
}
