package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericMessage;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejbremote.FamiliaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

@ManagedBean(name = "procesarPlanillaMb")
@ViewScoped
public class ProcesarPlanillaMb extends MainContext implements Serializable {

	private static final long					serialVersionUID	= 1L;
	private static Logger						LOG					= Logger.getLogger(ProcesarPlanillaMb.class);
	private List<GenericMessage<SiprePersona>>	beanGmList;
	private GenericMessage<SiprePersona>		beanGm;

	@SuppressWarnings("unused")
	private UsuarioEjbRemote					ejbUsuario;
	private FamiliaEjbRemote					ejbTmpFamilia;
	private PersonaEjbRemote					ejbPersona;

	private List<SipreTmpFamilia>				beanTmpFamiliaList;
	private List<SiprePersona>					beanPersonaList;

	private SipreTmpFamilia						beanTmpFamilia;
	private SiprePersona						beanPersona;

	public ProcesarPlanillaMb() {
		super();
		LOG.info("###ProcesarPlanillaMb");
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbTmpFamilia = (FamiliaEjbRemote) findServiceRemote(FamiliaEjbRemote.class);
			ejbPersona = (PersonaEjbRemote) findServiceRemote(PersonaEjbRemote.class);

			beanTmpFamiliaList = ejbTmpFamilia.findAll(100);
			beanGmList = new ArrayList<>();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void procesarNumeroHijos() {
		beanPersona = new SiprePersona();
		String numeroHijosPersona = null;
		String cipPersona = null;
		Integer vEdad;
		String fechaNacimiento;
		SipreTmpFamilia sipreTmpFamilia;
		Integer procesoContadorHijos = null;
		beanPersonaList = ejbPersona.findAllBySituacionAdministrativaYActividad();

		for (SiprePersona itemPersona : beanPersonaList) {

			procesoContadorHijos = 0;
			beanTmpFamiliaList = null;

			try {
				beanTmpFamiliaList = ejbTmpFamilia.findAllByIdPersona(itemPersona.getCpersonaNroAdm());
			} catch (Exception e) {
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL + e.getMessage(), SEVERITY_ERROR);

			}

			for (SipreTmpFamilia itemTmpFamilia : beanTmpFamiliaList) {

				// si tiene datos
				if (!UValidacion.esNuloOVacio(itemTmpFamilia.getCtfCif())) {
					String codigoTmpFamilia = itemTmpFamilia.getCtfCif();

					// codigo 50,60
					codigoTmpFamilia = codigoTmpFamilia.substring(codigoTmpFamilia.length() - 2, codigoTmpFamilia.length());

					// 1. que tengan esos codigo son familia
					boolean banderaNumeroHijos = false;
					if ("50".equals(codigoTmpFamilia) || "60".equals(codigoTmpFamilia)) {
						banderaNumeroHijos = true;
					} else {
						banderaNumeroHijos = false;
						addGenericMensaje("No tiene los codigo 56 y 60 ,no tiene familiares.",
								ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
						return;
					}

					if (UValidacion.esNuloOVacio(itemTmpFamilia.getCtfSitFamilia())) {
						addGenericMensaje("No se encontraron datos en TMP FAMILIA asociados al Personal.",
								ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
						return;
					} else {
						banderaNumeroHijos = true;
					}

					if (banderaNumeroHijos) {
						// 2 fecha nacimiento menor a 18
						fechaNacimiento = UValidacion.ConvertDateToString(itemTmpFamilia.getDtfFecNac());
						vEdad = UValidacion.getEdad(fechaNacimiento);

						switch (itemTmpFamilia.getCtfSitFamilia()) {
						case "01":
							if (vEdad < 18) {
								procesoContadorHijos++;
								addGenericMensaje("cod 01 , Tiene hijo menor a 18 años", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
							} else if (vEdad >= 18 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
								addGenericMensaje("cod 01 , Tiene hijo mayor a 18 Años y no tiene Fecha Renovacion ",
										ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING);
								break;

							} else if (vEdad >= 18 && !UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
								procesoContadorHijos++;
								addGenericMensaje("cod 01 , Tiene hijo mayor a 18 Años y si tiene  tiene Fecha Renovacion ",
										ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

							}

						case "02":
							if (vEdad < 28 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
								procesoContadorHijos++;
							}

							break;
						case "03":
							if (vEdad < 28 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
								procesoContadorHijos++;
							}
							break;
						default:
							break;
						}
						addGenericMensaje("Actualizando el Numero de Hijos al personal", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
								ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

						// updateProcesoNumeroHijo(cipPersona,
						// procesoContadorHijos);
					}
				} else {
					addGenericMensaje("No se encontraron datos en TMP FAMILIA asociados al Personal.",
							ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
				}

			}
		}

	}

	private void addGenericMensaje(String Mensaje, String constanteNombreProceso, String constanteTipoMensaje) {
		beanGm = new GenericMessage<>();
		beanGm.setMensaje("No tiene los codigo 56 y 60 ,no tiene familiares.");
		beanGm.setCampo(ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS);
		beanGm.setTipoMensaje(ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
		beanGmList.add(beanGm);
	}

	private void updateProcesoNumeroHijo(String cipPersona, Integer procesoContadorHijos) {
		// Actualizar Numero hijo de persona
		beanPersona.setNpersonaNroHijo(procesoContadorHijos.toString());
		beanPersona.setCpersonaNroAdm(cipPersona);
		// ejbPersona.merge(beanPersona);

		showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA + " Numero hijos : " + procesoContadorHijos + "(" + beanPersona.toString()
				+ ")", SEVERITY_INFO);
	}

	public List<SipreTmpFamilia> getBeanTmpFamiliaList() {
		return beanTmpFamiliaList;
	}

	public void setBeanTmpFamiliaList(List<SipreTmpFamilia> beanTmpFamiliaList) {
		this.beanTmpFamiliaList = beanTmpFamiliaList;
	}

	public List<SiprePersona> getBeanPersonaList() {
		return beanPersonaList;
	}

	public void setBeanPersonaList(List<SiprePersona> beanPersonaList) {
		this.beanPersonaList = beanPersonaList;
	}

	public SipreTmpFamilia getBeanTmpFamilia() {
		return beanTmpFamilia;
	}

	public void setBeanTmpFamilia(SipreTmpFamilia beanTmpFamilia) {
		this.beanTmpFamilia = beanTmpFamilia;
	}

	public SiprePersona getBeanPersona() {
		return beanPersona;
	}

	public void setBeanPersona(SiprePersona beanPersona) {
		this.beanPersona = beanPersona;
	}

	public List<GenericMessage<SiprePersona>> getBeanGmList() {
		return beanGmList;
	}

	public void setBeanGmList(List<GenericMessage<SiprePersona>> beanGmList) {
		this.beanGmList = beanGmList;
	}

	public GenericMessage<SiprePersona> getBeanGm() {
		return beanGm;
	}

	public void setBeanGm(GenericMessage<SiprePersona> beanGm) {
		this.beanGm = beanGm;
	}
}