package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;

@ManagedBean(name = "descuentoIngresoMb")
@ViewScoped
public class DescuentoIngresoMb extends MainContext implements Serializable {

	private static final long				serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote				ejbUsuario;
	private DescuentoIngresoEjbRemote		ejb;
	private SipreDescuentoIngreso			bean;
	private List<SipreDescuentoIngreso>			beanList;
	private SipreDescuentoIngreso			beanSelected;

	// primer fk
	private SipreConceptoIngreso			beanConceptoIngreso;
	private List<SipreConceptoIngreso>		beanConceptoIngresoList;
	private ConceptoIngresoEjbRemote		ejbConceptoIngreso;

	// segundo FK
	private ConceptoDescuentoEjbRemote		ejbConceptoDescuento;
	private List<SipreConceptoDescuento>	beanConceptoDescuentoList;
	private SipreConceptoDescuento			beanConceptoDescuento;

	public DescuentoIngresoMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb = (DescuentoIngresoEjbRemote) findServiceRemote(DescuentoIngresoEjbRemote.class);
			ejbConceptoIngreso = (ConceptoIngresoEjbRemote) findServiceRemote(ConceptoIngresoEjbRemote.class);
			ejbConceptoDescuento = (ConceptoDescuentoEjbRemote) findServiceRemote(ConceptoDescuentoEjbRemote.class);

			beanConceptoIngresoList = ejbConceptoIngreso.findAll();
			beanConceptoDescuentoList = ejbConceptoDescuento.findAll();
			beanList = ejb.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cleanBean() {
		bean = new SipreDescuentoIngreso();

	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void saveBean(ActionEvent event) {
		try {
			ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
			beanList = ejb.findAll();
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL, SEVERITY_ERROR);
		}
		
	}
	
	public void deleteBean(ActionEvent event) {
		try {
			ejb.remove(beanSelected);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA, SEVERITY_INFO);
			beanList = ejb.findAll();
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL, SEVERITY_ERROR);
		}
		
	}

	public SipreDescuentoIngreso getBean() {
		return bean;
	}

	public void setBean(SipreDescuentoIngreso bean) {
		this.bean = bean;
	}

	public SipreConceptoIngreso getBeanConceptoIngreso() {
		return beanConceptoIngreso;
	}

	public void setBeanConceptoIngreso(SipreConceptoIngreso beanConceptoIngreso) {
		this.beanConceptoIngreso = beanConceptoIngreso;
	}

	public List<SipreConceptoIngreso> getBeanConceptoIngresoList() {
		return beanConceptoIngresoList;
	}

	public void setBeanConceptoIngresoList(List<SipreConceptoIngreso> beanConceptoIngresoList) {
		this.beanConceptoIngresoList = beanConceptoIngresoList;
	}

	public List<SipreConceptoDescuento> getBeanConceptoDescuentoList() {
		return beanConceptoDescuentoList;
	}

	public void setBeanConceptoDescuentoList(List<SipreConceptoDescuento> beanConceptoDescuentoList) {
		this.beanConceptoDescuentoList = beanConceptoDescuentoList;
	}

	public SipreConceptoDescuento getBeanConceptoDescuento() {
		return beanConceptoDescuento;
	}

	public void setBeanConceptoDescuento(SipreConceptoDescuento beanConceptoDescuento) {
		this.beanConceptoDescuento = beanConceptoDescuento;
	}

	public SipreDescuentoIngreso getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreDescuentoIngreso beanSelected) {
		this.beanSelected = beanSelected;
	}

	public List<SipreDescuentoIngreso> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreDescuentoIngreso> beanList) {
		this.beanList = beanList;
	}

}