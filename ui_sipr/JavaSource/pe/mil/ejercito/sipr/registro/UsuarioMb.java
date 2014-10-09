package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.log.Log;

import pe.mil.ejercito.sipr.commons.Faces;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "usuarioMb")
@ViewScoped
public class UsuarioMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejb;
	private List<SipreUsuario> beanList;

	private SipreUsuario beanSelected;
	private SipreUsuario beanUpdate = new SipreUsuario();
	private SipreUsuario bean = new SipreUsuario();

	private UsuarioDto beanSelected1;
	private UsuarioDto beanUpdate1;
	private UsuarioDto bean1;

	public UsuarioMb() {
		super();
		try {
			beanSelected1 = new UsuarioDto();
			beanUpdate1 = new UsuarioDto();
			bean1 = new UsuarioDto();

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
			beanSelected = ejb.insertUsuario(beanSelected);
			showMessage("Perfil editado satisfactoriamente", SEVERITY_INFO);
			//beanList.add(beanSelected);
		} else {
			beanSelected = ejb.insertUsuario(beanSelected);
			showMessage("Perfil insertado satisfactoriamente", SEVERITY_INFO);
		}
		beanList = ejb.listUsuario(null);
	}

	public void insert(SipreUsuario bean) {
		bean.setCusuarioEst("I");
		ejb.insertUsuario(bean);
		beanList = ejb.listUsuario(null);
	}

	public void buscar(SipreUsuario bean) {

		beanUpdate1.setSipreUsuario(bean);
		bean1.setSipreUsuario(ejb.getBean(bean));
		beanUpdate1.setSipreUsuario(bean);

	}

	public void buscar() {
		beanUpdate1.setSipreUsuario(bean);
		bean1.setSipreUsuario(ejb.getBean(bean));
		beanUpdate1.setSipreUsuario(bean);
	}

	public void update() {
		beanUpdate.setCusuarioEst("U");
		ejb.updateUsuario(beanUpdate);
		beanList = ejb.listUsuario(null);
	}

	public void disable(ActionEvent event) {
		try {
			beanSelected.setCusuarioEst("D");
			ejb.updateDeshabilitarUsuario(beanSelected);
			showMessage("Se elminio con exito.", SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage("Ocurrió un problema al intentar eliminar el perfil", SEVERITY_ERROR);

		}
		
		
	
		
		
	}

	public void irEditar() {
		Faces.redirecciona("/modules/seguridad/gestionar_usuario/usuario_update.xhtml");
	}

	public void irNuevo() {
		Faces.redirecciona("/seguridad/gestionar_usuario/usuario_new.xhtml");
	}

	/* Inicio de Atributos */
	public SipreUsuario getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(SipreUsuario beanSelected) {
		this.beanSelected = beanSelected;
	}

	public List<SipreUsuario> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreUsuario> beanList) {
		this.beanList = beanList;
	}

	public SipreUsuario getBeanUpdate() {
		return beanUpdate;
	}

	public void setBeanUpdate(SipreUsuario beanUpdate) {
		this.beanUpdate = beanUpdate;
	}

	public SipreUsuario getBean() {
		return bean;
	}

	public void setBean(SipreUsuario bean) {
		this.bean = bean;
	}

	public UsuarioDto getBeanSelected1() {
		return beanSelected1;
	}

	public void setBeanSelected1(UsuarioDto beanSelected1) {
		this.beanSelected1 = beanSelected1;
	}

	public UsuarioDto getBeanUpdate1() {
		return beanUpdate1;
	}

	public void setBeanUpdate1(UsuarioDto beanUpdate1) {
		this.beanUpdate1 = beanUpdate1;
	}

	public UsuarioDto getBean1() {
		return bean1;
	}

	public void setBean1(UsuarioDto bean1) {
		this.bean1 = bean1;
	}

}