package pe.mil.ejercito.sipr.controlCalidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericMessage;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.ProgressBar;
import pe.mil.ejercito.sipr.ejbremote.PersonalPlanillasEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpPersonaEjbRemote;
import pe.mil.ejercito.sipr.model.PersonalPlanillas;
import pe.mil.ejercito.sipr.model.SipreTmpPersona;

@ManagedBean(name = "verificarInformacion")
@ViewScoped
public class VerificarInformacion extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	private static Logger					LOG					= Logger.getLogger(ImportarInformacion.class);
	private String							mesProceso;
	private Integer							numeroProceso;

	private TmpPersonaEjbRemote				ejbTmpPersona;
	private PersonalPlanillasEjbRemote		ejbPersonalPlanillas;

	private List<SipreTmpPersona>			beanTmpPersonaList;
	private List<PersonalPlanillas>			beanPersonalPlanillasList;

	private SipreTmpPersona					beanTmpPersona;
	private PersonalPlanillas				beanPersonalPlanillas;
	private String							tmpCip;
	private String							tmpCipPersonalPlanillas;

	private Integer							contadorP1;

	@ManagedProperty("#{progressBar}")
	private ProgressBar						progressBar;
	private GenericMessage<Object>			beanGmDetalle;
	private ArrayList<Object>				beanGmListObject;
	private List<GenericMessage<Object>>	beanGmList;
	// beanGmListBusqueda: new filter datatable
	private List<GenericMessage<Object>>	beanGmListBusqueda;
	private GenericMessage<Object>			beanGm;

	@PostConstruct
	public void init() {
		LOG.info("verificarInformacion");
	}

	public VerificarInformacion() {
		super();
		LOG.info("#verificarInformacion");
		try {
			ejbTmpPersona = (TmpPersonaEjbRemote) findServiceRemote(TmpPersonaEjbRemote.class);
			ejbPersonalPlanillas = (PersonalPlanillasEjbRemote) findServiceRemote(PersonalPlanillasEjbRemote.class);
			beanTmpPersonaList = ejbTmpPersona.findAll();
			cleanBeanGmList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanBeanGmList() {
		beanGmList = new ArrayList<>();
		updateComponente("dt");
	}

	public void moverTmpPersonaAPlanilla() {
		showMessage("Migrando datos de TmpPersona a Planilla Persona...", SEVERITY_INFO);
	}
	public void moverCopereATmpPersonal() {

		showMessage("Migrando datos de COPERE a Planilla...", SEVERITY_INFO);
		contadorP1 = 0;
		int cExito = 0;

		List<PersonalPlanillas> listPersonalPlanillas = ejbPersonalPlanillas.findAll(500);

		ejbTmpPersona.removeAll();
		for (PersonalPlanillas personaCopere : listPersonalPlanillas) {
			contadorP1++;

			progressBar.barraProgreso(contadorP1, listPersonalPlanillas.size());
			tmpCip = personaCopere.getCplanillasNroadmin();

			try {
				beanTmpPersona = new SipreTmpPersona();

				beanTmpPersona.setCpersonaNroAdm(personaCopere.getCplanillasNroadmin());
				beanTmpPersona.setVtpApeNom(personaCopere.getVplanillasApenom());
				beanTmpPersona.setCtpCodAgrupador(personaCopere.getCusuarioAgrupa());
				beanTmpPersona.setCtpCodArma(personaCopere.getVarmasCodigo());
				beanTmpPersona.setCtpCodBanco(personaCopere.getVbancoCodigo());
				beanTmpPersona.setCtpCodCargo(personaCopere.getVcargoCodigo());
				beanTmpPersona.setCtpCodCedula(personaCopere.getVcedulaCodigo());
				beanTmpPersona.setCtpCodEspAlt(personaCopere.getVplanillasEspalt());
				beanTmpPersona.setCtpCodEstCivil(personaCopere.getVestcivCodigo());

				beanTmpPersona.setCtpCodGraPen(personaCopere.getVgradoPension());
				beanTmpPersona.setCtpCodSitAdm(personaCopere.getVsituadmCodigo());
				beanTmpPersona.setCtpCodSitCausal(personaCopere.getVsitucausalCodigo());
				beanTmpPersona.setCtpCodUnidad(personaCopere.getVunidadesCodigo());
				beanTmpPersona.setCtpDni(personaCopere.getCplanillasDni().trim());
				//beanTmpPersona.setCtpIndAguin(personaCopere.getNplanillasAguinaldo());
				//beanTmpPersona.setCtpIndLicencia(personaCopere.getVgradoPension());
				beanTmpPersona.setCtpIndOnp(personaCopere.getVonpCodigo());
				//beanTmpPersona.setCtpSerRecon(personaCopere.getVgradoPension());
				beanTmpPersona.setCtpSexo(personaCopere.getVsexoCodigo());
				beanTmpPersona.setCtpSexPension(personaCopere.getVsexoPension());
				/*
				beanTmpPersona.setDtpFecAfiAfp(personaCopere.getVgradoPension());
				beanTmpPersona.setDtpFecFal(dtpFecFal);
				beanTmpPersona.setDtpFecFinContr(dtpFecFinContr);
				
				*/
				beanTmpPersona.setDtpFecRetiro(personaCopere.getDplanillasFechreti());

				beanTmpPersona.setDtpFecIng(personaCopere.getDplanillasFeching());
				beanTmpPersona.setDtpFecNac(personaCopere.getDplanillasFechnace());
				beanTmpPersona.setDtpFecPromo(personaCopere.getDplanillasFechpromo());

				beanTmpPersona.setNtpNroHijo(personaCopere.getVcantHijos());
				beanTmpPersona.setNtpPorPension(personaCopere.getNplanillasPorcpen());
				beanTmpPersona.setNtpPorUnif(personaCopere.getVporcUnif());
				beanTmpPersona.setNtpRetAscenso(personaCopere.getDplanillasRetascenso());

				beanTmpPersona.setVtpCadFunc(personaCopere.getVplanillasCadenaf());
				beanTmpPersona.setVtpCodEssalud(personaCopere.getVgradoPension());
				beanTmpPersona.setVtpCuspp(personaCopere.getVplanillasCussp());
				beanTmpPersona.setVtpDocAlta(personaCopere.getVplanillasDocalta());

				beanTmpPersona.setVtpDocRecon(personaCopere.getVplanillasDocreco());
				//beanTmpPersona.setVtpDocRetiro(personaCopere.getVgradoPension());
				beanTmpPersona.setVtpNomCausante(personaCopere.getVplanillasNomcausante());

				beanTmpPersona.setCtpCodGrado(personaCopere.getVgradosCodigo());
				beanTmpPersona.setCtpCodUnidad(personaCopere.getVunidadesCodigo());

			} catch (NumberFormatException e) {
				//LOG.info(tmpCip + "  - " + e.getMessage());
				addGenericMensaje("Error en el formato de los numeros.", "Moviendo Data..",
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			} catch (Exception e) {
				//LOG.info(tmpCip + "  - " + e.getMessage());
				addGenericMensaje(tmpCip + "No se pudo obtener la data de Copere de para esta persona.", "Moviendo Data..",
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}

			try {
				ejbTmpPersona.persist(beanTmpPersona);
				cExito++;
				addGenericMensaje(" se guardo el CIP " + tmpCip + " en Tmp Persona Correctamente.", "Moviendo Data..",
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

			} catch (Exception e) {
				// LOG.error(e.getMessage());
				addGenericMensaje(tmpCip + " Personal No se pudo se guardo en Tmp Persona. (" + e.getMessage() + ")", "Moviendo Data..",
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}
		}

		addGenericMensaje(cExito + "/" + contadorP1 + " se guardo en Tmp Persona Correctamente.", "Moviendo Data..",
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		addGenericMensaje("###############################################" + "Finalizando -> Migracion de Data de Copere a Planilla "
				+ "###############################################", "Moviendo Data..",
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	private void addGenericMensaje(String Mensaje, String constanteNombreProceso, String constanteTipoMensaje, Integer esDetalle) {
		if (1 == esDetalle) {
			beanGmDetalle = new GenericMessage<>();
			beanGmDetalle.setMensaje(Mensaje);
			beanGmDetalle.setCampo(constanteNombreProceso);
			beanGmDetalle.setTipoMensaje(constanteTipoMensaje);
			beanGmListObject.add(beanGmDetalle);
			beanGmList.get(beanGmList.indexOf(beanGm)).setListaObjeto(beanGmListObject);

		} else if (0 == esDetalle) {
			beanGmListObject = new ArrayList<Object>();
			beanGm = new GenericMessage<>();
			beanGm.setMensaje(Mensaje);
			beanGm.setCampo(constanteNombreProceso);
			beanGm.setTipoMensaje(constanteTipoMensaje);
			beanGmList.add(beanGm);
		}
	}

	public String getMesProceso() {
		return mesProceso;
	}

	public void setMesProceso(String mesProceso) {
		this.mesProceso = mesProceso;
	}

	public Integer getNumeroProceso() {
		return numeroProceso;
	}

	public void setNumeroProceso(Integer numeroProceso) {
		this.numeroProceso = numeroProceso;
	}

	public PersonalPlanillasEjbRemote getEjbPersonalPlanillas() {
		return ejbPersonalPlanillas;
	}

	public void setEjbPersonalPlanillas(PersonalPlanillasEjbRemote ejbPersonalPlanillas) {
		this.ejbPersonalPlanillas = ejbPersonalPlanillas;
	}



	public List<PersonalPlanillas> getBeanPersonalPlanillasList() {
		return beanPersonalPlanillasList;
	}

	public void setBeanPersonalPlanillasList(List<PersonalPlanillas> beanPersonalPlanillasList) {
		this.beanPersonalPlanillasList = beanPersonalPlanillasList;
	}

	public String getTmpCip() {
		return tmpCip;
	}

	public void setTmpCip(String tmpCip) {
		this.tmpCip = tmpCip;
	}

	public Integer getContadorP1() {
		return contadorP1;
	}

	public void setContadorP1(Integer contadorP1) {
		this.contadorP1 = contadorP1;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public GenericMessage<Object> getBeanGmDetalle() {
		return beanGmDetalle;
	}

	public void setBeanGmDetalle(GenericMessage<Object> beanGmDetalle) {
		this.beanGmDetalle = beanGmDetalle;
	}

	public ArrayList<Object> getBeanGmListObject() {
		return beanGmListObject;
	}

	public void setBeanGmListObject(ArrayList<Object> beanGmListObject) {
		this.beanGmListObject = beanGmListObject;
	}

	public List<GenericMessage<Object>> getBeanGmList() {
		return beanGmList;
	}

	public void setBeanGmList(List<GenericMessage<Object>> beanGmList) {
		this.beanGmList = beanGmList;
	}

	public List<GenericMessage<Object>> getBeanGmListBusqueda() {
		return beanGmListBusqueda;
	}

	public void setBeanGmListBusqueda(List<GenericMessage<Object>> beanGmListBusqueda) {
		this.beanGmListBusqueda = beanGmListBusqueda;
	}

	public GenericMessage<Object> getBeanGm() {
		return beanGm;
	}

	public void setBeanGm(GenericMessage<Object> beanGm) {
		this.beanGm = beanGm;
	}

	public PersonalPlanillas getBeanPersonalPlanillas() {
		return beanPersonalPlanillas;
	}

	public void setBeanPersonalPlanillas(PersonalPlanillas beanPersonalPlanillas) {
		this.beanPersonalPlanillas = beanPersonalPlanillas;
	}

	public String getTmpCipPersonalPlanillas() {
		return tmpCipPersonalPlanillas;
	}

	public void setTmpCipPersonalPlanillas(String tmpCipPersonalPlanillas) {
		this.tmpCipPersonalPlanillas = tmpCipPersonalPlanillas;
	}

	public TmpPersonaEjbRemote getEjbTmpPersona() {
		return ejbTmpPersona;
	}

	public void setEjbTmpPersona(TmpPersonaEjbRemote ejbTmpPersona) {
		this.ejbTmpPersona = ejbTmpPersona;
	}

	public SipreTmpPersona getBeanTmpPersona() {
		return beanTmpPersona;
	}

	public void setBeanTmpPersona(SipreTmpPersona beanTmpPersona) {
		this.beanTmpPersona = beanTmpPersona;
	}

	public List<SipreTmpPersona> getBeanTmpPersonaList() {
		return beanTmpPersonaList;
	}

	public void setBeanTmpPersonaList(List<SipreTmpPersona> beanTmpPersonaList) {
		this.beanTmpPersonaList = beanTmpPersonaList;
	}


}