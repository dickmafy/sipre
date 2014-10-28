package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;

@ManagedBean(name = "conceptoDescuentoMb")
@ViewScoped
public class ConceptoDescuentoMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private ConceptoDescuentoEjbRemote ejb;

	private List<SipreConceptoDescuento> beanList;
	private SipreConceptoDescuento bean;

	public ConceptoDescuentoMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (ConceptoDescuentoEjbRemote) findServiceRemote(ConceptoDescuentoEjbRemote.class);

			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreConceptoDescuento();
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

	public List<SipreConceptoDescuento> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreConceptoDescuento> beanList) {
		this.beanList = beanList;
	}

	public SipreConceptoDescuento getBean() {
		return bean;
	}

	public void setBean(SipreConceptoDescuento bean) {
		this.bean = bean;
	}

	

}