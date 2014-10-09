package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.log.Log;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "reintegroPersonalMb")
@ViewScoped
public class ReintegroPersonalMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SipreTmpBonificacion> beanList;
	private SipreTmpBonificacion beanSelected;
	private ReintegroPersonalEjbRemote ejb;
	private SipreTmpBonificacion bean;

	public ReintegroPersonalMb() {
		super();
		try {
			ejb = (ReintegroPersonalEjbRemote) findServiceRemote(ReintegroPersonalEjbRemote.class);
			beanList = ejb.listReintegroPersonal(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<SipreTmpBonificacion> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreTmpBonificacion> beanList) {
		this.beanList = beanList;
	}

	public SipreTmpBonificacion getBean() {
		return bean;
	}

	public void setBean(SipreTmpBonificacion bean) {
		this.bean = bean;
	}

	public void setBeanSelected(SipreTmpBonificacion beanSelected) {
		this.beanSelected = beanSelected;
	}

	public SipreTmpBonificacion getBeanSelected() {
		return beanSelected;
	}

}
