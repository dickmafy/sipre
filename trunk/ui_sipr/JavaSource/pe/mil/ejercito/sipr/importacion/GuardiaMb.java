package pe.mil.ejercito.sipr.importacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericResponseBean;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.TmpBonificacionEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpGuardiaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacionPK;
import pe.mil.ejercito.sipr.model.SipreTmpGuardia;
import pe.mil.ejercito.sipr.model.SipreTmpGuardiaPK;


@ManagedBean(name = "guardia")
@ViewScoped
public class GuardiaMb extends MainContext implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TmpGuardiaEjbRemote ejbGuardia;
	
	private String mes;
	private String anio;
	private List<SelectItem> lstAnios;
	private List<SelectItem> lstMeses;
	
	
	public GuardiaMb(){
		super();
		try{
			ejbGuardia = (TmpGuardiaEjbRemote) findServiceRemote(TmpGuardiaEjbRemote.class);
		}catch(Exception e){
			e.printStackTrace();
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
	
	public void changeValue(AjaxBehaviorEvent event) {
		System.out.println("change value");
		System.out.println("valor al año::"+anio);
		System.out.println("valor al mes::"+mes);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
	    anio =(String) event.getComponent().getAttributes().get("anio");
	     mes =(String) event.getComponent().getAttributes().get("mes");
			System.out.println("foo seleccionado::"+anio);
			System.out.println("foo seleccionado::"+mes);
			
				try {
				 List<SipreTmpGuardia> lstbean=new ArrayList<>();
					String fileOldName = event.getFile().getFileName();
					Workbook wb = null;
					String fileExt = null;

					if (FilenameUtils.isExtension(fileOldName, "xls") || FilenameUtils.isExtension(fileOldName, "XLS")) {
						fileExt = "xls";
					}
					if (FilenameUtils.isExtension(fileOldName, "xlsx") || FilenameUtils.isExtension(fileOldName, "XLSX")) {
						fileExt = "xlsx";
					}
					

					File file = transferFile(event);
					FileInputStream fileIS = null;

					fileIS = new FileInputStream(file);
                    String mesProceso=anio+""+mes;
					if (("xlsx").equals(fileExt)) {
						XSSFWorkbook hwb = new XSSFWorkbook(fileIS);
					    lstbean = readExcelNew(hwb, fileIS,mesProceso);
					    if(lstbean!=null && !lstbean.isEmpty()){
					    	for(SipreTmpGuardia bean:lstbean){
					    		ejbGuardia.persist(bean);
					    	}
					    	
					    }
					    
					} else {
						wb = new HSSFWorkbook(fileIS);
						lstbean = readOldExcel(wb, fileIS,mesProceso);
						 if(lstbean!=null && !lstbean.isEmpty()){
						    	for(SipreTmpGuardia bean:lstbean){
						    		ejbGuardia.persist(bean);
						    	}
						    }
					}
					showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,SEVERITY_INFO);
					anio="";
					mes="";

				} catch (FileNotFoundException e) {
					showMessage("Archivo no encontrado.", SEVERITY_ERROR);
				} catch (IOException e) {
					showMessage("No se pudo leer el archivo.", SEVERITY_ERROR);
					e.printStackTrace();
				} catch (NullPointerException e) {
					showMessage(e.toString(), SEVERITY_ERROR);
				} catch (EJBTransactionRolledbackException e) {
					showMessage("Existe información duplicada", SEVERITY_ERROR);
				}catch (Exception e) {
					e.printStackTrace();
					showMessage("No se copio el contenido del Excel correctamente.", SEVERITY_ERROR);
					
				}
			
		
		
	}
	
	

	
	private File transferFile(FileUploadEvent event) {
		String fileOldName = event.getFile().getFileName();
		// UploadedFile filePF = event.getFile();
		File archivo = null;
		String rutaGuardar = null;
		SimpleDateFormat fileId = null;
		String fileNewName = null;

		try {
			rutaGuardar = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

			fileId = new SimpleDateFormat("yyyyMMddHHmmss");
			fileNewName = fileId.format(new Date()) + fileOldName
					// Extension
					+ fileOldName.substring(event.getFile().getFileName().lastIndexOf('.'));

			archivo = new File(rutaGuardar + "/" + fileNewName);
			System.out.println("path :" + rutaGuardar);
			InputStream is = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(archivo);
			byte buf[] = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0)
				out.write(buf, 0, len);
			is.close();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return archivo;

		
	}
	
	
	
	private List<SipreTmpGuardia> readOldExcel(Workbook wb, FileInputStream fileIS,String mesProceso) throws NullPointerException, Exception{
		
		Sheet sheet = wb.getSheetAt(0);
	     Iterator<Row> rowIterator = sheet.iterator();
		 List<SipreTmpGuardia> lstResult=getLstBean(rowIterator,mesProceso);
		System.out.println("lista result::"+lstResult.size());
		
		return lstResult;
	}
	
	private List<SipreTmpGuardia> readExcelNew(XSSFWorkbook wb, FileInputStream fileIS,String mesProceso) throws NullPointerException, Exception{
		
		XSSFSheet sheet = wb.getSheetAt(0);
	    Iterator<Row> rowIterator = sheet.iterator();
	    List<SipreTmpGuardia> lstResult=getLstBean(rowIterator,mesProceso);
		System.out.println("lista result::"+lstResult.size());
		return lstResult;
	}
	
	private List<SipreTmpGuardia> getLstBean(Iterator<Row> rowIterator,String mesProceso)throws NullPointerException, Exception{
		List<SipreTmpGuardia> lstResult=new ArrayList<>();
		int i=0;
        while(rowIterator.hasNext()) {
        	Row row = rowIterator.next();
            if(i>=ConstantesUtil.EXCEL_ROW_INICIO_DTLL){
            	Cell cellAnioMes= row.getCell(ConstantesUtil.EXCEL_COLUMN_ANIO_MES);
    			Cell cellCip= row.getCell(ConstantesUtil.EXCEL_COLUMN_CIP);
    			Cell cellAplld= row.getCell(ConstantesUtil.EXCEL_COLUMN_NOMBRES);
    			Cell cellCncpto= row.getCell(ConstantesUtil.EXCEL_COLUMN_CONCEPTO);
    			Cell cellMonto= row.getCell(ConstantesUtil.EXCEL_COLUMN_MONTO);
    			Cell cellDescuento= row.getCell(ConstantesUtil.EXCEL_COLUMN_DESCUENTO);
    			Cell cellPagar= row.getCell(ConstantesUtil.EXCEL_COLUMN_PAGAR);
    			
    			Cell cellSituacion= row.getCell(ConstantesUtil.EXCEL_COLUMN_SITUACION_GUARDIA);
    			Cell cellMesGuardia= row.getCell(ConstantesUtil.EXCEL_COLUMN_MES_GUARDIA);
    			Cell cellMesReint= row.getCell(ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO_GUARDIA);
    			
    			
    			SipreTmpGuardia bean=new SipreTmpGuardia();
    			SipreTmpGuardiaPK pk=new SipreTmpGuardiaPK();
    			bean.setMesProceso(getValueCell(cellAnioMes,ConstantesUtil.EXCEL_COLUMN_ANIO_MES,row,ConstantesUtil.EXCEL_NAME_ANIO_MES,mesProceso));
    			pk.setCtgMesGuardia(getValueCell(cellMesGuardia,ConstantesUtil.EXCEL_COLUMN_MES_GUARDIA,row,ConstantesUtil.EXCEL_NAME_MES_GUARDIA,mesProceso));
    			pk.setCpersonaNroAdm(getValueCell(cellCip,ConstantesUtil.EXCEL_COLUMN_CIP,row,ConstantesUtil.EXCEL_NAME_CIP,mesProceso));
    			pk.setCciCodigo(getValueCell(cellCncpto,ConstantesUtil.EXCEL_COLUMN_CONCEPTO,row,ConstantesUtil.EXCEL_NAME_CONCEPTO,mesProceso));
    			
    			bean.setSipreTmpGuardiaPK(pk);
    			bean.setVtgApeNom(getValueCell(cellAplld,ConstantesUtil.EXCEL_COLUMN_NOMBRES,row,ConstantesUtil.EXCEL_NAME_NOMBRES,mesProceso));
    			bean.setNtgMtoGestion(new BigDecimal(getValueCell(cellMonto,ConstantesUtil.EXCEL_COLUMN_MONTO,row,ConstantesUtil.EXCEL_NAME_MONTO,mesProceso)));
    			bean.setNtgMtoDescuento(new BigDecimal(getValueCell(cellDescuento,ConstantesUtil.EXCEL_COLUMN_DESCUENTO,row,ConstantesUtil.EXCEL_NAME_DESCUENTO,mesProceso)));
    			bean.setNtgMtoPagado(new BigDecimal(getValueCell(cellPagar,ConstantesUtil.EXCEL_COLUMN_PAGAR,row,ConstantesUtil.EXCEL_NAME_PAGAR,mesProceso)));
    			
    			bean.setCtgIndSituacion(getValueCell(cellSituacion,ConstantesUtil.EXCEL_COLUMN_SITUACION_GUARDIA,row,ConstantesUtil.EXCEL_NAME_SITUACION_GUARDIA,mesProceso));
    			bean.setCtgMesReintegro(getValueCell(cellMesReint,ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO_GUARDIA,row,ConstantesUtil.EXCEL_NAME_MES_REINTEGRO_GUARDIA,mesProceso));
    			
    			lstResult.add(bean);
            }
            i++;
        }
        
		return lstResult;
	}
	
	private String getValueCell(Cell cell,Integer nro,Row row,String nameCell,String mesProceso) throws NullPointerException, Exception{
		
		boolean isread=true;
		String valueReturn=null;
		if(cell.getCellType() ==  Cell.CELL_TYPE_BLANK){
			if(nro== ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO_GUARDIA ){
				isread=false;
			}else {
				isread=false;
				throw new NullPointerException("No se permite valores vacios en la celda "+nameCell+(row.getRowNum()+1));
			
				
			}
		}
		if(nro==ConstantesUtil.EXCEL_COLUMN_ANIO_MES){
			System.out.println("VALORES *****"+cell.getNumericCellValue()+"/"+Double.parseDouble(mesProceso));
			if(cell.getNumericCellValue()==Double.parseDouble(mesProceso)){
				System.out.println("SON IGUALES");
				isread=true;
			}else{
				System.out.println("SON DIFERENTES");
				throw new NullPointerException("El mes y año del proceso de la celda "+nameCell+(row.getRowNum()+1)+ " no coindiden con el seleccionado");
			}
		}
		if(isread){
			switch (nro){
			case ConstantesUtil.EXCEL_COLUMN_ANIO_MES : 
			case ConstantesUtil.EXCEL_COLUMN_SITUACION_GUARDIA : 
			case ConstantesUtil.EXCEL_COLUMN_MES_GUARDIA : 
			case ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO_GUARDIA :{
				System.out.println("value nmero::"+cell.getNumericCellValue());
				Double value=cell.getNumericCellValue();
				valueReturn=value.intValue()+"";
				
				break;
			}
			case ConstantesUtil.EXCEL_COLUMN_MONTO : 
	     	case ConstantesUtil.EXCEL_COLUMN_DESCUENTO :
	     	case ConstantesUtil.EXCEL_COLUMN_PAGAR :{
	     		System.out.println("value: double:"+cell.getNumericCellValue());
				Double value=cell.getNumericCellValue();
				valueReturn= value.toString();
				break;
			 } 
	     	default :{
	     		System.out.println("value string::"+cell.toString());
	     		valueReturn= cell.toString();
	     		break;
	     	}
			}
		}
		
		return valueReturn;
		
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
