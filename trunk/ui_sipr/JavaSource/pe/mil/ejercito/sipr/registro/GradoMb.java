package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;

@ManagedBean(name = "gradoMb")
@ViewScoped
public class GradoMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private GradoEjbRemote ejb;

	private List<SipreGrado> beanList;
	private SipreGrado bean;
	private SipreGrado beanSelected;
	

	public GradoMb() {
		super();
		try {
			ejb = (GradoEjbRemote) findServiceRemote(GradoEjbRemote.class);
			beanList = ejb.findAll();
			bean = new SipreGrado();
			beanSelected = new SipreGrado();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreGrado();
		beanSelected = new SipreGrado();
	}

	public void saveBean(ActionEvent event) {
		try {
			bean = ejb.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
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
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();

	}

	public List<SipreGrado> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreGrado> beanList) {
		this.beanList = beanList;
	}

	public SipreGrado getBean() {
		return bean;
	}

	public void setBean(SipreGrado bean) {
		this.bean = bean;
	}

	public SipreGrado getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreGrado beanSelected) {
		this.beanSelected = beanSelected;
	}

}
