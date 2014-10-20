package pe.mil.ejercito.sipr.seguridad;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.PerfilEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePerfil;

@SuppressWarnings("serial")
@ManagedBean(name = "perfilMb")
@ViewScoped
public class Perfil extends MainContext {
	private List<SiprePerfil> perfilLst;
	private SiprePerfil perfilSelected;
	
	PerfilEjbRemote ejbPerfil;
	
	public Perfil(){
		super();
		ejbPerfil = (PerfilEjbRemote)findServiceRemote(PerfilEjbRemote.class);
		perfilLst = ejbPerfil.getPerfilList();
	}
	
	public String eliminarPerfil(){
		
		if(perfilSelected.getCodigoPerfil().compareTo(1L)==0){
			showMessage("No se puede eliminar el perfil Administrador", SEVERITY_WARN);
		}else{
			if(ejbPerfil.eliminarPerfil(perfilSelected).compareTo(1L)==0){
				showMessage("Eliminacion satisfactoria", SEVERITY_INFO);
				ejbAuditoria.persist(getAuditoria("PERFIL", new Date(),
						"ELIMINACION", null, null,
						"ELIMINACION SATISFACTORIA", getSessionUser().getCusuarioCodigo()));
				perfilLst.remove(perfilSelected);
			}else{
				showMessage("Ocurrió un problema al intentar eliminar el perfil", SEVERITY_ERROR);
				
			}
		}
		
		return "";
	}
	
	public void editarAgregarPerfil(ActionEvent event){
		if(perfilSelected.getCodigoPerfil()!=null){
			perfilSelected = ejbPerfil.addEditPerfil(perfilSelected);
			showMessage("Perfil editado satisfactoriamente", SEVERITY_INFO);
			ejbAuditoria.persist(getAuditoria("PERFIL", new Date(),
					"MODIFICACION", perfilSelected.getNombrePerfil(), perfilSelected.getNombrePerfil(),
					"MODIFICACION SATISFACTORIA", getSessionUser().getCusuarioCodigo()));
		}else{
			perfilSelected = ejbPerfil.addEditPerfil(perfilSelected);
			showMessage("Nuevo perfil creado satisfactoriamente", SEVERITY_INFO);
			ejbAuditoria.persist(getAuditoria("PERFIL", new Date(),
					"ELIMINACION", null, null,
					"ELIMINACION SATISFACTORIA", getSessionUser().getCusuarioCodigo()));
			ejbAuditoria.persist(getAuditoria("PERFIL", new Date(),
					"REGISTRO", perfilSelected.getNombrePerfil(), null,
					"REGISTRO SATISFACTORIO", getSessionUser().getCusuarioCodigo()));
			perfilLst.add(perfilSelected);	
			
		}
	}
	
	public void nuevoPerfil(ActionEvent event){
		perfilSelected = new SiprePerfil();
	}

	public List<SiprePerfil> getPerfilLst() {
		return perfilLst;
	}

	public void setPerfilLst(List<SiprePerfil> perfilLst) {
		this.perfilLst = perfilLst;
	}

	public SiprePerfil getPerfilSelected() {
		return perfilSelected;
	}

	public void setPerfilSelected(SiprePerfil perfilSelected) {
		this.perfilSelected = perfilSelected;
	}
	
	
}
