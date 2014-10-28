package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDetPK;

@ManagedBean(name = "descuentoLeyDetalleMb")
@ViewScoped
public class DescuentoLeyDetalleMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejbUsuario;
	private DescuentoLeyEjbRemote ejbDescuentoLey;

	private DescuentoLeyDetalleEjbRemote ejb;
	private List<SipreDescuentoLeyDet> beanList;
	private SipreDescuentoLeyDet bean;

	private List<SipreDescuentoLey> beanDescuentoLeyList;

	public DescuentoLeyDetalleMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);

			ejbDescuentoLey = (DescuentoLeyEjbRemote) findServiceRemote(DescuentoLeyEjbRemote.class);
			beanDescuentoLeyList = ejbDescuentoLey
					.findAll(ConstantesUtil.LISTAR_EJB_REMOTE);

			ejb = (DescuentoLeyDetalleEjbRemote) findServiceRemote(DescuentoLeyDetalleEjbRemote.class);
			beanList = ejb.findAll(ConstantesUtil.LISTAR_EJB_REMOTE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreDescuentoLeyDet();
		SipreDescuentoLeyDetPK sipreDescuentoLeyDetPK = new SipreDescuentoLeyDetPK();
		bean.setSipreDescuentoLeyDetPK(sipreDescuentoLeyDetPK);
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

	public List<SipreDescuentoLeyDet> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreDescuentoLeyDet> beanList) {
		this.beanList = beanList;
	}

	public SipreDescuentoLeyDet getBean() {
		return bean;
	}

	public void setBean(SipreDescuentoLeyDet bean) {
		this.bean = bean;
	}

	public List<SipreDescuentoLey> getBeanDescuentoLeyList() {
		return beanDescuentoLeyList;
	}

	public void setBeanDescuentoLeyList(
			List<SipreDescuentoLey> beanDescuentoLeyList) {
		this.beanDescuentoLeyList = beanDescuentoLeyList;
	}

}