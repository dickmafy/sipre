package pe.mil.ejercito.sipr.importacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pe.mil.ejercito.sipr.commons.ConfiguracionDefault;
import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.convert.DBFWriter;
import pe.mil.ejercito.sipr.commons.convert.JDBField;
import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpJudicialEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticiaPK;
import pe.mil.ejercito.sipr.model.SipreTmpJudicial;
import pe.mil.ejercito.sipr.model.SipreTmpJudicialPK;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

@ManagedBean(name = "judicialCrediticiaMb")
@ViewScoped
public class JudicialCrediticiaMb extends MainContext implements Serializable {
	private static Logger LOG = Logger.getLogger(JudicialCrediticiaMb.class);
	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejbUsuario;
	private Date anioMes;

	private TmpJudicialEjbRemote ejbJudicial;
	private TmpEntidadCrediticiaEjbRemote ejbEntidad;
	private String tipoArchivo;
	private UploadedFile fileUploaded;

	public static final String JBOSS_CATALINA = "catalina.home";
	public static final String JBOSS_TEMP = "tmpFiles";

	private String mes;
	private String anio;
	private List<SelectItem> lstAnios;
	private List<SelectItem> lstMeses;
	private File file;

	private List<SipreTmpJudicial> beanJList;
	private List<SipreTmpEntidadCrediticia> beanECList;

	public JudicialCrediticiaMb() {
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
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Setiembre", "Octubre",
				"Noviembre", "Diciembre" };

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

