package pe.mil.ejercito.sipr.entidadCrediticia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import pe.mil.ejercito.sipr.commons.ConfiguracionDefault;
import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.commons.convert.DBFWriter;
import pe.mil.ejercito.sipr.commons.convert.JDBField;
import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpJudicialEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SipreTmpJudicial;

@ManagedBean(name = "crediticiaConversionMb")
@ViewScoped
public class CrediticiaConversionMb extends MainContext implements Serializable {
	private static Logger					LOG					= Logger.getLogger(CrediticiaConversionMb.class);
	private static final long				serialVersionUID	= 1L;
	private UsuarioEjbRemote				ejbUsuario;
	private Date							anioMes;

	private TmpJudicialEjbRemote			ejbJudicial;
	private TmpEntidadCrediticiaEjbRemote	ejbEntidad;
	private String							tipoArchivo;

	public static final String				JBOSS_CATALINA		= "catalina.home";
	public static final String				JBOSS_TEMP			= "tmpFiles";

	private String							mes;
	private String							anio;
	private List<SelectItem>				lstAnios;
	private List<SelectItem>				lstMeses;
	private File							file;

	private List<SipreTmpJudicial>			beanJList;
	private List<SipreTmpEntidadCrediticia>	beanECList;

	public CrediticiaConversionMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbJudicial = (TmpJudicialEjbRemote) findServiceRemote(TmpJudicialEjbRemote.class);
			ejbEntidad = (TmpEntidadCrediticiaEjbRemote) findServiceRemote(TmpEntidadCrediticiaEjbRemote.class);
			tipoArchivo = ConstantesUtil.TIPO_FILE_ENTIDAD + "";
			cleanBeanGmList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanBeanGmList() {
		updateComponente("dt");
		updateComponente("dt2");
	}

	@PostConstruct
	public void loadAnioMes() {
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre",
				"Diciembre" };

		lstAnios = new ArrayList<SelectItem>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		for (int i = 0; i < 10; i++) {
			SelectItem si = new SelectItem();
			si.setLabel((year + i) + "");
			si.setValue((year + i));
			lstAnios.add(si);
		}

