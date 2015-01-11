package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLeyPK;
import pe.mil.ejercito.sipr.model.SipreCalculoQuintaCategoria;
import pe.mil.ejercito.sipr.model.SipreCalculoQuintaCategoriaPK;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyPersona;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetallePK;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;
import pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;
import pe.mil.ejercito.sipr.model.SipreTmpJudicial;

@ManagedBean(name = "procesarPlanillaMb3")
@ViewScoped
public class ProcesarPlanillaMb3 extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	private static Logger					LOG					= Logger.getLogger(ProcesarPlanillaMb3.class);
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

	public ProcesarPlanillaMb3() {
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

	public void p17() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_17, ConstantesUtil.PROCESO_17,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	public void p16() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_16, ConstantesUtil.PROCESO_16,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		//CONFIGURACION
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_12, ConstantesUtil.PROCESO_12,
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

		List<SipreTmpEntidadCrediticia> listTmpCrediticia = ejbTmpCrediticia.findAll();
		List<SipreTmpJudicial> listTmpJudicial = ejbTmpJudicial.findAll();

		cTotal++;
		for (SipreTmpJudicial itemJudicial : listTmpJudicial) {
			//CONFIGURACION

			progressBar.barraProgreso(cTotal, listTmpJudicial.size() + listTmpCrediticia.size());
			//tmpCip = itemPlanilla.getSiprePersona().getCpersonaNroAdm();

			try {
				planillaDetalle = new SiprePlanillaDetalle();
				pkPlanillaDetalle = new SiprePlanillaDetallePK();

				// PK DETALLE
				pkPlanillaDetalle.setCtpCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01);
				pkPlanillaDetalle.setCpersonaNroAdm(itemJudicial.getSiprePersona().getCpersonaNroAdm());
				pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
				pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
				pkPlanillaDetalle.setCciCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);

				// DETALLE
				planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_16);
				planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
				planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
				planillaDetalle.setNpdMtoConcepto(itemJudicial.getNtjMonto());

				if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
					ejbPlanillaDetalle.persist(planillaDetalle);
					cExito++;
				} else {
					addGenericMensaje("Ya existe el registro repetido en Planilla Detalle : " + pkPlanillaDetalle.toString(),
							ConstantesUtil.PROCESO_16, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				}
			} catch (Exception e) {
				addGenericMensaje("Error al registrar el registro TEMPORAL Judicial en Planilla Detalle  " + "(" + e.getMessage() + ")",
						ConstantesUtil.PROCESO_16, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
						ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				continue;
			}

		}//judicial

		for (SipreTmpEntidadCrediticia itemCrediticia : listTmpCrediticia) {
			progressBar.barraProgreso(cTotal, listTmpJudicial.size() + listTmpCrediticia.size());
			try {
				planillaDetalle = new SiprePlanillaDetalle();
				pkPlanillaDetalle = new SiprePlanillaDetallePK();

				// PK DETALLE
				planillaDetalle.setIndProceso(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_16);
				pkPlanillaDetalle.setCtpCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01);
				pkPlanillaDetalle.setCpersonaNroAdm(itemCrediticia.getSiprePersona().getCpersonaNroAdm());
				pkPlanillaDetalle.setCplanillaMesProceso(mesProceso);
				pkPlanillaDetalle.setNplanillaNumProceso(numeroProceso);
				pkPlanillaDetalle.setCciCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);

				// DETALLE
				planillaDetalle.setSiprePlanillaDetallePK(pkPlanillaDetalle);
				planillaDetalle.setCpdConDestino(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080);
				planillaDetalle.setNpdMtoConcepto(itemCrediticia.getNtecMonto());

				if (!ejbPlanillaDetalle.findPkExist("SiprePlanillaDetalle", pkPlanillaDetalle)) {
					ejbPlanillaDetalle.persist(planillaDetalle);
					cExito++;
				} else {
					addGenericMensaje("Ya existe el registro en Planilla Detalle : " + pkPlanillaDetalle.toString(),
							ConstantesUtil.PROCESO_16, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_WARNING,
							ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				}
			} catch (Exception e) {
				addGenericMensaje(
						"Error al registrar el registro TEMPORAL de Entidad Crediticia en Planilla Detalle  " + "(" + e.getMessage() + ")",
						ConstantesUtil.PROCESO_16, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
						ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
				continue;
			}

		}//crediticia
	}

	public void p15() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_15, ConstantesUtil.PROCESO_15,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		List<SiprePlanilla> list = ejbPlanilla.findAll();
		BigDecimal sueldo = new BigDecimal(0);
		SipreCalculoQuintaCategoria beanQuinta = new SipreCalculoQuintaCategoria();
		SipreCalculoQuintaCategoriaPK beanQuintaPk = new SipreCalculoQuintaCategoriaPK();
		int cTotal = 0;
		int cExito = 0;
		ejbCalculoQuintaCategoria.removeDelMes(mesProceso, numeroProceso);

		for (SiprePlanilla itemPlanilla : list) {
			try {
				//CONFIGURACION
				cTotal++;
				progressBar.barraProgreso(cTotal, list.size());
				tmpCip = itemPlanilla.getSiprePersona().getCpersonaNroAdm();
				beanQuinta = new SipreCalculoQuintaCategoria();
				beanQuintaPk = new SipreCalculoQuintaCategoriaPK();
				sueldo = ejbPlanillaDetalle.getSueldoPorPersona(tmpCip, numeroProceso, mesProceso);

				//CASO 1 : No incremento sueldo
				//BEAN PK
				beanQuintaPk.setCpersonaNroAdm(tmpCip);
				beanQuintaPk.setCplanillaMesProceso(mesProceso);
				beanQuintaPk.setNplanillaNumProceso(numeroProceso);
				beanQuinta.setSipreCalculoQuintaCategoriaPK(beanQuintaPk);

				BigDecimal meses = new BigDecimal(12);
				BigDecimal per = new BigDecimal(0.15);
				per = per.setScale(2, RoundingMode.HALF_UP);
				BigDecimal vecesUIT = new BigDecimal(7);

				BigDecimal UIT = new BigDecimal(2000);
				BigDecimal UITPor7 = UIT.multiply(vecesUIT);
				BigDecimal montoFinal = new BigDecimal(0);

				//Ganancia al Año
				montoFinal = sueldo.multiply(meses);

				//restas las 7 UIT
				montoFinal = montoFinal.subtract(UITPor7);

				//obtener el 15 porciento
				montoFinal = montoFinal.multiply(per);
				beanQuinta.setNcqcImpRenta(montoFinal);

				//div entre los meses
				montoFinal = montoFinal.divide(meses);
				beanQuinta.setNcqcRemun(montoFinal);

				beanQuinta.setCcqcTipoPersona("A");
				beanQuinta.setDcqcFecReg(new Date());
				beanQuinta.setNcqcAdsto(new BigDecimal(0));
				beanQuinta.setNcqcBonif(new BigDecimal(0));
				beanQuinta.setNcqcReing(new BigDecimal(0));

				ejbCalculoQuintaCategoria.persist(beanQuinta);
				cExito++;
			} catch (Exception e) {
				//TODO :Remove for test
				/*addGenericMensaje("Finalizado con errores :  " + e.getMessage(), ConstantesUtil.PROCESO_15,
						ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);*/
			}

		}

		addGenericMensaje("Finalizado  " + cTotal + "/" + cTotal + " registros guardados", ConstantesUtil.PROCESO_15,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

	}

	public void p14() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_14, ConstantesUtil.PROCESO_14,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	public void p13() {
		cleanBeanGmList();
		addGenericMensaje("Iniciando " + ConstantesUtil.PROCESO_13, ConstantesUtil.PROCESO_13,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
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

	public void p4DescuentoLey() {
		cleanBeanGmList();
		addGenericMensaje("Limpiando CalculoDescuentoLey....", ConstantesUtil.PROCESO_4, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO,
				ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		limpiarP4ProcesodelMes();

		//temportal

		List<SiprePlanilla> list = ejbPlanilla.findAll();
		int cTotal = 0;
		String codigoConceptoDescuento = "";

		addGenericMensaje("Obteniendo el Descuento Ley Persona de ....", ConstantesUtil.PROCESO_4,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);

		for (SiprePlanilla item : list) {
			cTotal++;
			progressBar.barraProgreso(cTotal, list.size());
			tmpCip = item.getSiprePersona().getCpersonaNroAdm();
			List<SipreDescuentoLeyPersona> listDLP = ejbDescuentoLeyPersona.findByCip(tmpCip);
			addGenericMensaje("Iniciando  el Calculo Concepto Descuento Ley de CIP: " + tmpCip, ConstantesUtil.PROCESO_4,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
			for (SipreDescuentoLeyPersona itemDLP : listDLP) {
				try {
					List<SipreConceptoDescuentoLey> listCDL = ejbConceptoDescuentoLey.findAll();

					addGenericMensaje("Iniciando  el Calculo descuento Ley Persona de CIP: " + tmpCip, ConstantesUtil.PROCESO_4,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
					for (SipreConceptoDescuentoLey itemConceptoDL : listCDL) {
						try {
							if ((itemConceptoDL.getSipreConceptoDescuentoLeyPK().getCdlCodigo()
									.equals(itemDLP.getSipreDescuentoLeyPersonaPK().getCdlCodigo()) && (itemConceptoDL
									.getSipreConceptoDescuentoLeyPK().getCdldCodigo().equals(itemDLP.getSipreDescuentoLeyPersonaPK()
									.getCdldCodigo())))) {

								codigoConceptoDescuento = itemConceptoDL.getSipreConceptoDescuentoLeyPK().getCcdCodigo();

								insertCalculoDescuentoLey(codigoConceptoDescuento, tmpCip, itemConceptoDL);
							}
						} catch (Exception e) {
							addGenericMensaje("Error al procesar el ConceptoDescuentoLey  de CIP: " + tmpCip + itemConceptoDL.toString(),
									ConstantesUtil.PROCESO_4, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
									ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
							addGenericMensaje(itemConceptoDL.toString() + " - Error ConceptoDescuentoLey -  " + e.getMessage(),
									ConstantesUtil.PROCESO_4, ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR,
									ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
							continue;
						}
					}//for
						//}
				} catch (Exception e) {
					addGenericMensaje("Error al procesar el DescuentoLeyPersona" + itemDLP.toString(), ConstantesUtil.PROCESO_4,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
					addGenericMensaje(itemDLP.toString() + "Error DescuentoLeyPersona: " + e.getMessage(), ConstantesUtil.PROCESO_4,
							ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_HIJO);
					continue;
				}
			}//for
		}//for

		addGenericMensaje(cTotal + " / " + list.size() + "  Registros Procesados.", ConstantesUtil.PROCESO_4,
				ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
	}

	private void insertCalculoDescuentoLey(String codigoConceptoDescuento, String tmpCIp, SipreConceptoDescuentoLey itemConceptoDL) {
		//List<SipreCalculoDescuentoLey> listCalculoDL = ejbCalculoDescuentoLey.findAll();
		SipreCalculoDescuentoLey sipreCalculoDescuentoLey = new SipreCalculoDescuentoLey();
		SipreCalculoDescuentoLeyPK pk = new SipreCalculoDescuentoLeyPK();
		sipreCalculoDescuentoLey = new SipreCalculoDescuentoLey();
		pk = new SipreCalculoDescuentoLeyPK();
		pk.setCcdCodigo(itemConceptoDL.getSipreConceptoDescuentoLeyPK().getCcdCodigo());
		pk.setCpersonaNroAdm(tmpCIp);
		pk.setCplanillaMesProceso(mesProceso);
		pk.setCtpCodigo(ConstantesUtil.PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01); //PRINCIPAL
		pk.setNplanillaNumProceso(numeroProceso);
		sipreCalculoDescuentoLey.setSipreCalculoDescuentoLeyPK(pk);

		BigDecimal sueldo = ejbPlanillaDetalle.getSueldoPorPersona(tmpCIp, numeroProceso, mesProceso);
		sipreCalculoDescuentoLey.setNcdlMtoAplicable(sueldo.multiply(itemConceptoDL.getNcdlPorEmpleado()));
		sipreCalculoDescuentoLey.setNcdlMtoEmpleado(itemConceptoDL.getNcdlPorEmpleado());
		sipreCalculoDescuentoLey.setNcdlMtoEmpleador(itemConceptoDL.getNcdlPorEmpleador());
		try {
			ejbCalculoDescuentoLey.persist(sipreCalculoDescuentoLey);
			addGenericMensaje("Insertando Calculo descuento ley persona  de CIP: " + tmpCip, ConstantesUtil.PROCESO_4,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_INFO, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		} catch (Exception e) {
			addGenericMensaje("Error Insertando Calculo descuento Ley Persona  de CIP: " + tmpCip, ConstantesUtil.PROCESO_4,
					ConstantesUtil.MENSAJE_GENERIC_TIPO_MENSAJE_ERROR, ConstantesUtil.GENERIC_MENSAJE_DT_PADRE);
		}
	}

	private void limpiarP4ProcesodelMes() {
		ejbCalculoDescuentoLey.removeDelMes(mesProceso, numeroProceso);

	}

	
	private void deleteProcesoDelMes() {
		ejbPlanillaDescuento.deleteProcesoDelMes("SiprePlanillaDescuento", "siprePlanillaDescuentoPK.cplanillaMesProceso",
				"siprePlanillaDescuentoPK.nplanillaNumProceso", mesProceso, numeroProceso);
		ejbPlanillaDetalle.deleteProcesoDelMes("SiprePlanillaDetalle", "siprePlanillaDetallePK.cplanillaMesProceso",
				"siprePlanillaDetallePK.nplanillaNumProceso", mesProceso, numeroProceso);

		ejbPlanilla.deleteProcesoDelMes("SiprePlanilla", "id.cplanillaMesProceso", "id.nplanillaNumProceso", mesProceso, numeroProceso);
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