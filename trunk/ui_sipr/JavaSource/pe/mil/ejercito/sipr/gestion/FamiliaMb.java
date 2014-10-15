package pe.mil.ejercito.sipr.gestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejbremote.FamiliaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TipoPlanillaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

@ManagedBean(name = "familiaMb")
@ViewScoped
public class FamiliaMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private FamiliaEjbRemote ejb;
	private TipoPlanillaEjbRemote ejbTipoPlanilla;

	private SipreTmpFamilia bean;
	private List<SipreTmpFamilia> beanList;

	private List<SipreTipoPlanilla> tipoPlanillaList;

	public static final String JBOSS_CATALINA = "catalina.home";
	public static final String JBOSS_TEMP = "tmpFiles";
	public FamiliaMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (FamiliaEjbRemote) findServiceRemote(FamiliaEjbRemote.class);
			ejbTipoPlanilla = (TipoPlanillaEjbRemote) findServiceRemote(TipoPlanillaEjbRemote.class);

			tipoPlanillaList = ejbTipoPlanilla.findAll(100);
			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		bean = new SipreTmpFamilia();
	}

	public void saveBean(ActionEvent event) {
		try {
			if (UValidacion.esNuloOVacio(bean.getCtfCif())) {
				bean = ejb.persist(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
			} else {
				bean = ejb.merge(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);

			}
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {

		event.getFile().getContentType();
		String fileOldName = event.getFile().getFileName();
		Workbook wb = null;
		String fileExt = null;

		if (FilenameUtils.isExtension(fileOldName, "xls")
				|| FilenameUtils.isExtension(fileOldName, "XLS")) {
			fileExt = "xls";
		}
		if (FilenameUtils.isExtension(fileOldName, "xlsx")
				|| FilenameUtils.isExtension(fileOldName, "XLSX")) {
			fileExt = "xlsx";
		}
		if (FilenameUtils.isExtension(fileOldName, "txt")
				|| FilenameUtils.isExtension(fileOldName, "TXT")) {
			fileExt = "txt";
		}

		File file = transferFile(event);
		FileInputStream fileIS = null;
		try {
			fileIS = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (("xlsx").equals(fileExt)) {
			//Nuevo  - Excel 2010
			wb = new XSSFWorkbook(fileIS);
			readExcelNew(wb,fileIS);
		} else {
			//Antiguo - Excel 90
			wb = new HSSFWorkbook(fileIS);
			readExcelOld(wb, fileIS);
		}
		System.out.println("Terminado");
	}

	private void readExcelOld(Workbook wb, FileInputStream fileIS)
			throws IOException {
		Sheet sheet = wb.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();
		 while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             //For each row, iterate through all the columns
             Iterator<Cell> cellIterator = row.cellIterator();
              
             while (cellIterator.hasNext()) 
             {
                 Cell cell = cellIterator.next();
                 //Check the cell type and format accordingly
                 switch (cell.getCellType()) 
                 {
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

		
	}

	private void readExcelNew(Workbook wb, FileInputStream fileIS) throws IOException {
		// Create Workbook .xlsx 2007- 2010 files
		//XSSFWorkbook workbook = new XSSFWorkbook(fileIS);
		// Get first/desired sheet from the workbook
		//XSSFSheet sheet = workbook.getSheetAt(0);
		Sheet sheet = wb.getSheetAt(0);
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		 while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             //For each row, iterate through all the columns
             Iterator<Cell> cellIterator = row.cellIterator();
              
             while (cellIterator.hasNext()) 
             {
                 Cell cell = cellIterator.next();
                 //Check the cell type and format accordingly
                 switch (cell.getCellType()) 
                 {
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
	
	}

	/**
	 * Transfiere el archivo subido a la carpeta de Jboss,
	 * standalone>deployments>ear_sipr.ear>ui_sipr.war>
	 * 
	 * @param event
	 * @return
	 */
	private File transferFile(FileUploadEvent event) {
		String fileOldName = event.getFile().getFileName();
		// UploadedFile filePF = event.getFile();
		File archivo = null;
		String rutaGuardar = null;
		SimpleDateFormat fileId = null;
		String fileNewName = null;

		try {
			rutaGuardar = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");

			fileId = new SimpleDateFormat("yyyyMMddHHmmss");
			fileNewName = fileId.format(new Date())
					+ fileOldName
					// Extension
					+ fileOldName.substring(event.getFile().getFileName()
							.lastIndexOf('.'));

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

		// Otroa ruta a Guardar Ruta :
		// M:\work-2014-erickzon\jboss_eap_61\standalone\tmp\tmpFiles
		/*
		 * y comprobar Extension String rootPath =
		 * System.getProperty(JBOSS_CATALINA); File dir = new File(rootPath +
		 * File.separator + JBOSS_TEMP); if (!dir.exists()) dir.mkdirs(); String
		 * filename = file.getName(); if (FilenameUtils.isExtension(filename,
		 * "xls") || FilenameUtils.isExtension(filename, "XLS")) { nombreArchivo
		 * = nombrePais + nombreCompañia + ".xls"; extension = "xls"; }
		 */
	}

	public SipreTmpFamilia getBean() {
		return bean;
	}

	public void setBean(SipreTmpFamilia bean) {
		this.bean = bean;
	}

	public List<SipreTmpFamilia> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreTmpFamilia> beanList) {
		this.beanList = beanList;
	}

	public List<SipreTipoPlanilla> getTipoPlanillaList() {
		return tipoPlanillaList;
	}

	public void setTipoPlanillaList(List<SipreTipoPlanilla> tipoPlanillaList) {
		this.tipoPlanillaList = tipoPlanillaList;
	}

}