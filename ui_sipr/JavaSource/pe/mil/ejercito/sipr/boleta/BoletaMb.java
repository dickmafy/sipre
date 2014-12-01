package pe.mil.ejercito.sipr.boleta;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.primefaces.model.StreamedContent;

import pe.mil.ejercito.sipr.commons.ConexionORCL;
import pe.mil.ejercito.sipr.commons.ConfiguracionDefault;
import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UDate;
import pe.mil.ejercito.sipr.ejbremote.BoletaCabeceraEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.BoletaDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanilla;



@ManagedBean(name = "boletaMb")
@ViewScoped
public class BoletaMb extends MainContext implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	private Date fechaProceso;
	private StreamedContent file;
	private String nameBoleta;
	private PlanillaEjbRemote ejbPlanilla;
	
	private String nroAdm;
	private List<SelectItem> lstNames;
	private boolean seEnvia;//true 
	
	public BoletaMb(){
		try{
			setNameBoleta(ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+"boleta.pdf");
			setSeEnvia(true);
			ejbPlanilla = (PlanillaEjbRemote) findServiceRemote(PlanillaEjbRemote.class);
				
			 if (!ConexionORCL.isInstance()) {
	                new ConexionORCL().setValores(ConfiguracionDefault.IP, ConfiguracionDefault.INSTANCIA, ConfiguracionDefault.PUERTO, ConfiguracionDefault.USUARIO, ConfiguracionDefault.PASSWORD);
	           }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@PostConstruct
	public void loadPersonal(){
		List<SiprePlanilla> lstPlanilla=ejbPlanilla.getListPlanillaByNroAdm();
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
	
	public String generarRpt() throws FileNotFoundException {
		String   print = null;

        try {
            print = imprimir(file);

            if (print != null) {
                 try {
                	System.out.println("name::>"+nameBoleta);
                	System.out.println("PRINT::>"+print);
                
                	//JasperExportManager.exportReportToPdfFile(print, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+nameBoleta);
                	//JasperExportManager.exportReportToPdfFile(print, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+"/"+nameBoleta );
                	//expPdf.exportReport();
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
         setNameBoleta("boleta.pdf");
        
        return "";
    }

    public String   imprimir(StreamedContent file) {
    	String    print = null;
        try{
        	System.out.println("persona cod:"+nroAdm);
        	System.out.println("fecha cod:"+fechaProceso+"::::-::::"+UDate.toStringfecha(fechaProceso, UDate.FORMATO_AA_MM));
        	System.out.println("se envia:"+seEnvia);
        	
        	 String path = ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_BOLETA);
        	 nameBoleta="boleta"+"_"+nroAdm+".pdf";
        	 if (ConexionORCL.conectar()) {
        		 System.out.println("CONECTOOO!!"+ConexionORCL.conectar());
             Map<String,Object> parametro = new HashMap<String, Object>();
             parametro.put("LOGO_EJERCITO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.LOGO));
             parametro.put("ANO_MES_PROCESO",UDate.toStringfecha(fechaProceso, UDate.FORMATO_AA_MM));
           
             parametro.put("NRO_ADM", nroAdm);
             parametro.put("SUB_RPT_DESCUENTO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_DESCUENTO));
             parametro.put("SUB_RPT_PERCIBO", ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_INGRESO));
             
              try{
            	//  JasperReport   jasperReport = JasperCompileManager.compileReport(ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.REPORT_BOLETA_JXML));//REPORT_BOLETA_JXML
            	// print= JasperFillManager.fillReport(jasperReport,parametro,ConexionORCL.getConexion());
            	print= JasperFillManager.fillReportToFile(path,parametro,ConexionORCL.getConexion()); 
            	JasperExportManager.exportReportToPdfFile(print, ConstantesUtil.getRutaFiles(FacesContext.getCurrentInstance(), ConfiguracionDefault.RUTA_REPORT_FILE)+nameBoleta);
             }catch(JRException ex){
            	ex.printStackTrace(); 
             }finally{
             	ConexionORCL.cerrarConexion();
     		}
             
         }
        	 
       }catch(SQLException ex){
        	ex.printStackTrace();
        
        }catch(Exception e){
        	e.printStackTrace();
        }
       

        return print;
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
    
    
}
