package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.math.BigDecimal;
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
import pe.mil.ejercito.sipr.ejbremote.PlanillaOtroEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.SituacionAdmEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SipreSituacionAdm;
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
	private SituacionAdmEjbRemote				ejbSituacionAdm;
	private PlanillaEjbRemote					ejbPlanilla;
	private PlanillaOtroEjbRemote				ejbPlanillaOtro;

	private List<SipreTmpFamilia>				beanTmpFamiliaList;
	private List<SiprePersona>					beanPersonaList;
	private List<SiprePlanillaOtro>				beanPlanillaOtroList;

	private SipreTmpFamilia						beanTmpFamilia;
	private SiprePersona						beanPersona;

	public ProcesarPlanillaMb() {
		super();
		LOG.info("###ProcesarPlanillaMb");
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbTmpFamilia = (FamiliaEjbRemote) findServiceRemote(FamiliaEjbRemote.class);
			ejbPersona = (PersonaEjbRemote) findServiceRemote(PersonaEjbRemote.class);
			ejbSituacionAdm = (SituacionAdmEjbRemote) findServiceRemote(SituacionAdmEjbRemote.class);
			ejbPlanilla = (PlanillaEjbRemote) findServiceRemote(PlanillaEjbRemote.class);
			ejbPlanillaOtro = (PlanillaOtroEjbRemote) findServiceRemote(PlanillaOtroEjbRemote.class);

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
		procesarPlanillaPrincipal();
		procesarPlanillaOtros();
	}

	private void procesarPlanillaOtros() {

		try {
			// CIP existe en SIPREPERSONA y que no este en SiprePlanilla
			beanPlanillaOtroList = ejbPlanillaOtro.listBy();
			addGenericMensaje("Se encontro " + beanPlanillaOtroList.size() + " registros de Otras Planillas",
					ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
		} catch (Exception e1) {
			addGenericMensaje("-No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
		}

		// sipre planmia otros , filtrar a la tabla personal
		// verificar si la persona esta, no se puede repetir persona de
		// sipre_palnilla_otro a sipre_planilla
		SiprePlanilla planilla = new SiprePlanilla();
		SiprePersona itemPersona = null;
		for (SiprePlanillaOtro itemPlanillaOtro : beanPlanillaOtroList) {
			String tmpCip = "";
			itemPersona = new SiprePersona();
			try {
				tmpCip = itemPlanillaOtro.getSiprePlanillaOtroPK().getCpersonaNroAdm();
				itemPersona = ejbPersona.findById(tmpCip);
				addGenericMensaje("Se encontro a la Persona CIP : " + tmpCip, ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

				planilla = new SiprePlanilla();

				SiprePlanillaPK pkPlanilla = new SiprePlanillaPK();
				pkPlanilla.setCpersonaNroAdm(itemPersona.getCpersonaNroAdm());
				pkPlanilla.setCplanillaMesProceso("1");
				pkPlanilla.setNplanillaNumProceso(1);
				planilla.setId(pkPlanilla);

				planilla.setCplanillaCodGraPen(itemPersona.getCpersonaCodGraPen());
				planilla.setCplanillaDni(itemPersona.getCpersonaDni());
				planilla.setCplanillaIndActPen(obtenerSituacionAdm(itemPersona.getSipreSituacionAdm().getCsaCodigo()));
				planilla.setCplanillaIndCalculo("1"); // pendiente salvatierra
				planilla.setCplanillaIndLicencia(itemPersona.getCpersonaIndLicencia());
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
				planilla.setNplanillaPorUnif(itemPersona.getNpersonaPorUnif());
				planilla.setNplanillaRetAscenso(Integer.parseInt(itemPersona.getNpersonaRetAscenso()));

				planilla.setSipreAgrupador(itemPersona.getSipreAgrupador());
				planilla.setSipreArma(itemPersona.getSipreArma());
				planilla.setSipreBanco(itemPersona.getSipreBanco());

				planilla.setSipreCargo(itemPersona.getSipreCargo());
				planilla.setSipreCedula(itemPersona.getSipreCedula());
				planilla.setSipreEspecialidadAlterna(itemPersona.getSipreEspecialidadAlterna());
				planilla.setSipreEstadoCivil(itemPersona.getSipreEstadoCivil());
				planilla.setSipreGrado(itemPersona.getSipreGrado());

				planilla.setSipreSituacionCausal(itemPersona.getSipreSituacionCausal());
				planilla.setSipreUnidad(itemPersona.getSipreUnidad());
				planilla.setSipreUsuario(getSessionUser());

				planilla.setNplanillaTieServicio(null);// pendiente salvatierra
				// planilla.setSipreCalculoQuintaCategoria(sipreCalculoQuintaCategoria);

				// OTROS LLENADOS
				planilla.setCplanillaIndAguin("1");
				planilla.setCplanillaSexPension("1");
				planilla.setDplanillaFecAfiAfp(new Date());
				planilla.setDplanillaFecFal(new Date());
				planilla.setDplanillaFecFinContr(new Date());
				planilla.setDplanillaFecRetiro(new Date());

				BigDecimal bPlanillaPorPension = new BigDecimal(1);
				planilla.setNplanillaPorPension(bPlanillaPorPension);

				planilla.setNplanillaTieServicio("1");
				planilla.setSipreSituacionAdm(itemPersona.getSipreSituacionAdm());

				// auditoria
				planilla.setCplanillaUsuMod(getSessionUser().getVusuarioNom());
				planilla.setDplanillaFecReg(new Date());
				planilla.setDplanillaFecMod(new Date());
				itemPersona = null;
			} catch (NumberFormatException e) {
				addGenericMensaje("Error en el formato de los numeros.", ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			} catch (Exception e) {
				addGenericMensaje(
						"No se pudo asignar los datos de SIPRE_PLANILLA_OTRO a SIPRE_PLANILLA - CIP :" + tmpCip,
						ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			}

			try {
				ejbPlanilla.persist(planilla);
				addGenericMensaje("Se guardo correctamente el CIP : " + tmpCip, ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
			} catch (Exception e) {
				LOG.error(e.getMessage());
				addGenericMensaje("-Personal " + tmpCip + ".No se pudo grabar el personal.",
						ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			}
		}

	}

	private void procesarPlanillaPrincipal() {
		try {
			beanPersonaList = ejbPersona.findAllBySituacionAdministrativaDeListaRevista();
		} catch (Exception e1) {
			addGenericMensaje("-No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
		}

		// sipre planmia otros , filtrar a la tabla personal
		// verificar si la persona esta, no se puede repetir persona de
		// sipre_palnilla_otro a sipre_planilla
		SiprePlanilla planilla = new SiprePlanilla();

		for (SiprePersona itemPersona : beanPersonaList) {
			try {
				planilla = new SiprePlanilla();

				SiprePlanillaPK pkPlanilla = new SiprePlanillaPK();
				pkPlanilla.setCpersonaNroAdm(itemPersona.getCpersonaNroAdm());
				pkPlanilla.setCplanillaMesProceso("1");
				pkPlanilla.setNplanillaNumProceso(1);
				planilla.setId(pkPlanilla);

				planilla.setCplanillaCodGraPen(itemPersona.getCpersonaCodGraPen());
				planilla.setCplanillaDni(itemPersona.getCpersonaDni());
				planilla.setCplanillaIndActPen(obtenerSituacionAdm(itemPersona.getSipreSituacionAdm().getCsaCodigo()));
				planilla.setCplanillaIndCalculo("1"); // pendiente salvatierra
				planilla.setCplanillaIndLicencia(itemPersona.getCpersonaIndLicencia());
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
				planilla.setNplanillaPorUnif(itemPersona.getNpersonaPorUnif());
				planilla.setNplanillaRetAscenso(Integer.parseInt(itemPersona.getNpersonaRetAscenso()));

				planilla.setSipreAgrupador(itemPersona.getSipreAgrupador());
				planilla.setSipreArma(itemPersona.getSipreArma());
				planilla.setSipreBanco(itemPersona.getSipreBanco());

				planilla.setSipreCargo(itemPersona.getSipreCargo());
				planilla.setSipreCedula(itemPersona.getSipreCedula());
				planilla.setSipreEspecialidadAlterna(itemPersona.getSipreEspecialidadAlterna());
				planilla.setSipreEstadoCivil(itemPersona.getSipreEstadoCivil());
				planilla.setSipreGrado(itemPersona.getSipreGrado());

				planilla.setSipreSituacionCausal(itemPersona.getSipreSituacionCausal());
				planilla.setSipreUnidad(itemPersona.getSipreUnidad());
				planilla.setSipreUsuario(getSessionUser());

				planilla.setNplanillaTieServicio(null);// pendiente salvatierra
				// planilla.setSipreCalculoQuintaCategoria(sipreCalculoQuintaCategoria);

				// OTROS LLENADOS
				planilla.setCplanillaIndAguin("1");
				planilla.setCplanillaSexPension("1");
				planilla.setDplanillaFecAfiAfp(new Date());
				planilla.setDplanillaFecFal(new Date());
				planilla.setDplanillaFecFinContr(new Date());
				planilla.setDplanillaFecRetiro(new Date());

				BigDecimal bPlanillaPorPension = new BigDecimal(1);
				planilla.setNplanillaPorPension(bPlanillaPorPension);

				planilla.setNplanillaTieServicio("1");
				planilla.setSipreSituacionAdm(itemPersona.getSipreSituacionAdm());

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
				LOG.error(e.getMessage());
				addGenericMensaje("-Personal " + itemPersona.getCpersonaNroAdm() + ".No se pudo grabar el personal.",
						ConstantesUtil.PROCESO_2_PLANILLA_LISTA_REVISTA, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
			}
		}
	}

	/**
	 * Tabla Sipre_Situacion_Adm
	 * 
	 * @param busca
	 *            por CSA_CODIGO
	 * @return CSA_TIPO_PERSONA
	 */
	private String obtenerSituacionAdm(String csaCodigo) {
		return ((SipreSituacionAdm) ejbSituacionAdm.findById(csaCodigo)).getCsaTipoPersona();

	}

	public void procesarNumeroHijos() {
		cleanBeanGmList();
		addGenericMensaje("###INICIANDO.", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

		try {
			beanPersona = new SiprePersona();
			String numeroHijosPersona = null;
			Integer vEdad;
			String fechaNacimiento;
			SipreTmpFamilia sipreTmpFamilia;
			Integer procesoContadorHijos = null;
			String tmpCip = null;

			try {
				beanPersonaList = ejbPersona.findAllBySituacionAdministrativaYActividad();
			} catch (Exception e1) {
				addGenericMensaje("No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
				// return;
			}

			for (SiprePersona itemPersona : beanPersonaList) {

				addGenericMensaje(
						"Persona : " + itemPersona.getCpersonaNroAdm() + " - Persona Nombre : " + itemPersona.getVpersonaApeNom()
								+ "- Numero Hijos Actual : " + itemPersona.getNpersonaNroHijo(),
						ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);

				procesoContadorHijos = 0;
				beanTmpFamiliaList = null;
				tmpCip = itemPersona.getCpersonaNroAdm();

				try {
					beanTmpFamiliaList = ejbTmpFamilia.findAllByIdPersona(tmpCip);
				} catch (Exception e) {
					addGenericMensaje("No se pudo obtener registros de la Tabla Familia con el codigo CIP " + tmpCip,
							ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);

				}

				for (SipreTmpFamilia itemTmpFamilia : beanTmpFamiliaList) {
					String codigoTmpFamilia = itemTmpFamilia.getCtfCif();

					codigoTmpFamilia = codigoTmpFamilia.substring(codigoTmpFamilia.length() - 2, codigoTmpFamilia.length());

					// codigo CIF con codigo 50 -69 ,son familiares
					boolean banderaNumeroHijos = false;
					for (int i = 50; i <= 69; i++) {
						if (i == Integer.parseInt(codigoTmpFamilia)) {
							banderaNumeroHijos = true;
							addGenericMensaje("_Tiene un hijo con codigo XXX" + i + ".", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
									ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
						}
					}
					// estado 1
					if (UValidacion.esNuloOVacio(itemTmpFamilia.getCtfSitFamilia())) {
						addGenericMensaje("No se encontro la SituacionFamilia en Tabala TMP_FAMILIA asociados al Personal.",
								ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
						// return;
					} else {
						banderaNumeroHijos = true;
					}

					if (banderaNumeroHijos) {
						// fecha nacimiento menor a 18
						fechaNacimiento = UValidacion.ConvertDateToString(itemTmpFamilia.getDtfFecNac());
						vEdad = UValidacion.getEdad(fechaNacimiento);
						if ("1".equals(itemTmpFamilia.getCtfSitFamilia())) {
							if (vEdad < 18) {
								procesoContadorHijos++;
							} else {
								addGenericMensaje("__Es mayor de edad", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
							}
						} else {
							addGenericMensaje("No tiene Situacion de Familia : 1 ", ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
									ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR);
						}

					}
				}// FOR FAMILIA

				updateProcesoNumeroHijo(itemPersona, procesoContadorHijos);
				addGenericMensaje("___Numero de Hijos Final: " + procesoContadorHijos, ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
			}// FOR PERSONA

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

	private void updateProcesoNumeroHijo(SiprePersona siprePersona, Integer procesoContadorHijos) {
		// Actualizar Numero hijo de persona
		beanPersona = siprePersona;
		beanPersona.setNpersonaNroHijo(procesoContadorHijos.toString());
		ejbPersona.merge(beanPersona);

	}

	public void guardaHospitalaria() {
		/*
		 * switch (itemTmpFamilia.getCtfSitFamilia()) { case "1":
		 * addGenericMensaje("cod 01",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO); if (vEdad < 18) {
		 * procesoContadorHijos++;
		 * addGenericMensaje("cod 01 , Tiene hijo menor a 18 años",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO); // No tiene fecha
		 * renovacion } else if (vEdad >= 18 &&
		 * UValidacion.esNuloOVacio(itemTmpFamilia
		 * .getCtfFecRenovac().toString())) { addGenericMensaje(
		 * "cod 01 , Tiene hijo mayor a 18 Años y no tiene Fecha Renovacion ",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING); break; // Si
		 * tiene cod renovacion si es 1 pasa } else if (vEdad >= 18 &&
		 * !UValidacion
		 * .esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
		 * procesoContadorHijos++; addGenericMensaje(
		 * "cod 01 , Tiene hijo mayor a 18 Años y si tiene  tiene Fecha Renovacion "
		 * , ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO);
		 * 
		 * } break; case "2": addGenericMensaje("cod 02",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO); if (vEdad < 28 &&
		 * UValidacion
		 * .esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
		 * procesoContadorHijos++; }
		 * 
		 * break; case "3": addGenericMensaje("cod 03",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO); if (vEdad < 28 &&
		 * UValidacion
		 * .esNuloOVacio(itemTmpFamilia.getCtfFecRenovac().toString())) {
		 * procesoContadorHijos++; } break; default:
		 * addGenericMensaje("Ningun Codigo- DEFAULT",
		 * ConstantesUtil.PROCESO_1_PLANILLA_NUMERO_HIJOS,
		 * ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO); break; }
		 */
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