	public String handleFileUpload(FileUploadEvent event) {
		try {

			showMessage("Procesando el archivo subido.", SEVERITY_INFO);

			tipoArchivo = fileUploaded.getContentType();
			// anio = (String) event.getComponent().getAttributes().get("year");
			// mes = (String) event.getComponent().getAttributes().get("month");
			System.out.println("foo seleccionado::" + anio);
			System.out.println("foo seleccionado::" + mes);

			file = transferFile(fileUploaded.getFileName(),
					fileUploaded.getInputstream());
			// InputStream is = event.getFile().getInputstream();
			InputStream is = fileUploaded.getInputstream();
			switch (tipoArchivo) {
			case "2":
				// CREDITICIA
				ejbEntidad.removeAll();
				readDbf(is, tipoArchivo != null ? Integer.parseInt(tipoArchivo)
						: null);
				beanECList = ejbEntidad.findAll();
				updateComponente("dt");
				updateComponente("dt2");
				break;
			case "1":
				// JUDICIAL
				ejbJudicial.removeAll();
				generarDeTxtDBF(is, file);
				beanJList = ejbJudicial.findAll();
				updateComponente("dt");
				updateComponente("dt2");
				break;
			}

		} catch (FileNotFoundException e) {
			showMessage("Archivo no encontrado.", SEVERITY_ERROR);
		} catch (IOException e) {
			showMessage("No se pudo leer el archivo.", SEVERITY_ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			showMessage("No se copio el contenido del Excel correctamente.",
					SEVERITY_ERROR);
		}
		return "";

	}

	private void readDbf(InputStream is, Integer tipo) throws IOException {
		DBFReader reader = new DBFReader(is);
		int numberOfFields = reader.getFieldCount();
		for (int i = 0; i < numberOfFields; i++) {
			DBFField field = reader.getField(i);
			System.out.println(field.getName());
		}

		System.out.println("tipo archivo::>" + tipoArchivo);

		Object[] rowObjects;
		while ((rowObjects = reader.nextRecord()) != null) {
			if (tipo == ConstantesUtil.TIPO_FILE_ENTIDAD) {
				SipreTmpEntidadCrediticia ent = new SipreTmpEntidadCrediticia();
				SipreTmpEntidadCrediticiaPK pk = new SipreTmpEntidadCrediticiaPK();
				int numbColumn = 0;
				for (int i = 0; i < rowObjects.length; i++) {
					try {
						switch (i) {
						// case 0:{numbColumn++;} break;
						case 1: {
							pk.setCtecTipoMovim(String.valueOf(rowObjects[i]));
							numbColumn++;
						}
							break;
						case 2: {
							pk.setCpersonaNroAdm(String.valueOf(rowObjects[i]));
							numbColumn++;
						}
							break;
						case 3: {
							pk.setCecCodigo(String.valueOf(rowObjects[i]));
							numbColumn++;
						}
							break;
						case 4: {
							ent.setNtecMonto(new BigDecimal(rowObjects[i]
									.toString()));
							numbColumn++;
						}
							break;
						case 5: {
							ent.setNtecMtoAnterior(new BigDecimal(rowObjects[i]
									.toString()));
							numbColumn++;
						}
							break;
						case 6: {
							ent.setNtecNroCuota((new BigDecimal(rowObjects[i]
									.toString())).intValue());
							numbColumn++;
						}
							break;
						// case
						// 6:{ent.setNtecNroCuota(Integer.parseInt(rowObjects[i].toString()));numbColumn++;}break;
						// case 7:{numbColumn++;} break;
						// case 8:{numbColumn++;} break;
						case 9: {
							pk.setCtecMesProceso(String.valueOf(rowObjects[i]));
							numbColumn++;
						}
							break;
						}

						if ((i + 1) == numberOfFields) {
							System.out.println("guardando....");
							numbColumn = 0;
							ent.setSipreTmpEntidadCrediticiaPK(pk);

							ejbEntidad.persist(ent);
							System.out.println("saliendo de persisnt....");

							ent = new SipreTmpEntidadCrediticia();
							pk = new SipreTmpEntidadCrediticiaPK();

						}
						System.out.println(rowObjects[i] + "==>nro columna:"
								+ i + "-" + numbColumn);
					} catch (EJBTransactionRolledbackException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		}
		is.close();
	}

	@SuppressWarnings({ "unused", "resource" })
	private void generarDeTxtDBF(InputStream is, File fileTxt) {
		try {
			Integer LONGITUD_OBLIGATORIA = 40;
			String MES_PROCESO = "";
			String CIP = "";
			String CTP_CODIGO = "";
			String CEC_CODIGO = "";
			String NTJ_MONTO = "";
			String NTJ_PORCENJAJE = "";

			int numeroColumnas = 6;
			int numeroFilas = 100;
			// C STRING; ; L BOOLEAN , D DATE, N NUMBER , F FLOAT
			JDBField[] fields = { new JDBField("MES_PROCESO", 'C', 100, 0),
					new JDBField("CIP", 'C', 100, 0),
					new JDBField("TIPO_PLAN", 'C', 100, 0),
					new JDBField("ENT_CRE", 'C', 100, 0),
					new JDBField("MONTO", 'C', 13, 0),
					new JDBField("PORCENJAJE", 'C', 6, 0),
			// new JDBField("TestF", 'F', 20, 6),new JDBField("TestD", 'D', 8,
			// 0)
			};

			// DBFReader dbfreader = new
			// DBFReader("E:\\hexiong\\work\\project\\book2.dbf");
			// String nombreDBF = "N:\\test_text_to_dbf.dbf";
			String nombreDBF = "C:\\DBF_ARCHIVO_JUDICIAL.dbf";
			// String nombreText = "test.txt";

			DBFWriter dbfwriter = new DBFWriter(nombreDBF, fields);
			Object[][] records = new Object[numeroFilas][numeroColumnas];
			int contador = 0;
			BufferedReader br = new BufferedReader(new FileReader(fileTxt));
			String texto;
			while ((texto = br.readLine()) != null) {
				contador++;
				if (texto.trim().length() > 0
						&& texto.length() == LONGITUD_OBLIGATORIA) {
					// SUBSTRING : texto >= X && >=Y
					LOG.info("###INICIO - FILA : " + contador);

					MES_PROCESO = texto.substring(0, 6);
					LOG.info("MES_PROCESO:      " + MES_PROCESO);

					CIP = texto.substring(6, 15);
					LOG.info("CIP:              " + CIP);

					CTP_CODIGO = texto.substring(15, 17);
					LOG.info("CTP_CODIGO:      " + CTP_CODIGO);

					CEC_CODIGO = texto.substring(17, 21);
					LOG.info("CEC_CODIGO:      " + CEC_CODIGO);

					NTJ_MONTO = texto.substring(21, 34).trim();
					LOG.info("NTJ_MONTO:       " + NTJ_MONTO);

					NTJ_PORCENJAJE = texto.substring(34, 40).trim();
					LOG.info("NTJ_PORCENJAJE: " + NTJ_PORCENJAJE);

					LOG.info("################");

					// Object[][] matrix = new Object[rows][cols];
					/*
					 * Double d1 = new Double(0000000000000.0D); d1 =
					 * Double.parseDouble(NTJ_MONTO);
					 */
					Object v1 = MES_PROCESO;
					Object v2 = CIP;
					Object v3 = CTP_CODIGO;
					Object v4 = CEC_CODIGO;
					Object v5 = NTJ_MONTO;
					Object v6 = NTJ_PORCENJAJE;

					// for (int filas = 0; filas < numeroFilas; filas++) {
					// for (int i = 0; i < 3; i++) {
					records[contador - 1][0] = v1;
					records[contador - 1][1] = v2;
					records[contador - 1][2] = v3;
					records[contador - 1][3] = v4;
					records[contador - 1][4] = v5;
					records[contador - 1][5] = v6;
					// }
					// }
					try {
						SipreTmpJudicial bean = new SipreTmpJudicial();
						SipreTmpJudicialPK judicialPK = new SipreTmpJudicialPK();
						judicialPK.setCtjMesProceso(MES_PROCESO);
						judicialPK.setCecCodigo(CEC_CODIGO);
						judicialPK.setCpersonaNroAdm(CIP);
						judicialPK.setCtpCodigo(CTP_CODIGO);
						bean.setSipreTmpJudicialPK(judicialPK);
						bean.setNtjMonto(new BigDecimal(NTJ_MONTO));
						bean.setNtjPorcenjaje(new BigDecimal(NTJ_PORCENJAJE));
						ejbJudicial.persist(bean);
						LOG.info("Se guardo Correctamente.");
					} catch (Exception e) {
						LOG.info("Error Txt To DBF detalle : " + e.getMessage());
					}

				} else {
					System.out
							.println("El archivo no cumple la longitud permitida. El Archivo tiene: "
									+ texto.length());
				}
			}

			for (Object[] record : records) {
				dbfwriter.addRecord(record);
			}

			dbfwriter.close();
			System.out.println("DBF CREADO EN LA RAIZ.......");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private File transferFile(FileUploadEvent event) {
		String fileOldName = event.getFile().getFileName();
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
					+ fileOldName.substring(event.getFile().getFileName()
							.lastIndexOf('.'));
			archivo = new File(rutaGuardar + "/"
					+ ConfiguracionDefault.RUTA_FILE_SYSTEM + fileNewName);
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

	@SuppressWarnings("unused")
	private File transferFile(String nameFile, InputStream is) {
		File archivo = null;
		String rutaGuardar = null;
		SimpleDateFormat fileId = null;
		String fileNewName = null;
		try {
			rutaGuardar = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");
			fileId = new SimpleDateFormat("yyyyMMddHHmmss");
			fileNewName = fileId.format(new Date()) + nameFile
					+ nameFile.substring(nameFile.lastIndexOf('.'));
			archivo = new File(rutaGuardar + "/"
					+ ConfiguracionDefault.RUTA_FILE_SYSTEM + fileNewName);
			System.out.println("path :" + rutaGuardar);
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

	public UploadedFile getFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(UploadedFile fileUploaded) {
		this.fileUploaded = fileUploaded;
	}

}
