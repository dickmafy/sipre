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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.groovy.reflection.stdclasses.BigDecimalCachedClass;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericResponseBean;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.ProgressBar;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejb.TmpBonificacionEjbBean;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacionPK;



@ManagedBean(name = "bonificacionPersonal")
@ViewScoped
public class BonificacionPersonalMb extends MainContext implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UsuarioEjbRemote ejbUsuario;
	private TmpBonificacionEjbBean ejbBonificacion;
	private SipreTmpBonificacion bean;
	private GenericResponseBean<SipreTmpBonificacion> sessionBean ; 
	
	public static final String		JBOSS_CATALINA		= "catalina.home";
	public static final String		JBOSS_TEMP			= "tmpFiles";

	@ManagedProperty("#{progressBar}")
	private ProgressBar				progressBar;
	
	public BonificacionPersonalMb(){
		super();
		try{
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbBonificacion = (TmpBonificacionEjbBean) findServiceRemote(TmpBonificacionEjbBean.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

	
	public void newBean(ActionEvent event) {
		bean = new SipreTmpBonificacion();
		
	}
	
	

	public void saveBean(ActionEvent event) {
		try {
			bean = ejbBonificacion.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		
	}

	public void updateBean(ActionEvent event) {
		try {
			bean = ejbBonificacion.merge(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,SEVERITY_INFO);

		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		
	}
	
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
		List<SipreTmpBonificacion> lstbean=new ArrayList<>();
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

			if (("xlsx").equals(fileExt)) {
				wb = new XSSFWorkbook(fileIS);
			    lstbean = readExcelNew(wb, fileIS, bean);
			} else {
				wb = new HSSFWorkbook(fileIS);
				lstbean = readExcelOld(wb, fileIS, bean);
			}

		} catch (FileNotFoundException e) {
			showMessage("Archivo no encontrado.", SEVERITY_ERROR);
		} catch (IOException e) {
			showMessage("No se pudo leer el archivo.", SEVERITY_ERROR);
			e.printStackTrace();
		} catch (Exception e) {
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
	
	
	
	private List<SipreTmpBonificacion> readOldExcel(Workbook wb, FileInputStream fileIS) throws Exception{
		List<SipreTmpBonificacion> lstResult=new ArrayList<>();
		Sheet sheet = wb.getSheetAt(0);
		/*int fila=ConstantesUtil.EXCEL_ROW_ANIO;
		StringBuilder aniomes=new StringBuilder();
		while(fila<(ConstantesUtil.EXCEL_ROW_MES+1)){
			fila++;
			Row row=sheet.getRow(fila);
			Cell cell= row.getCell(ConstantesUtil.EXCEL_COLUMN_ANIO);
			if(Cell.CELL_TYPE_BLANK == cell.getCellType()){
				try{
					int value= Integer.parseInt(cell.toString()) ;
					aniomes.append(value);
				}catch(NumberFormatException n){
					throw new Exception("Error de formato, para el año y/o mes solo se aceptan valores numéricos");
				}
				
			}else{
				throw new Exception("Error de formato, el año y/o mes no pueden ser valores vacios");
			}
			
		}**/
		
		int totalRow=sheet.getPhysicalNumberOfRows();
		for(int i=ConstantesUtil.EXCEL_ROW_INICIO_DTLL ;i<totalRow;i++){
			Row row=sheet.getRow(i);
			Cell cellAnioMes= row.getCell(ConstantesUtil.EXCEL_COLUMN_ANIO_MES);
			Cell cellCip= row.getCell(ConstantesUtil.EXCEL_COLUMN_CIP);
			Cell cellAplld= row.getCell(ConstantesUtil.EXCEL_COLUMN_NOMBRES);
			Cell cellCncpto= row.getCell(ConstantesUtil.EXCEL_COLUMN_CONCEPTO);
			Cell cellMonto= row.getCell(ConstantesUtil.EXCEL_COLUMN_MONTO);
			Cell cellSituacion= row.getCell(ConstantesUtil.EXCEL_COLUMN_SITUACION);
			Cell cellMesReintegro= row.getCell(ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO);
			Cell cellDeduccion= row.getCell(ConstantesUtil.EXCEL_COLUMN_DEDUCCION);
			
			SipreTmpBonificacion bean=new SipreTmpBonificacion();
			SipreTmpBonificacionPK pk=new SipreTmpBonificacionPK();
			pk.setCtbMesBonificacion(getValueCell(cellAnioMes,ConstantesUtil.EXCEL_COLUMN_ANIO_MES));
			pk.setCpersonaNroAdm(getValueCell(cellCip,ConstantesUtil.EXCEL_COLUMN_CIP));
			pk.setCciCodigo(getValueCell(cellCncpto,ConstantesUtil.EXCEL_COLUMN_CONCEPTO));
			bean.setSipreTmpBonificacionPK(pk);
			bean.setVtbApeNom(getValueCell(cellAplld,ConstantesUtil.EXCEL_COLUMN_NOMBRES));
			bean.setNtbMonto(Double.parseDouble(getValueCell(cellMonto,ConstantesUtil.EXCEL_COLUMN_MONTO)));
			bean.setCtbIndSituacion(getValueCell(cellSituacion,ConstantesUtil.EXCEL_COLUMN_SITUACION));
			
			
		}
		
		
		
		return lstResult;
	}
	
	private String getValueCell(Cell cell,Integer nro) throws NumberFormatException, Exception{
		if(Cell.CELL_TYPE_BLANK == cell.getCellType()){
			switch (nro){
			case ConstantesUtil.EXCEL_COLUMN_ANIO_MES : ;
			case ConstantesUtil.EXCEL_COLUMN_SITUACION : ;
			case ConstantesUtil.EXCEL_COLUMN_MES_REINTEGRO :{
				Integer value=Integer.parseInt(cell.toString());
				return value.toString();
				
			}
			case ConstantesUtil.EXCEL_COLUMN_MONTO : ;
	     	case ConstantesUtil.EXCEL_COLUMN_DEDUCCION :{
				Double value=Double.parseDouble(cell.toString());
				return value.toString();
			 } 
	     	default :{
	     		return cell.toString();
	     	}
			}
			
			
			
		}else {
			throw new Exception("Los valores de las celdas no deben estar vacias");
		}
		
	}	
	
	private List<SipreTmpBonificacion> readExcelOld(Workbook wb, FileInputStream fileIS, SipreTmpBonificacion bean) {
		try {
			Sheet sheet = wb.getSheetAt(0);
			Cell cell;
			int contadorFilas = 1;
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			row = rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				CellReference ref = new CellReference(0, 0);
				cell = row.getCell(ref.getCol());
				cell.getColumnIndex();
				sheet.getLastRowNum();
				long contadorFilasTotal = (long) sheet.getPhysicalNumberOfRows();
				// no cuenta la cabecera
				if (contadorFilas < contadorFilasTotal) {
					contadorFilas++;

					// CIF
					cell = row.getCell(0, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfCif(getValorCeldaExcel(cell));

					// CIP
					cell = row.getCell(1, Row.RETURN_NULL_AND_BLANK);
					//bean.setCpersonaNroAdm(getValorCeldaExcel(cell));

					// NOMBRES
					cell = row.getCell(2, Row.RETURN_NULL_AND_BLANK);
					//bean.setVtfApeNom(getValorCeldaExcel(cell));

					// Fecha Nacimiento
					cell = row.getCell(3, Row.RETURN_NULL_AND_BLANK);
					//bean.setDtfFecNac(UValidacion.ConvertStringToDate2(getValorCeldaExcel(cell)));

					// Fecha Renovacion
					cell = row.getCell(4, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfFecRenovac(UValidacion.ConvertStringToDate2(getValorCeldaExcel(cell)));

					// SEXO
					cell = row.getCell(5, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfSexo(getValorCeldaExcel(cell));

					// CTF_CAUSAL_RENOVAC
					cell = row.getCell(6, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfCauRenovac(getValorCeldaExcel(cell));

					// CTF_SITUACION_FAMILIA
					cell = row.getCell(7, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfSitFamilia(getValorCeldaExcel(cell));

					// CTF_DNI
					cell = row.getCell(8, Row.RETURN_NULL_AND_BLANK);
					//bean.setCtfDni(getValorCeldaExcel(cell));

					// Row.RETURN_NULL_AND_BLANK
					ejbBonificacion.merge(bean);
					//progressBar.barraProgreso(contadorFilas, (int) contadorFilasTotal);
				}// if

			}// while
			showMessage(contadorFilas + " filas  leida correctamente.", SEVERITY_INFO);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_FAMILIA, SEVERITY_ERROR);
		}
		return new ArrayList<>();

	}

	public String getValorCeldaExcel(Cell cell) {
		String valorCelda = "";
		try {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					valorCelda = UValidacion.ConvertDateToString(date);
				} else {
					Long i = (long) cell.getNumericCellValue();
					valorCelda = String.valueOf((i));
				}
				break;
			case Cell.CELL_TYPE_STRING:
				System.out.print("String : " + cell.getStringCellValue() + "\t");
				valorCelda = cell.getStringCellValue();
				break;
			}
			System.out.println("");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return valorCelda.trim();

	}

	private List<SipreTmpBonificacion> readExcelNew(Workbook wb, FileInputStream fileIS, SipreTmpBonificacion bean) throws IOException {
		
		Sheet sheet = wb.getSheetAt(0);
	
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					break;
				}
			}
			System.out.println("");
		}
		return new ArrayList<>();

	}


	public SipreTmpBonificacion getBean() {
		return bean;
	}

	public void setBean(SipreTmpBonificacion bean) {
		this.bean = bean;
	}
	
	
	

}