		lstMeses = new ArrayList<SelectItem>();
		for (int i = 0; i < meses.length; i++) {
			SelectItem si = new SelectItem();
			si.setLabel(meses[i]);
			si.setValue(i + 1);
			lstMeses.add(si);
		}
		anio = year + "";
		System.out.println("mes preselccionado::" + month);
		mes = (month + 1) + "";

	}

	public void changeValue(AjaxBehaviorEvent event) {
		System.out.println("change value");
		System.out.println("valor al a�o::" + anio);
		System.out.println("valor al mes::" + mes);
	}

	public void changeValueRadio(AjaxBehaviorEvent event) {
		System.out.println("change value radio");
		System.out.println("valor al tipo archivo::" + tipoArchivo);

	}

	public void seteandoTipo() {
		System.out.println("tipoArchivo::" + tipoArchivo);
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {

			showMessage("Procesando el archivo subido.", SEVERITY_INFO);

			tipoArchivo = (String) event.getComponent().getAttributes().get("typeFile");
			anio = (String) event.getComponent().getAttributes().get("year");
			mes = (String) event.getComponent().getAttributes().get("month");
			System.out.println("foo seleccionado::" + anio);
			System.out.println("foo seleccionado::" + mes);

			file = transferFile(event);
			InputStream is = event.getFile().getInputstream();
			//CREDITICIA CONVERSION ARCHIVO
			ejbJudicial.removeAll();
			generarDeTxtDBF(is, file);
			beanJList = ejbJudicial.findAll();
			updateComponente("dt");
			updateComponente("dt2");

		} catch (FileNotFoundException e) {
			showMessage("Archivo no encontrado.", SEVERITY_ERROR);
		} catch (IOException e) {
			showMessage("No se pudo leer el archivo.", SEVERITY_ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			showMessage("No se copio el contenido del Excel correctamente.", SEVERITY_ERROR);
		}

	}

	private void generarDeTxtDBF(InputStream is, File fileTxt) {
		BufferedReader br;
		DBFWriter dbfwriter;
		int cTotal = 0;
		int cExitoso = 0;
		try {
			Integer LONGITUD_OBLIGATORIA = 55;
			int numeroColumnas = 8;
			int numeroFilas = 100;
			//C STRING; ; L BOOLEAN , D DATE, N NUMBER  , F FLOAT
			JDBField[] fields = { new JDBField("1", 'C', 100, 0), new JDBField("2", 'C', 100, 0), new JDBField("3", 'C', 100, 0),
					new JDBField("4", 'C', 100, 0), new JDBField("5", 'C', 100, 0), new JDBField("6", 'C', 100, 0),
					new JDBField("7", 'C', 100, 0), new JDBField("8", 'C', 100, 0),
			//new JDBField("TestF", 'F', 20, 6),new JDBField("TestD", 'D', 8, 0)
			};

			//DBFReader dbfreader = new DBFReader("E:\\hexiong\\work\\project\\book2.dbf");
			//String nombreDBF = "N:\\test_text_to_dbf.dbf";
			//test MIERCOLES 12
			String nombreDBF = "C:\\1_CONVERTIDO_TXT_A_DBF_" + UValidacion.getDateNameForFileSavedYMD_HMS() + ".dbf";

			dbfwriter = new DBFWriter(nombreDBF, fields);
			Object[][] records = new Object[numeroFilas][numeroColumnas];

			br = new BufferedReader(new FileReader(fileTxt));

			String texto;
			while ((texto = br.readLine()) != null) {
				cTotal++;
				if (texto.trim().length() > 0 && texto.length() == LONGITUD_OBLIGATORIA) {

					// SUBSTRING : texto >= X && >=Y
					LOG.info("###INICIO - FILA : " + cTotal);

					Object v1 = texto.substring(0, 1).trim();
					LOG.info("1:      " + v1);

					Object v2 = texto.substring(1, 2).trim();
					LOG.info("2:              " + v2);

					Object v3 = texto.substring(2, 9).trim();
					LOG.info("3:      " + v3);

					Object v4 = texto.substring(9, 12).trim();
					LOG.info("4:      " + v4);

					Object v5 = texto.substring(12, 14).trim();
					LOG.info("5:       " + v5);

					Object v6 = texto.substring(14, 33).trim();
					LOG.info("6: " + v6);

					Object v7 = texto.substring(33, 34).trim();
					LOG.info("7: " + v7);

					Object v8 = texto.substring(34, 55).trim();
					LOG.info("8: " + v8);

					LOG.info("################");

					records[cTotal - 1][0] = v1;
					records[cTotal - 1][1] = v2;
					records[cTotal - 1][2] = v3;
					records[cTotal - 1][3] = v4;
					records[cTotal - 1][4] = v5;
					records[cTotal - 1][5] = v6;
					records[cTotal - 1][6] = v6;
					records[cTotal - 1][7] = v6;
					cExitoso++;
				} else {
					cTotal--;
					System.out.println("El archivo no cumple la longitud permitida. El Archivo tiene: " + texto.length());
				}
			}

			for (Object[] record : records) {
				dbfwriter.addRecord(record);
			}

			dbfwriter.close();
			br.close();
			System.out.println("DBF Creado y Guardado.");

		} catch (Exception e) {
			e.printStackTrace();
		}



		showMessage("Convertidos " + cExitoso + " de " + cTotal + " para el Archivo DBF", SEVERITY_INFO);
	}

	private File transferFile(FileUploadEvent event) {
		String fileOldName = event.getFile().getFileName();

		File archivo = null;
		String rutaGuardar = null;
		SimpleDateFormat fileId = null;
		String fileNewName = null;

		try {
			rutaGuardar = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

			fileId = new SimpleDateFormat("yyyyMMddHHmmss");
			fileNewName = fileId.format(new Date()) + fileOldName + fileOldName.substring(event.getFile().getFileName().lastIndexOf('.'));

			archivo = new File(rutaGuardar + "/" + ConfiguracionDefault.RUTA_FILE_SYSTEM + fileNewName);
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

	public UsuarioEjbRemote getEjbUsuario() {
		return ejbUsuario;
	}

	public void setEjbUsuario(UsuarioEjbRemote ejbUsuario) {
		this.ejbUsuario = ejbUsuario;
	}

	public Date getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(Date anioMes) {
		this.anioMes = anioMes;
	}

	public TmpJudicialEjbRemote getEjbJudicial() {
		return ejbJudicial;
	}

	public void setEjbJudicial(TmpJudicialEjbRemote ejbJudicial) {
		this.ejbJudicial = ejbJudicial;
	}

	public TmpEntidadCrediticiaEjbRemote getEjbEntidad() {
		return ejbEntidad;
	}

	public void setEjbEntidad(TmpEntidadCrediticiaEjbRemote ejbEntidad) {
		this.ejbEntidad = ejbEntidad;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<SipreTmpJudicial> getBeanJList() {
		return beanJList;
	}

	public void setBeanJList(List<SipreTmpJudicial> beanJList) {
		this.beanJList = beanJList;
	}

	public List<SipreTmpEntidadCrediticia> getBeanECList() {
		return beanECList;
	}

	public void setBeanECList(List<SipreTmpEntidadCrediticia> beanECList) {
		this.beanECList = beanECList;
	}

}