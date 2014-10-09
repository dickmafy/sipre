package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

@ManagedBean(name="grupoGrado")
@ViewScoped
public class GrupoGrado extends MainContext implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<SipreGrupoGrado> list;
	private SipreGrupoGrado beanSelected;
	private GrupoGradoEjbRemote ejb;
	
	public GrupoGrado(){
		super();
		try {
			ejb =  (GrupoGradoEjbRemote) findServiceRemote(GrupoGradoEjbRemote.class);
			list= ejb.listGrupoGrado(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
	
	public List<SipreGrupoGrado> getList() {
		return list;
	}

	public void setList(List<SipreGrupoGrado> list) {
		this.list = list;
	}

	public SipreGrupoGrado getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreGrupoGrado beanSelected) {
		this.beanSelected = beanSelected;
	}

}
