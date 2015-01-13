package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericMessage;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.ProgressBar;
import pe.mil.ejercito.sipr.ejbremote.CalculoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.CalculoQuintaCategoriaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyPersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PermanenteEjbRemote;
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
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetallePK;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

@ManagedBean(name = "procesarPlanillaMb2")
@ViewScoped
public class ProcesarPlanillaMb2 extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	private static Logger					LOG					= Logger.getLogger(ProcesarPlanillaMb2.class);
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
	private PermanenteEjbRemote				ejbPermanente;

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

	public ProcesarPlanillaMb2() {
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
			ejbPermanente = (PermanenteEjbRemote) findServiceRemote(PermanenteEjbRemote.class);

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

	private void limpiarP4ProcesodelMes() {
		ejbCalculoDescuentoLey.removeDelMes(mesProceso, numeroProceso);

	}

	public void p12() {
		//CONFIGURACION
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_12, ConstantesUtil.PROCESO_12,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		boolean esAdmin = false;
		boolean esDocente = false;
		boolean montoFijo = false;
		cTotal = 0;
		cExito = 0;
		BigDecimal montoFijoSoles;
		SiprePlanillaDetalle planillaDetalle;

		SiprePlanillaDetallePK pkPlanillaDetalle;
		List<SiprePlanilla> list = ejbPlanilla.findAll();

		for (SiprePlanilla itemPlanilla : list) {
			//CONFIGURACION
			cTotal++;
			progressBar.barraProgreso(cTotal, list.size());
			tmpCip = itemPlanilla.getSiprePersona().getCpersonaNroAdm();
			/*
			if ("618853200".equals(tmpCip)) {
				LOG.info("tmp " + tmpCip);
			}*/

			if (ConstantesUtil.PROCESAR_PLANILLA_CODIGO_CSA_ACTIVIDAD_PRESENTE.equals(itemPlanilla.getSiprePersona().getSipreSituacionAdm()
					.getCsaCodigo())) {

				int tmpGradoCodigo = Integer.valueOf(itemPlanilla.getSiprePersona().getSipreGrado().getCgradoCodigo());

				if (tmpGradoCodigo >= 600 && tmpGradoCodigo <= 631 && tmpGradoCodigo >= 700 && tmpGradoCodigo <= 731) {
					/*addGenericMensaje("Comprobando Administrativos.. ", ConstantesUtil.PROCESO_12,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);*/
					esAdmin = true;

				}
				if ((tmpGradoCodigo >= 664 && tmpGradoCodigo <= 696) || (tmpGradoCodigo >= 764 && tmpGradoCodigo <= 796)
						|| (tmpGradoCodigo >= 805 && tmpGradoCodigo <= 829) || (tmpGradoCodigo == 885)) {
					/*addGenericMensaje("Comprobando Docentes.. ", ConstantesUtil.PROCESO_12,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);*/
					esDocente = true;

				}

				if (tmpGradoCodigo >= 821 && tmpGradoCodigo <= 829) {
					/*addGenericMensaje("Insertando en Planilla Detalle un monto fijo de 200 soles... ", ConstantesUtil.PROCESO_12,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);*/
					montoFijo = true;
					montoFijoSoles = new BigDecimal(200);

				} else {
					/*addGenericMensaje("Insertando en Planilla Detalle un monto fijo de 200 soles... ", ConstantesUtil.PROCESO_12,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);*/
					montoFijoSoles = new BigDecimal(400);

				}

				if (esDocente || esAdmin) {
					try {
						planillaDetalle = new SiprePlanillaDetalle();
						pkPlanillaDetalle = new SiprePlanillaDetallePK();

						// PK DETALLE
						pkPlanillaDetalle.setCtpCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01);
						pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
						pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
						pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
						//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
						pkPlanillaDetalle.setCciCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);

						// DETALLE
						planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_12);
						planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
						planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
						planillaDetalle.setNpdMtoConcepto(montoFijoSoles);

						if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
							ejbPlanillaDetalle.persist(planillaDetalle);
							cExito++;
						} else {
							addGenericMensaje("Ya existe el registro :           " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
									ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
						}
					} catch (Exception e) {
						addGenericMensaje("Error al registrar a la persona           " + tmpCip + " en Planila Detalle (" + e.getMessage()
								+ ")", ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
								ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
						continue;
					}
				} else {
					addGenericMensaje("No se encontro relacion con Codigo Docentes o Administradores.         " + itemPlanilla.toString(),
							ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				}

			} else {
				addGenericMensaje("No se encontro relacion con el Tipo de Planilla Principal.            " + itemPlanilla.toString(),
						ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
						ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			}

		}//FOR PLANILLA

		addGenericMensaje("Resultado : Insertado " + cExito + " / " + cTotal + " registros en Sipre Planilla Detalle .",
				ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		addGenericMensaje("#Finalizado " + ConstantesUtil.PROCESO_12, ConstantesUtil.PROCESO_12,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	public void p11() throws EJBTransactionRolledbackException, ClassNotFoundException {
		//CONFIGURACION
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_11, ConstantesUtil.PROCESO_11,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		boolean esAdmin = false;
		boolean esDocente = false;
		boolean montoFijo = false;
		String tmpCip = "";
		cTotal = 0;
		cExito = 0;
		BigDecimal montoFijoSoles;
		SiprePlanillaDetalle planillaDetalle;
		SiprePlanillaDetallePK pkPlanillaDetalle;
		List<SiprePlanilla> list = ejbPlanilla.findAll();
		List<SipreTmpBonificacion> listTmpBonificacion = ejbTmpBonificacion.findAll();

		for (SiprePlanilla itemPlanilla : list) {
			//CONFIGURACION
			cTotal++;
			progressBar.barraProgreso(cTotal, list.size());
			tmpCip = itemPlanilla.getSiprePersona().getCpersonaNroAdm();
			for (SipreTmpBonificacion itemTmpBonificacion : listTmpBonificacion) {
				if ("01".equals(itemPlanilla.getSiprePersona().getSipreSituacionAdm().getCsaCodigo())) {

					//1. tmp bonificacion , ind_situacion : 1 del mes
					if ("1".equals(itemTmpBonificacion.getCtbIndSituacion())) {

						try {
							//INSERTAR EN PLANILLA DETALLE - ind_situacion 1 el MONTO 
							planillaDetalle = new SiprePlanillaDetalle();
							pkPlanillaDetalle = new SiprePlanillaDetallePK();

							// PK DETALLE
							pkPlanillaDetalle.setCtpCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodDestino());
							pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
							pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
							pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
							//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
							pkPlanillaDetalle.setCciCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodigo());

							// DETALLE
							planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_11);
							planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
							planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
							planillaDetalle.setNpdMtoConcepto(itemTmpBonificacion.getNtbMonto());
							if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
								ejbPlanillaDetalle.persist(planillaDetalle);
								cExito++;
							} else {
								addGenericMensaje("Ya existe el registro : " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							}
						} catch (Exception e) {
							addGenericMensaje(
									"Error al registrar a la persona  " + tmpCip + " en Planila Detalle (" + e.getMessage() + ")",
									ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
									ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							continue;
						}

					} else if ("2".equals(itemTmpBonificacion.getCtbIndSituacion())) {
						cExito++;

						//tmp bonificacion , ind_situacion : 2 del mes
						try {
							//INSERTAR EN PLANILLA DETALLE - ind_situacion 2 el MONTO 
							planillaDetalle = new SiprePlanillaDetalle();
							pkPlanillaDetalle = new SiprePlanillaDetallePK();

							// PK DETALLE
							pkPlanillaDetalle.setCtpCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodDestino());
							pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
							pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
							pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
							//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
							pkPlanillaDetalle.setCciCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodigo());

							// DETALLE
							planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_11);
							planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
							planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
							planillaDetalle.setNpdMtoConcepto(itemTmpBonificacion.getNtbMonto());
							if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
								ejbPlanillaDetalle.persist(planillaDetalle);
								cExito++;
							} else {
								addGenericMensaje("Ya existe el registro : " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							}
						} catch (Exception e) {
							addGenericMensaje(
									"Error al registrar a la persona  " + tmpCip + " en Planila Detalle (" + e.getMessage() + ")",
									ConstantesUtil.PROCESO_12, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
									ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							continue;
						}

					}
				} else {
					//1.SI NO EXISTE en el TMPBONIFICACION
					List<SiprePlanillaDetalle> listPermanenciaTmp = null;

					//1.1 ver si existe en PERMANENCIA la persona y el concepto por lo que se procedera con ese valor solo si es mayor el 
					//monto del la tabla Permanencia.
					listPermanenciaTmp = ejbPlanillaDetalle.findObjectByFieldCriteria("CIP", tmpCip, "CONCEPTO", itemTmpBonificacion
							.getSipreConceptoIngreso().getCciCodigo());
					if (null != listPermanenciaTmp && listPermanenciaTmp.size() >= 1) {

						//si el MONTO DE GRADO  es mayor que el monto de PERMANENTE, USAR MONTO DE GRADO
						BigDecimal montoPorGrado = new BigDecimal(100);
						if (montoPorGrado.compareTo(listPermanenciaTmp.get(0).getNpdMtoConcepto()) > 0) {

							planillaDetalle = new SiprePlanillaDetalle();
							pkPlanillaDetalle = new SiprePlanillaDetallePK();

							// PK DETALLE
							pkPlanillaDetalle.setCtpCodigo("0314");
							pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
							pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
							pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
							//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
							pkPlanillaDetalle.setCciCodigo(listPermanenciaTmp.get(0).getSipreConceptoIngreso().getCciCodigo());

							// DETALLE
							planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_11);
							planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
							planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
							planillaDetalle.setNpdMtoConcepto(montoPorGrado);
							if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
								ejbPlanillaDetalle.persist(planillaDetalle);
								cExito++;
							} else {
								addGenericMensaje("Ya existe el registro : " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							}

						} else {
							//SI EL MONTO DE PERMANENTE ES MAYOR  QUE EL MONTO DE GRADO, USAR EL MONTO DE PERMANENTE
							planillaDetalle = new SiprePlanillaDetalle();
							pkPlanillaDetalle = new SiprePlanillaDetallePK();

							// PK DETALLE
							pkPlanillaDetalle.setCtpCodigo("0314");
							pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
							pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
							pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
							//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
							pkPlanillaDetalle.setCciCodigo(listPermanenciaTmp.get(0).getSipreConceptoIngreso().getCciCodigo());

							// DETALLE
							planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_11);
							planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
							planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
							planillaDetalle.setNpdMtoConcepto(listPermanenciaTmp.get(0).getNpdMtoConcepto());
							if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
								ejbPlanillaDetalle.persist(planillaDetalle);
								cExito++;
							} else {
								addGenericMensaje("Ya existe el registro : " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
										ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							}

						}


					} else {

						//1.2 no EXISTE en PERMANENCIA , SE USA LOS VALORES ORIGINALES
						planillaDetalle = new SiprePlanillaDetalle();
						pkPlanillaDetalle = new SiprePlanillaDetallePK();

						// PK DETALLE
						pkPlanillaDetalle.setCtpCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodDestino());
						pkPlanillaDetalle.setCpersonaNroAdm(itemPlanilla.getSiprePersona().getCpersonaNroAdm());
						pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
						pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
						//QUE CONCEPTO VA ? = 'REMUNERACION POR RACIONAMIENTO DS 040 2003'
						pkPlanillaDetalle.setCciCodigo(itemTmpBonificacion.getSipreConceptoIngreso().getCciCodigo());

						// DETALLE
						planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_11);
						planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
						planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
						planillaDetalle.setNpdMtoConcepto(itemTmpBonificacion.getNtbMonto());
						if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
							ejbPlanillaDetalle.persist(planillaDetalle);
							cExito++;
						} else {
							addGenericMensaje("Ya existe el registro : " + itemPlanilla.toString(), ConstantesUtil.PROCESO_12,
									ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
						}
					}//else
				}//else
			}//bonificacion
		}//planilla
		addGenericMensaje("Registrados correctamente : " + list.size() + " / " + list.size(), ConstantesUtil.PROCESO_11,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	public void p10() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_10, ConstantesUtil.PROCESO_10,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	public void p9() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_9, ConstantesUtil.PROCESO_9,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	public void p8() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_8, ConstantesUtil.PROCESO_8,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
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