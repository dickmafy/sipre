package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "usuarioMb")
@ViewScoped
public class UsuarioMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejb;

	private List<SipreUsuario> beanList;
	private SipreUsuario beanSelected;
	

	public UsuarioMb() {
		super();
		try {
			ejb = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			beanList = ejb.listUsuario(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void nuevoUsuario(ActionEvent event) {
		beanSelected = new SipreUsuario();
	}

	public void insert(ActionEvent event) {
		
		if (beanSelected.getCusuarioCodigo() != null) {
			beanSelected.setCusuarioEst("U");
			beanSelected = ejb.insertUsuario(beanSelected);
			showMessage("Perfil editado satisfactoriamente", SEVERITY_INFO);
			//beanList.add(beanSelected);
		} else {
			beanSelected.setCusuarioEst("I");
			beanSelected = ejb.insertUsuario(beanSelected);
			showMessage("Perfil insertado satisfactoriamente", SEVERITY_INFO);
		}
		beanList = ejb.listUsuario(null);
	}




	public void disable(ActionEvent event) {
		try {
			beanSelected.setCusuarioEst("D");
			ejb.updateDeshabilitarUsuario(beanSelected);
			showMessage("Se elminio con exito.", SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage("Ocurri� un problema al intentar eliminar el perfil", SEVERITY_ERROR);

		}
		
		
	
		
		
	}

	public List<SipreUsuario> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreUsuario> beanList) {
		this.beanList = beanList;
	}

	public SipreUsuario getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreUsuario beanSelected) {
		this.beanSelected = beanSelected;
	}

}