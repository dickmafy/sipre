package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

@ManagedBean(name = "grupoGradoMb")
@ViewScoped
public class GrupoGradoMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private GrupoGradoEjbRemote ejb;

	private List<SipreGrupoGrado> beanList;
	private SipreGrupoGrado bean;
	private SipreGrupoGrado beanSelected;
	

	public GrupoGradoMb() {
		super();
		try {
			ejb = (GrupoGradoEjbRemote) findServiceRemote(GrupoGradoEjbRemote.class);
			beanList = ejb.findAllSort(100, "cggCodigo");
			bean = new SipreGrupoGrado();
			beanSelected = new SipreGrupoGrado();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreGrupoGrado();
		beanSelected = new SipreGrupoGrado();
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

	public List<SipreGrupoGrado> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreGrupoGrado> beanList) {
		this.beanList = beanList;
	}

	public SipreGrupoGrado getBean() {
		return bean;
	}

	public void setBean(SipreGrupoGrado bean) {
		this.bean = bean;
	}

	public SipreGrupoGrado getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreGrupoGrado beanSelected) {
		this.beanSelected = beanSelected;
	}

}
