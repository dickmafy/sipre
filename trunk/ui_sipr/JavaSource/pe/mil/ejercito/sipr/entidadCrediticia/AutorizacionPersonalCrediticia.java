package pe.mil.ejercito.sipr.entidadCrediticia;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.TipoPlanillaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;

@ManagedBean(name = "autorizacionPersonalCrediticia")
@ViewScoped
public class AutorizacionPersonalCrediticia extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private ConceptoIngresoEjbRemote ejb;
	private TipoPlanillaEjbRemote ejbTipoPlanilla;

	private List<SipreConceptoIngreso> beanList;
	private List<SipreTipoPlanilla> tipoPlanillaList;
	private SipreConceptoIngreso bean = new SipreConceptoIngreso();
	private String						anio;
	private String						numero;
	private String						entidad;

	public AutorizacionPersonalCrediticia() {
		super();
		try {
			/*
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (ConceptoIngresoEjbRemote) findServiceRemote(ConceptoIngresoEjbRemote.class);
			ejbTipoPlanilla = (TipoPlanillaEjbRemote) findServiceRemote(TipoPlanillaEjbRemote.class);

			tipoPlanillaList = ejbTipoPlanilla.findAll(100);
			beanList = ejb.findAll(100);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void newBean() {
		showMessage("Se encontro a la entidad Crediticia con exito. Entidad :  " + entidad, SEVERITY_INFO);
		//cleanBean();
	}

	public void cleanBean() {
		SipreTipoPlanilla sipreTipoPlanilla = new SipreTipoPlanilla();
		bean = new SipreConceptoIngreso();
		bean.setSipreTipoPlanilla(sipreTipoPlanilla);
	}

	public void saveBean(ActionEvent event) {
		try {
				bean = ejb.persist(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_CONCEPTO_INGRESO,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();
	}

	public void updateBean(ActionEvent event) {
		try {
				bean = ejb.merge(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_CONCEPTO_INGRESO,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();
	}

	public List<SipreConceptoIngreso> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreConceptoIngreso> beanList) {
		this.beanList = beanList;
	}

	public SipreConceptoIngreso getBean() {
		return bean;
	}

	public void setBean(SipreConceptoIngreso bean) {
		this.bean = bean;
	}

	public List<SipreTipoPlanilla> getTipoPlanillaList() {
		return tipoPlanillaList;
	}

	public void setTipoPlanillaList(List<SipreTipoPlanilla> tipoPlanillaList) {
		this.tipoPlanillaList = tipoPlanillaList;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

}