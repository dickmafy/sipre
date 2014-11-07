package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

@ManagedBean(name = "procesarPlanillaMb")
@ViewScoped
public class ProcesarPlanillaMb extends MainContext implements Serializable {

	private static final long					serialVersionUID	= 1L;
	private static Logger						LOG					= Logger.getLogger(ProcesarPlanillaMb.class);
	private List<GenericMessage<SiprePersona>>	beanGmList;
	private GenericMessage<SiprePersona>		beanGm;

	private UsuarioEjbRemote					ejbUsuario;
	private FamiliaEjbRemote					ejbTmpFamilia;
	private PersonaEjbRemote					ejbPersona;
	private PlanillaEjbRemote					ejbPlanilla;

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
			cleanBeanGmList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanBeanGmList() {
		beanGmList = new ArrayList<>();
	}

	public void procesarListaRevista() {
		cleanBeanGmList();

		try {
			beanPersonaList = ejbPersona.findAllBySituacionAdministrativaDeListaRevista();
		} catch (Exception e1) {
			addGenericMensaje("-No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			// return;
		}

		SiprePlanilla planilla = null;

		for (SiprePersona itemPersona : beanPersonaList) {
			try {
				planilla = new SiprePlanilla();

				SiprePlanillaPK pkPlanilla = new SiprePlanillaPK();

				planilla.setId(pkPlanilla);

				planilla.setCplanillaCodGraPen(itemPersona.getCpersonaCodGraPen());
				planilla.setCplanillaDni(itemPersona.getCpersonaDni());
				planilla.setCplanillaIndActPen(null);
				planilla.setCplanillaIndCalculo(null);
				planilla.setCplanillaIndLicencia(null);
				planilla.setCplanillaIndOnp(itemPersona.getCpersonaIndOnp());
				planilla.setCplanillaIndQui(itemPersona.getCpersonaIndQui());
				planilla.setCplanillaSerRecon(itemPersona.getCpersonaSerRecon());
				planilla.setCplanillaSexo(itemPersona.getCpersonaSexo());
				planilla.setCplanillaSexPension(itemPersona.getCpersonaSexPension());

				planilla.setDplanillaFecAfiAfp(itemPersona.getDpersonaFecAfiAfp());
				planilla.setDplanillaFecFal(itemPersona.getDpersonaFecFal());
				planilla.setDplanillaFecFinContr(itemPersona.getDpersonaFecFinContr());
				planilla.setDplanillaFecIng(itemPersona.getDpersonaFecIng());
				planilla.setDplanillaFecNac(itemPersona.getDpersonaFecNac());
				planilla.setDplanillaFecPromo(itemPersona.getDpersonaFecPromo());
				planilla.setDplanillaFecRetiro(itemPersona.getDpersonaFecRetiro());

				planilla.setVplanillaApeNom(itemPersona.getVpersonaApeNom());
				planilla.setVplanillaCadFunc(itemPersona.getVpersonaCadFunc());
				planilla.setVplanillaCodEssalud(itemPersona.getVpersonaCodEssalud());
				planilla.setVplanillaCuspp(itemPersona.getVpersonaCuspp());
				planilla.setVplanillaDocAlta(itemPersona.getVpersonaDocAlta());
				planilla.setVplanillaDocRecon(itemPersona.getVpersonaDocRecon());
				planilla.setVplanillaDocRetiro(itemPersona.getVpersonaDocRetiro());
				planilla.setVplanillaNomCausante(itemPersona.getVpersonaNomCausante());

				planilla.setNplanillaNroHijo(Integer.parseInt(itemPersona.getNpersonaNroHijo()));
				planilla.setNplanillaPorPension(itemPersona.getNpersonaPorPension());
				planilla.setNplanillaPorUnif(itemPersona.getNpersonaPorUnif());
				planilla.setNplanillaRetAscenso(Integer.parseInt(itemPersona.getNpersonaRetAscenso()));
				planilla.setNplanillaTieServicio(null);

				planilla.setSipreAgrupador(itemPersona.getSipreAgrupador());
				planilla.setSipreArma(itemPersona.getSipreArma());
				planilla.setSipreBanco(itemPersona.getSipreBanco());
				planilla.setSipreCalculoQuintaCategoria(null);
				planilla.setSipreCargo(itemPersona.getSipreCargo());
				planilla.setSipreCedula(itemPersona.getSipreCedula());
				planilla.setSipreEspecialidadAlterna(itemPersona.getSipreEspecialidadAlterna());
				planilla.setSipreEstadoCivil(itemPersona.getSipreEstadoCivil());
				planilla.setSipreGrado(itemPersona.getSipreGrado());
				planilla.setSiprePersona(itemPersona);// PERSONA repetido?
				planilla.setSipreSituacionCausal(itemPersona.getSipreSituacionCausal());
				planilla.setSipreUnidad(itemPersona.getSipreUnidad());
				planilla.setSipreUsuario(getSessionUser());

				// auditoria
				planilla.setCplanillaUsuMod(getSessionUser().getVusuarioNom());
				planilla.setDplanillaFecReg(new Date());
				planilla.setDplanillaFecMod(new Date());
			} catch (NumberFormatException e) {
				addGenericMensaje("Error en el formato de los numeros.", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			} catch (Exception e) {
				addGenericMensaje("No se pudo asignar obtener la data del Personal " + itemPersona.getCpersonaNroAdm(),
						ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			}

			try {
				ejbPlanilla.merge(planilla);
			} catch (Exception e) {
				addGenericMensaje("-Personal " + itemPersona.getCpersonaNroAdm() + ".No se pudo grabar el personal.",
						ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			}
		}
	}

	public void procesarNumeroHijos() {
		cleanBeanGmList();
		addGenericMensaje("###INICIANDO.", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

		try {
			beanPersona = new SiprePersona();
			String numeroHijosPersona = null;
			String cipPersona = null;
			Integer vEdad;
			String fechaNacimiento;
			SipreTmpFamilia sipreTmpFamilia;
			Integer procesoContadorHijos = null;

			try {
				beanPersonaList = ejbPersona.findAllBySituacionAdministrativaYActividad();
			} catch (Exception e1) {
				addGenericMensaje("-No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
				// return;
			}

			for (SiprePersona itemPersona : beanPersonaList) {

				addGenericMensaje(
						"###Persona : " + itemPersona.getCpersonaNroAdm() + " - Persona Nombre : " + itemPersona.getVpersonaApeNom()
								+ "- Persona Numero Hijos : " + itemPersona.getNpersonaNroHijo(),
						ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

				procesoContadorHijos = 0;
				beanTmpFamiliaList = null;

				try {
					beanTmpFamiliaList = ejbTmpFamilia.findAllByIdPersona(itemPersona.getCpersonaNroAdm());
				} catch (Exception e) {
					addGenericMensaje("-No se pudo obtener registros de la Tabla Familia por CIP Persona.",
							ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
					// return;
				}

				for (SipreTmpFamilia itemTmpFamilia : beanTmpFamiliaList) {

					// si tiene datos
					if (!UValidacion.esNuloOVacio(itemTmpFamilia.getCtfCif())) {
						String codigoTmpFamilia = itemTmpFamilia.getCtfCif();

						// codigo 50 -69
						codigoTmpFamilia = codigoTmpFamilia.substring(codigoTmpFamilia.length() - 2, codigoTmpFamilia.length());

						// 1. que tengan esos codigo son familia
						boolean banderaNumeroHijos = false;
						for (int i = 50; i <= 69; i++) {
							if (i == Integer.parseInt(codigoTmpFamilia)) {
								banderaNumeroHijos = true;
								addGenericMensaje("Tiene un hijo con codigo XXXXX" + i + ".",
										ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
							} else {
								/*
								 * banderaNumeroHijos = false;
								 * addGenericMensaje(
								 * "No tiene los codigo 56 y 60 ,no tiene familiares."
								 * ,
								 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS
								 * ,
								 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR
								 * );
								 * 
								 * //return;
								 */
							}
						}

						if (UValidacion.esNuloOVacio(itemTmpFamilia.getCtfSitFamilia())) {
							addGenericMensaje("No se encontraron datos en TMP FAMILIA asociados al Personal.",
									ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
							// return;
						} else {
							banderaNumeroHijos = true;
						}

						if (banderaNumeroHijos) {
							// 2 fecha nacimiento menor a 18
							fechaNacimiento = UValidacion.ConvertDateToString(itemTmpFamilia.getDtfFecNac());
							vEdad = UValidacion.getEdad(fechaNacimiento);

							switch (itemTmpFamilia.getCtfSitFamilia()) {
							case "1":
								addGenericMensaje("cod 01", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
								if (vEdad < 18) {
									procesoContadorHijos++;
									addGenericMensaje("cod 01 , Tiene hijo menor a 18 años",
											ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
											ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
									// No tiene fecha renovacion
								} else if (vEdad >= 18 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
									addGenericMensaje("cod 01 , Tiene hijo mayor a 18 Años y no tiene Fecha Renovacion ",
											ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
											ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING);
									break;
									// Si tiene fecha renovacion
								} else if (vEdad >= 18 && !UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
									procesoContadorHijos++;
									addGenericMensaje("cod 01 , Tiene hijo mayor a 18 Años y si tiene  tiene Fecha Renovacion ",
											ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
											ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

								}
								break;
							case "2":
								addGenericMensaje("cod 02", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
								if (vEdad < 28 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
									procesoContadorHijos++;
								}

								break;
							case "3":
								addGenericMensaje("cod 03", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
								if (vEdad < 28 && UValidacion.esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
									procesoContadorHijos++;
								}
								break;
							default:
								addGenericMensaje("Ningun Codigo- DEFAULT", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
								break;
							}

							// updateProcesoNumeroHijo(cipPersona,
							// procesoContadorHijos);
						}
					} else {
						addGenericMensaje("No se encontraron datos en TMP FAMILIA asociados al Personal.",
								ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
					}

				}
				addGenericMensaje("Actualizando el Numero de Hijos : #" + procesoContadorHijos + "al personal",
						ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
			}
		} catch (Exception e3) {
			addGenericMensaje("####FINALIZANDO - Con Error " + e3.getMessage(), ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
		} finally {
			addGenericMensaje("####FINALIZANDO", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
		}

	}

	private void addGenericMensaje(String Mensaje, String constanteNombreProceso, String constanteTipoMensaje) {
		beanGm = new GenericMessage<>();
		beanGm.setMensaje(Mensaje);
		beanGm.setCampo(constanteNombreProceso);
		beanGm.setTipoMensaje(constanteTipoMensaje);
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