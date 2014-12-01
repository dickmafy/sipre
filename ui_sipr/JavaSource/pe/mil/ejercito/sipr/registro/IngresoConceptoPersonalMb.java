package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoConceptoPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreIngresoOtroPK;

@ManagedBean(name = "ingresoConceptoPersonalMb")
@ViewScoped
public class IngresoConceptoPersonalMb extends MainContext implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private IngresoConceptoPersonalEjbRemote ejb;
	private ConceptoIngresoEjbRemote ejbConceptoIngreso;

	private List<SipreIngresoOtro> beanList;
	private List<SipreConceptoIngreso> beanConceptoList;

	private SipreIngresoOtro bean;

	public IngresoConceptoPersonalMb() {
		super();

		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (IngresoConceptoPersonalEjbRemote) findServiceRemote(IngresoConceptoPersonalEjbRemote.class);
			ejbConceptoIngreso = (ConceptoIngresoEjbRemote) findServiceRemote(ConceptoIngresoEjbRemote.class);
			//beanList = ejb.findAll(100);
			beanConceptoList = ejbConceptoIngreso.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		clearBean();
	}

	private void clearBean() {
		bean = new SipreIngresoOtro();
		SipreIngresoOtroPK tmpId = new SipreIngresoOtroPK();
		bean.setId(tmpId);
	}

	public void saveBean(ActionEvent event) {
		try {
			bean.setSipreUsuario(getSessionUser());
			bean = ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL
							+ " Recuerde que no se puede repetir la Persona y el Concepto",
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		clearBean();
	}

	public void updateBean(ActionEvent event) {
		try {
			bean = ejb.merge(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL
							+ " Recuerde que no se puede repetir la Persona y el Concepto",
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		clearBean();
	}

	public List<SipreIngresoOtro> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreIngresoOtro> beanList) {
		this.beanList = beanList;
	}

	public List<SipreConceptoIngreso> getBeanConceptoList() {
		return beanConceptoList;
	}

	public void setBeanConceptoList(List<SipreConceptoIngreso> beanConceptoList) {
		this.beanConceptoList = beanConceptoList;
	}

	public SipreIngresoOtro getBean() {
		return bean;
	}

	public void setBean(SipreIngresoOtro bean) {
		this.bean = bean;
	}

	

}