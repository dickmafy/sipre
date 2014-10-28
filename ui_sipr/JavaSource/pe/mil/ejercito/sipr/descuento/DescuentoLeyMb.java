package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;


@ManagedBean(name = "descuentoLeyMb")
@ViewScoped
public class DescuentoLeyMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	
	private DescuentoLeyEjbRemote ejb;

	private SipreDescuentoLey bean;
	private List<SipreDescuentoLey> beanList;

	public DescuentoLeyMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (DescuentoLeyEjbRemote) findServiceRemote(DescuentoLeyEjbRemote.class);

			beanList = ejb.findAll(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreDescuentoLey();
	}

	public void saveBean(ActionEvent event) {
		try {
				bean = ejb.persist(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_DESCUENTO_LEY,
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
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_DESCUENTO_LEY,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();
	}

	public SipreDescuentoLey getBean() {
		return bean;
	}

	public void setBean(SipreDescuentoLey bean) {
		this.bean = bean;
	}

	public List<SipreDescuentoLey> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreDescuentoLey> beanList) {
		this.beanList = beanList;
	}

	

	

}