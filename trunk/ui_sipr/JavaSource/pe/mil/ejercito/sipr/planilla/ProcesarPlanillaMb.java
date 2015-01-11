package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejbremote.CalculoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.CalculoQuintaCategoriaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyPersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PlanillaOtroEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.SituacionAdmEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpBonificacionEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpEntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpFamiliaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpGuardiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TmpJudicialEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;
import pe.mil.ejercito.sipr.model.SipreIngresoGrado;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicionalPK;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetallePK;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;
import pe.mil.ejercito.sipr.model.SipreTmpGuardia;
import pe.mil.ejercito.sipr.model.SipreTmpGuardiaPK;

@ManagedBean(name = "procesarPlanillaMb")
@ViewScoped
public class ProcesarPlanillaMb extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	private static Logger					LOG					= Logger.getLogger(ProcesarPlanillaMb.class);
	private String							mesProceso;
	private Integer							numeroProceso;
	private Boolean[]						habilitarProceso;

	private UsuarioEjbRemote				ejbUsuario;
	private TmpFamiliaEjbRemote				ejbTmpFamilia;
	private PersonaEjbRemote				ejbPersona;
	private SituacionAdmEjbRemote			ejbSituacionAdm;
	private PlanillaEjbRemote				ejbPlanilla;
	private PlanillaOtroEjbRemote			ejbPlanillaOtro;
	private PlanillaDetalleEjbRemote		ejbPlanillaDetalle;
	private PlanillaAdicionalEjbRemote		ejbPlanillaAdicional;
	private PlanillaDescuentoEjbRemote		ejbPlanillaDescuento;

	private ConceptoIngresoEjbRemote		ejbConceptoIngreso;
	private GradoEjbRemote					ejbGrado;
	private IngresoGradoEjbRemote			ejbIngresoGrado;
	private ConceptoDescuentoLeyEjbRemote	ejbConceptoDescuentoLey;
	private DescuentoLeyPersonaEjbRemote	ejbDescuentoLeyPersona;
	private CalculoDescuentoLeyEjbRemote	ejbCalculoDescuentoLey;

	private CalculoQuintaCategoriaEjbRemote	ejbCalculoQuintaCategoria;

	private TmpBonificacionEjbRemote		ejbTmpBonificacion;
	private TmpGuardiaEjbRemote				ejbTmpGuardia;
	private TmpEntidadCrediticiaEjbRemote	ejbTmpCrediticia;
	private TmpJudicialEjbRemote			ejbTmpJudicial;

	private List<SipreTmpFamilia>			beanTmpFamiliaList;
	private List<SiprePersona>				beanPersonaList;
	private List<SiprePlanillaOtro>			beanPlanillaOtroList;

	private SipreTmpFamilia					beanTmpFamilia;
	private SiprePersona					beanPersona;
	private String							tmpCip;
	private int								cTotal;
	private int								cExito;

	@ManagedProperty("#{progressBar}")
	private ProgressBar						progressBar;
	private GenericMessage<Object>			beanGmDetalle;
	private ArrayList<Object>				beanGmListObject;
	private List<GenericMessage<Object>>	beanGmList;
	// beanGmListBusqueda: new filter datatable
	private List<GenericMessage<Object>>	beanGmListBusqueda;
	private GenericMessage<Object>			beanGm;
	private Date							fechaInicio;
	private Date							fechaFin;

	@PostConstruct
	public void init() {
		LOG.info("init : ProcesarPlanillaMb");
		//progressBar.setFechaInicio(new Date());
	}

	public ProcesarPlanillaMb() {
		super();
		LOG.info("###ProcesarPlanillaMb");
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbTmpFamilia = (TmpFamiliaEjbRemote) findServiceRemote(TmpFamiliaEjbRemote.class);
			ejbPersona = (PersonaEjbRemote) findServiceRemote(PersonaEjbRemote.class);
			ejbSituacionAdm = (SituacionAdmEjbRemote) findServiceRemote(SituacionAdmEjbRemote.class);

			ejbPlanilla = (PlanillaEjbRemote) findServiceRemote(PlanillaEjbRemote.class);
			ejbPlanillaOtro = (PlanillaOtroEjbRemote) findServiceRemote(PlanillaOtroEjbRemote.class);
			ejbPlanillaDetalle = (PlanillaDetalleEjbRemote) findServiceRemote(PlanillaDetalleEjbRemote.class);
			ejbPlanillaAdicional = (PlanillaAdicionalEjbRemote) findServiceRemote(PlanillaAdicionalEjbRemote.class);
			ejbPlanillaDescuento = (PlanillaDescuentoEjbRemote) findServiceRemote(PlanillaDescuentoEjbRemote.class);

			ejbTmpGuardia = (TmpGuardiaEjbRemote) findServiceRemote(TmpGuardiaEjbRemote.class);
			ejbTmpBonificacion = (TmpBonificacionEjbRemote) findServiceRemote(TmpBonificacionEjbRemote.class);

			ejbConceptoIngreso = (ConceptoIngresoEjbRemote) findServiceRemote(ConceptoIngresoEjbRemote.class);
			ejbGrado = (GradoEjbRemote) findServiceRemote(GradoEjbRemote.class);
			ejbIngresoGrado = (IngresoGradoEjbRemote) findServiceRemote(IngresoGradoEjbRemote.class);

			ejbDescuentoLeyPersona = (DescuentoLeyPersonaEjbRemote) findServiceRemote(DescuentoLeyPersonaEjbRemote.class);
			ejbCalculoDescuentoLey = (CalculoDescuentoLeyEjbRemote) findServiceRemote(CalculoDescuentoLeyEjbRemote.class);
			ejbConceptoDescuentoLey = (ConceptoDescuentoLeyEjbRemote) findServiceRemote(ConceptoDescuentoLeyEjbRemote.class);

			ejbTmpCrediticia = (TmpEntidadCrediticiaEjbRemote) findServiceRemote(TmpEntidadCrediticiaEjbRemote.class);
			ejbTmpJudicial = (TmpJudicialEjbRemote) findServiceRemote(TmpJudicialEjbRemote.class);

			ejbCalculoQuintaCategoria = (CalculoQuintaCategoriaEjbRemote) findServiceRemote(CalculoQuintaCategoriaEjbRemote.class);

			beanTmpFamiliaList = ejbTmpFamilia.findAll();
			cleanBeanGmList();
			//iniciarEstadoBotones();
			habilitarTodo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void habilitarTodo() {
		habilitarProceso = new Boolean[18];
		for (int i = 1; i < habilitarProceso.length; i++) {
			habilitarProceso[i] = true;
		}
	}

	private void iniciarEstadoBotones() {

		habilitarProceso = new Boolean[18];
		for (int i = 1; i < habilitarProceso.length; i++) {
			habilitarProceso[i] = false;
		}
		habilitarProceso[1] = true;

	}

	private void cleanBeanGmList() {
		beanGmList = new ArrayList<>();
		updateComponente("dt");
	}



	public void p7() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_7, ConstantesUtil.PROCESO_7,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	public void p6() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_6, ConstantesUtil.PROCESO_6,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}







	public void p5() {
		cleanBeanGmList();
		showMessage("###INICIANDO  " + ConstantesUtil.PROCESO_5, SEVERITY_INFO);
		addGenericMensaje("  Registrando Guardias Hospitalarias.", ConstantesUtil.PROCESO_5,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		/*
		 * TALBA INGRESO_GRADO GRADO y CONCEPTO INGRESO obtener el monto que gana esa persona y guardarlo en detalle.
		 */

		if (verificarSiYaSePagoEnPlanillaAdicional()) {
			// si , indicador de tmpbonificacion se actualiza ,aceptado?

		} else {
			// no, indicador de tmpbonificacion se actualiza ,aceptado?
		}

		addGenericMensaje("Finalizando Guardias Hospitalarias.", ConstantesUtil.PROCESO_5,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	private boolean proceso3PlanillaPrincipal() {
		boolean bandera3Ingreso = false;

		List<SiprePlanilla> list = ejbPlanilla.findAll();
		SiprePlanillaDetalle planillaDetalle;
		SiprePlanillaDetallePK pkPlanillaDetalle;

		int cTotal = 0;
		int cExito = 0;
		int cFalla = 0;
		for (SiprePlanilla item : list) {
			cTotal++;
			progressBar.barraProgreso(cTotal, list.size());

			SipreGrado tmpGrado = ejbGrado.findById(item.getSiprePersona().getSipreGrado().getCgradoCodigo());
			List<SipreIngresoGrado> listSipreIngresoGrado = ejbIngresoGrado.findAll();

			for (SipreIngresoGrado itemSIG : listSipreIngresoGrado) {
				if ("01".equals(itemSIG.getSipreConceptoIngreso().getSipreTipoPlanilla().getCtpCodigo())
						&& tmpGrado.getCgradoCodigo().equals(itemSIG.getSipreGrado().getCgradoCodigo())) {

					try {
						planillaDetalle = new SiprePlanillaDetalle();
						pkPlanillaDetalle = new SiprePlanillaDetallePK();

						// PK DETALLE
						pkPlanillaDetalle.setCtpCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01);
						pkPlanillaDetalle.setCpersonaNroAdm(item.getSiprePersona().getCpersonaNroAdm());
						pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
						pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
						pkPlanillaDetalle.setCciCodigo(itemSIG.getSipreConceptoIngreso().getCciCodigo());

						// DETALLE,agrega 1 como texto al final de la cadena para jerarquia.
						planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_3 + "1");
						planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
						planillaDetalle.setCpdConDestino(itemSIG.getSipreConceptoIngreso().getCciCodDestino());
						BigDecimal monto = itemSIG.getNigMonto();
						planillaDetalle.setNpdMtoConcepto(monto);
						//INSERTANDO PLANILLA_DETALLE , verificando que no exita previamente
						if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
							ejbPlanillaDetalle.persist(planillaDetalle);
							cExito++;
						} else {
							addGenericMensaje("Ya se ha registrado antes estos datos. Detalle : " + item.toString(),
									ConstantesUtil.PROCESO_3, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
									ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
						}
					} catch (Exception e) {
						LOG.info(e.getMessage());
						continue;
					}
				}
			}// FOR
		}// FOR
			//TODO corregir el contador entre varios for
		addGenericMensaje(cTotal + " / " + list.size() + "  Registros de la Planilla Principal han sido Procesados.",
				ConstantesUtil.PROCESO_3, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		bandera3Ingreso = true;
		return bandera3Ingreso;
	}

	@SuppressWarnings("unused")
	public boolean verificarSiYaSePagoEnPlanillaAdicional() {

		SiprePlanillaAdicional siprePlanillaAdicional = new SiprePlanillaAdicional();
		boolean banderaProcesoGuardia = false;
		int contador5 = 0;

		try {
			List<SipreTmpGuardia> list = ejbTmpGuardia.findAll(100);

			SipreTmpGuardiaPK pkGuardia = new SipreTmpGuardiaPK();

			if (list.size() == 0) {
				addGenericMensaje("No hay registros de la TmbGuardia ", ConstantesUtil.PROCESO_5,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			} else {
				banderaProcesoGuardia = true;
			}

			if (banderaProcesoGuardia) {

				for (SipreTmpGuardia item : list) {
					contador5++;
					progressBar.barraProgreso(contador5, list.size());

					SiprePlanillaAdicionalPK pk = new SiprePlanillaAdicionalPK();
					pk.setCplanillaMesProceso(mesProceso);
					pk.setCpaMesAdicional(item.getSipreTmpGuardiaPK().getCtgMesGuardia());
					pk.setNplanillaNumProceso(numeroProceso);

					pk.setCpersonaNroAdm(item.getSipreTmpGuardiaPK().getCpersonaNroAdm());
					pk.setCtpCodigo("62");
					pk.setCciCodigo(item.getSipreTmpGuardiaPK().getCciCodigo());
					BigDecimal monto = null;
					try {
						monto = ejbPlanillaAdicional.verificarSiYaSePago(pk);
						if (null == monto || monto.compareTo(BigDecimal.ZERO) < 0) {
							addGenericMensaje("Monto no se pudo comparar con Planilla Adicional.         Detalle : " + pk.toString(),
									ConstantesUtil.PROCESO_5, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
									ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							continue;
						}
					} catch (Exception e) {
						addGenericMensaje("Monto no se pudo comparar con Planilla Adicional.         Detalle : " + pk.toString(),
								ConstantesUtil.PROCESO_5, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
								ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
						continue;

					}

					if (item.getNtgMtoPagado() == monto) {
						// ya esta al dia con el pago
						item.setCtgIndSituacion("1");
					} else {
						// no se le pago.
						item.setCtgIndSituacion("2");
					}
					BigDecimal suma = new BigDecimal(0);
					suma = item.getNtgMtoGestion().subtract(item.getNtgMtoPagado());

					item.setNtgMtoReintegro(suma);
					ejbTmpGuardia.merge(item);
					addGenericMensaje(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA + ". El registro : " + item.toString(),
							ConstantesUtil.PROCESO_5, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

				}

				addGenericMensaje("Se guardaron todos los datos correctamente", ConstantesUtil.PROCESO_5,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				banderaProcesoGuardia = true;
			}
		} catch (Exception e) {

			addGenericMensaje("No se pudo guardar en Tmp Guardia (" + e.getMessage() + ")", ConstantesUtil.PROCESO_5,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			banderaProcesoGuardia = false;

		}
		return banderaProcesoGuardia;

	}

	private List<SiprePlanillaDetalle> planillaAdicionalMonto() {
		// agrupar de planilla adicional por CTP
		SiprePlanillaDetalle planillaDetalle;
		SiprePlanillaDetallePK planillaDetallepk;
		List<SiprePlanillaDetalle> listPlanillaDetalle = new ArrayList<SiprePlanillaDetalle>();
		List<Object[]> list = ejbPlanillaAdicional.findMontoBy(numeroProceso, mesProceso);
		for (Object[] item : list) {
			planillaDetalle = new SiprePlanillaDetalle();
			planillaDetallepk = new SiprePlanillaDetallePK();
			planillaDetallepk.setCtpCodigo((String) item[0]);
			planillaDetallepk.setCpersonaNroAdm((String) item[1]);
			planillaDetallepk.setCplanillaMesProceso((String) item[2]);
			planillaDetallepk.setNplanillaNumProceso((Integer) item[3]);
			// insertar CCI_CODIGO fijo = 0080
			planillaDetallepk.setCciCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
			planillaDetalle.setSiprePlanillaDetallePK(planillaDetallepk);

			planillaDetalle.setNpdMtoConcepto((BigDecimal) item[4]);
			planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
			planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_3 + "2");

			listPlanillaDetalle.add(planillaDetalle);
		}
		return listPlanillaDetalle;
	}

	public void p3() {
		cleanBeanGmList();

		showMessage("## INICIANDO  " + ConstantesUtil.PROCESO_3, SEVERITY_INFO);
		showMessage("LIMPIANDO Planilla Detalle.", SEVERITY_INFO);
		//Se removio la planilla detalle en el P2
		//ejbPlanillaDetalle.removeAll();

		addGenericMensaje("#INICIANDO - Procesando Planilla Principal.", ConstantesUtil.PROCESO_3,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		if (proceso3PlanillaPrincipal()) {
			// si
			addGenericMensaje("#TERMINANDO CORRECTAMENTE - Procesando Planilla Principal.", ConstantesUtil.PROCESO_3,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

			try {
				addGenericMensaje("#INICIANDO - Procesando Planilla ADICIONAL.", ConstantesUtil.PROCESO_3,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				procesar3PlanillaAdicional();
				addGenericMensaje("#TERMINANDO CORRECTAMENTE - Procesando Planilla ADICIONAL.", ConstantesUtil.PROCESO_3,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			} catch (Exception e) {
				addGenericMensaje("#TERMINADO CON ERRORES - Procesando Planilla Principal.", ConstantesUtil.PROCESO_3,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

				LOG.info("No se pudo guardar en Planilla Detalle > " + e.getMessage());
				addGenericMensaje("No se pudo guardar en Planilla Detalle (" + e.getMessage() + ")", ConstantesUtil.PROCESO_3,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}

		} else {
			// no,
			addGenericMensaje("#TERMINADO - Procesando Planilla Principal.", ConstantesUtil.PROCESO_3,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		}

	}

	private void procesar3PlanillaAdicional() throws ClassNotFoundException {

		List<SiprePlanillaDetalle> listPlanillaDetalle = planillaAdicionalMonto();
		int cTotal = 0;

		for (SiprePlanillaDetalle itemPlanillaDetalle : listPlanillaDetalle) {

			if ("307209600".equals(itemPlanillaDetalle.getSiprePlanillaDetallePK().getCpersonaNroAdm())) {
				int monitor;
				monitor = 0;
			}
			cTotal++;
			progressBar.barraProgreso(cTotal, listPlanillaDetalle.size());
			try {

				SiprePlanilla tmpPlanilla = new SiprePlanilla();
				SiprePlanillaPK tmpPlanillaPk = new SiprePlanillaPK();
				tmpPlanillaPk.setCpersonaNroAdm(itemPlanillaDetalle.getSiprePlanillaDetallePK().getCpersonaNroAdm());
				tmpPlanillaPk.setCplanillaMesProceso(mesProceso);
				tmpPlanillaPk.setNplanillaNumProceso(numeroProceso);
				tmpPlanilla.setId(tmpPlanillaPk);

				// SOLO SI existe la planilla ,puede insertarse el Detalle
				if (ejbPlanilla.findPkExist("SiprePlanilla", tmpPlanillaPk)) {
					//if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", item.getSiprePlanillaDetallePK())) {
					ejbPlanillaDetalle.merge(itemPlanillaDetalle);
					//ejbPlanillaDetalle.persist(item);
					addGenericMensaje(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA + " Datos Guardados : " + itemPlanillaDetalle.toString(),
							ConstantesUtil.PROCESO_3, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				} else {
					addGenericMensaje("No se encuentra la PK en SiprePlanilla  =  " + itemPlanillaDetalle.toString(),
							ConstantesUtil.PROCESO_3, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				}
			} catch (Exception e) {
				LOG.info("procesar3PlanillaAdicional SiprePlanillaDetalle :  " + itemPlanillaDetalle.toString());
				LOG.info("ERROR procesar3PlanillaAdicional: " + e.getMessage());
				continue;
			}

		}
	}

	public void p2() {
		cleanBeanGmList();
		addGenericMensaje("### Iniciando Proceso 2 - ListaRevista ", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		addGenericMensaje("### Limpiando Planilla correspondiente... ", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		deleteProcesoDelMes();

		procesarPlanillaPrincipal();
		// procesarPlanillaOtros();
		addGenericMensaje("### Terminando Proceso 2 - Lista Revista", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	private void deleteProcesoDelMes() {
		ejbPlanillaDescuento.deleteProcesoDelMes("SiprePlanillaDescuento", "siprePlanillaDescuentoPK.cplanillaMesProceso",
				"siprePlanillaDescuentoPK.nplanillaNumProceso", mesProceso, numeroProceso);
		ejbPlanillaDetalle.deleteProcesoDelMes("SiprePlanillaDetalle", "siprePlanillaDetallePK.cplanillaMesProceso",
				"siprePlanillaDetallePK.nplanillaNumProceso", mesProceso, numeroProceso);

		ejbPlanilla.deleteProcesoDelMes("SiprePlanilla", "id.cplanillaMesProceso", "id.nplanillaNumProceso", mesProceso, numeroProceso);
	}

	private void procesarPlanillaOtros() {
		int contadorPOtroTotal = 0;
		int contadorPOtrosGuardado = 0;
		addGenericMensaje("###############################################" + "Iniciando -> Procesar Planilla Otro "
				+ "###############################################", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		try {
			// CIP existe en SIPREPERSONA y que no este en SiprePlanilla
			beanPlanillaOtroList = ejbPlanillaOtro.procesarPlanillaOtrosList();
			addGenericMensaje("Se encontro " + beanPlanillaOtroList.size() + " registros de Otras Planillas", ConstantesUtil.PROCESO_2,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		} catch (Exception e1) {
			addGenericMensaje("No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_2,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		}

		// sipre planmia otros , filtrar a la tabla personal
		// verificar si la persona esta, no se puede repetir persona de
		// sipre_palnilla_otro a sipre_planilla
		SiprePlanilla planilla = new SiprePlanilla();
		SiprePersona itemPersona = null;
		int limitador = 0;
		for (SiprePlanillaOtro itemPlanillaOtro : beanPlanillaOtroList) {
			contadorPOtroTotal++;
			progressBar.barraProgreso(contadorPOtroTotal, beanPlanillaOtroList.size());
			itemPersona = new SiprePersona();
			try {
				tmpCip = itemPlanillaOtro.getSiprePlanillaOtroPK().getCpersonaNroAdm();
				itemPersona = ejbPersona.findById(tmpCip);
				addGenericMensaje("Planilla Otros : Se encontro a la Persona CIP  = " + tmpCip, ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

				planilla = new SiprePlanilla();

				SiprePlanillaPK pkPlanilla = new SiprePlanillaPK();
				pkPlanilla.setCpersonaNroAdm(itemPersona.getCpersonaNroAdm());
				pkPlanilla.setCplanillaMesProceso("012014");
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
				//planilla.setCplanillaIndAguin("1");
				//planilla.setCplanillaSexPension("1");
				//planilla.setDplanillaFecAfiAfp(new Date());
				//planilla.setDplanillaFecFal(new Date());
				//planilla.setDplanillaFecFinContr(new Date());
				//planilla.setDplanillaFecRetiro(new Date());

				BigDecimal bPlanillaPorPension = new BigDecimal(1);
				planilla.setNplanillaPorPension(bPlanillaPorPension);

				planilla.setNplanillaTieServicio("1");
				planilla.setSipreSituacionAdm(itemPersona.getSipreSituacionAdm());

				// auditoria
				planilla.setCplanillaUsuMod(getSessionUser().getVusuarioNom());
				planilla.setDplanillaFecReg(new Date());
				planilla.setDplanillaFecMod(new Date());
				LOG.info("MONITOREANDO 1>>" + planilla);
			} catch (NumberFormatException e) {
				addGenericMensaje("Error en el formato de los numeros.", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			} catch (Exception e) {
				addGenericMensaje("No se pudo asignar los datos de SIPRE_PLANILLA_OTRO a SIPRE_PLANILLA - CIP :" + tmpCip,
						ConstantesUtil.PROCESO_2, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
						ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}

			try {
				LOG.info("procesarPlanillaOtros Total :" + contadorPOtroTotal);
				addGenericMensaje(tmpCip + " Persona de PlanillaOtros - verificando para pasar a Planilla.", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
				// PLANILLA OTRO- > PLANILLA
				if (ejbPlanilla.siPersonaExisteEnPlanillaPrincipal(tmpCip) == 0) {
					LOG.info("MONITOREANDO 2>>" + planilla);
					ejbPlanilla.persist(planilla);
					contadorPOtrosGuardado++;
					addGenericMensaje(tmpCip + " Persona de PlanillaOtros - Guardada en Planilla.", ConstantesUtil.PROCESO_2,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
					LOG.info("procesarPlanillaOtros FINAL:" + contadorPOtrosGuardado);
					addGenericMensaje("Se guardo correctamente el CIP : " + tmpCip, ConstantesUtil.PROCESO_2,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
				} else {
					LOG.info(tmpCip + " Persona - ya existe en planilla.(" + contadorPOtroTotal + ")");
					addGenericMensaje(tmpCip + " Persona - ya existe en planilla.", ConstantesUtil.PROCESO_2,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				addGenericMensaje("-Personal " + tmpCip + ".No se pudo grabar el personal.", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
			}
		}

		addGenericMensaje("Planilla Otro : Se registraron " + contadorPOtrosGuardado + "  de  " + contadorPOtroTotal,
				ConstantesUtil.PROCESO_2, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		addGenericMensaje("###############################################" + "FINALIZANDO -> Procesar Planilla Otro "
				+ "###############################################", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	private void procesarPlanillaPrincipal() {
		int cTotal = 0;
		int cExito = 0;
		addGenericMensaje("### Iniciando - Procesar Planilla Principal..", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		try {
			beanPersonaList = ejbPersona.procesarPlanillaPrincipalList();
		} catch (Exception e1) {
			addGenericMensaje("-No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_2,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		}

		// sipre planmia otros , filtrar a la tabla personal
		// verificar si la persona esta, no se puede repetir persona de
		// sipre_palnilla_otro a sipre_planilla
		SiprePlanilla planilla = new SiprePlanilla();
		for (SiprePersona itemPersona : beanPersonaList) {
			cTotal++;
			progressBar.barraProgreso(cTotal, beanPersonaList.size());
			tmpCip = itemPersona.getCpersonaNroAdm();

			try {
				planilla = new SiprePlanilla();

				SiprePlanillaPK pkPlanilla = new SiprePlanillaPK();
				pkPlanilla.setCpersonaNroAdm(itemPersona.getCpersonaNroAdm());
				pkPlanilla.setCplanillaMesProceso(mesProceso);
				pkPlanilla.setNplanillaNumProceso(numeroProceso);
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
				// planilla.setNplanillaRetAscenso(Integer.parseInt(itemPersona.getNpersonaRetAscenso()));

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

				// planilla.setNplanillaTieServicio(null);// pendiente
				// salvatierra
				// planilla.setSipreCalculoQuintaCategoria(sipreCalculoQuintaCategoria);

				// OTROS LLENADOS
				//planilla.setCplanillaIndAguin("1");
				//planilla.setCplanillaSexPension("1");
				// planilla.setDplanillaFecAfiAfp(new Date());
				// planilla.setDplanillaFecFal(new Date());
				// planilla.setDplanillaFecFinContr(new Date());
				// planilla.setDplanillaFecRetiro(new Date());

				//BigDecimal bPlanillaPorPension = new BigDecimal(1);
				//planilla.setNplanillaPorPension(bPlanillaPorPension);

				//planilla.setNplanillaTieServicio("1");
				planilla.setSipreSituacionAdm(itemPersona.getSipreSituacionAdm());

				// auditoria
				planilla.setCplanillaUsuMod(getSessionUser().getVusuarioNom());
				planilla.setDplanillaFecReg(new Date());
				planilla.setDplanillaFecMod(new Date());

			} catch (NumberFormatException e) {
				LOG.info(tmpCip + "  - " + e.getMessage());
				addGenericMensaje("Error en el formato de los numeros.", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			} catch (Exception e) {
				LOG.info(tmpCip + "  - " + e.getMessage());
				addGenericMensaje(tmpCip + "No se pudo asignar obtener la data del Personal ", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}

			try {
				// PERSONA- > PLANILLA
				if (ejbPlanilla.siPersonaExisteEnPlanillaPrincipal(tmpCip) == 0) {
					ejbPlanilla.persist(planilla);
					cExito++;
					addGenericMensaje(tmpCip + " Persona - Guardada en Planilla.", ConstantesUtil.PROCESO_2,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				} else {
					addGenericMensaje(tmpCip + " Persona - ya existe en planilla.", ConstantesUtil.PROCESO_2,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				}

			} catch (Exception e) {
				addGenericMensaje(tmpCip + " Personal No se pudo grabar el personal. (" + e.getMessage() + ")", ConstantesUtil.PROCESO_2,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}
		}

		addGenericMensaje("Planilla Principal : Se registraron " + cExito + "  de  " + cTotal, ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		addGenericMensaje("Finalizando -> Procesar Planilla Principal .", ConstantesUtil.PROCESO_2,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	/**
	 * Tabla Sipre_Situacion_Adm
	 * 
	 * @param busca
	 *            por CSA_CODIGO
	 * @return CSA_TIPO_PERSONA
	 */
	private String obtenerSituacionAdm(String csaCodigo) {
		return ejbSituacionAdm.findById(csaCodigo).getCsaTipoPersona();

	}

	public void p1() {
		cleanBeanGmList();
		boolean estadoP1 = false;
		int cTotal = 0;
		int cExito = 0;
		addGenericMensaje("###INICIANDO - Proceso Numero de Hijos", ConstantesUtil.PROCESO_1,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		try {
			beanPersona = new SiprePersona();
			Integer vEdad;
			String fechaNacimiento;
			Integer procesoContadorHijos = null;
			tmpCip = null;

			try {
				beanPersonaList = ejbPersona.getPersonasEnActividad();
				estadoP1 = true;
			} catch (Exception e1) {

				addGenericMensaje("No se pudo obtener registros de la Tabla Personal.", ConstantesUtil.PROCESO_1,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				estadoP1 = false;
			}

			if (estadoP1) {

				for (SiprePersona itemPersona : beanPersonaList) {
					cTotal++;
					progressBar.barraProgreso(cTotal, beanPersonaList.size());
					addGenericMensaje(
							"Persona : " + itemPersona.getCpersonaNroAdm() + " - Persona Nombre : " + itemPersona.getVpersonaApeNom(),
							ConstantesUtil.PROCESO_1, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

					procesoContadorHijos = 0;
					beanTmpFamiliaList = null;
					try {
						tmpCip = itemPersona.getCpersonaNroAdm();
						beanTmpFamiliaList = ejbTmpFamilia.findAllByIdPersona(tmpCip);
					} catch (Exception e) {
						addGenericMensaje("No se pudo obtener registros de la Tabla Familia con el codigo CIP " + tmpCip,
								ConstantesUtil.PROCESO_1, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
								ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
						continue;
					}

					for (SipreTmpFamilia itemTmpFamilia : beanTmpFamiliaList) {
						String codigoTmpFamilia = itemTmpFamilia.getCtfCif();
						codigoTmpFamilia = codigoTmpFamilia.substring(codigoTmpFamilia.length() - 2, codigoTmpFamilia.length());
						// codigo CIF con codigo 50 -69 ,son familiares
						for (int i = 50; i <= 69; i++) {
							if (i == Integer.parseInt(codigoTmpFamilia)) {
								estadoP1 = true;
								addGenericMensaje(tmpCip + " : Tiene un hijo con codigo XXX" + i + ".", ConstantesUtil.PROCESO_1,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
							}
						}
						// estado 1
						if (UValidacion.esNuloOVacio(itemTmpFamilia.getCtfSitFamilia())) {
							addGenericMensaje("No se encontro la SituacionFamilia en Tabala TMP_FAMILIA asociados al Personal.",
									ConstantesUtil.PROCESO_1, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
									ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
							continue;
						}

						// fecha nacimiento menor a 18
						fechaNacimiento = UValidacion.ConvertDateToString(itemTmpFamilia.getDtfFecNac());
						vEdad = UValidacion.getEdad(fechaNacimiento);
						//SITUACION DE FAMILIA 1
						if ("1".equals(itemTmpFamilia.getCtfSitFamilia())) {
							if (vEdad < 18) {
								procesoContadorHijos++;
							} else {
								addGenericMensaje(tmpCip + " : Es mayor de edad", ConstantesUtil.PROCESO_1,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
							}
						} else {
							addGenericMensaje(tmpCip + " No tiene Situacion de Familia : 1 ", ConstantesUtil.PROCESO_1,
									ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
						}

					}// FOR FAMILIA

					if (beanTmpFamiliaList.size() >= 1) {
						updateProcesoNumeroHijo(itemPersona, procesoContadorHijos);
						cExito++;
						addGenericMensaje(tmpCip + " Numero de Hijos Actualmente : " + procesoContadorHijos, ConstantesUtil.PROCESO_1,
								ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
					}

				}// FOR PERSONA
			}

		} catch (Exception e3) {
			addGenericMensaje("####FINALIZANDO con Error - " + tmpCip + " -No se encontro relacion entre TMP_FAMILIA y PERSONAL",
					ConstantesUtil.PROCESO_1, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			LOG.info("####FINALIZANDO - " + e3.getMessage());
		} finally {
			addGenericMensaje("####FINALIZADO : Se actualizaron  " + cExito + " de " + beanPersonaList.size()
					+ " personas con Situacion Administrativa Presente.", ConstantesUtil.PROCESO_1,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		}

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

	@SuppressWarnings("unused")
	private void updateProcesoNumeroHijo(SiprePersona siprePersona, Integer procesoContadorHijos) {
		// Actualizar Numero hijo de persona

		/* beanPersona = siprePersona; beanPersona.setNpersonaNroHijo(procesoContadorHijos.toString()); ejbPersona.merge(beanPersona); */

		ejbPersona.updatePersonaHijos(tmpCip, procesoContadorHijos.toString());

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

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public List<GenericMessage<Object>> getBeanGmList() {
		return beanGmList;
	}

	public void setBeanGmList(List<GenericMessage<Object>> beanGmList) {
		this.beanGmList = beanGmList;
	}

	public GenericMessage<Object> getBeanGm() {
		return beanGm;
	}

	public void setBeanGm(GenericMessage<Object> beanGm) {
		this.beanGm = beanGm;
	}

	public List<SiprePlanillaOtro> getBeanPlanillaOtroList() {
		return beanPlanillaOtroList;
	}

	public void setBeanPlanillaOtroList(List<SiprePlanillaOtro> beanPlanillaOtroList) {
		this.beanPlanillaOtroList = beanPlanillaOtroList;
	}

	public List<GenericMessage<Object>> getBeanGmListBusqueda() {
		return beanGmListBusqueda;
	}

	public void setBeanGmListBusqueda(List<GenericMessage<Object>> beanGmListBusqueda) {
		this.beanGmListBusqueda = beanGmListBusqueda;
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

	public Boolean[] getHabilitarProceso() {
		return habilitarProceso;
	}

	public void setHabilitarProceso(Boolean[] habilitarProceso) {
		this.habilitarProceso = habilitarProceso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}