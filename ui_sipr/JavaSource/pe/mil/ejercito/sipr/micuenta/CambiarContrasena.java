package pe.mil.ejercito.sipr.micuenta;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.Encripta;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;

@ManagedBean(name="cambiarContrasena")
@ViewScoped
public class CambiarContrasena extends MainContext {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrasenaActual;
	private String contrasenaNueva;
	private String confirmarContrasena;
	
	private UsuarioEjbRemote usrioEjb;
	
	public CambiarContrasena(){
		super();
		usrioEjb = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
	}

	public String getContrasenaActual() {
		return contrasenaActual;
	}

	public void setContrasenaActual(String contrasenaActual) {
		this.contrasenaActual = contrasenaActual;
	}

	public String getContrasenaNueva() {
		return contrasenaNueva;
	}

	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}

	public String getConfirmarContrasena() {
		return confirmarContrasena;
	}

	public void setConfirmarContrasena(String confirmarContrasena) {
		this.confirmarContrasena = confirmarContrasena;
	}
	
	
	public String cambiarContrasena(){
		String passwdEnc = Encripta.encripta(contrasenaActual, Encripta.HASH_SHA1);
		if(passwdEnc.equals(getSessionUser().getVusuarioPass())){
			if(contrasenaNueva.equals(confirmarContrasena)){
				usrioEjb.cambiarContrasena(Encripta.encripta(contrasenaNueva, Encripta.HASH_SHA1), getSessionUser().getCusuarioCodigo());
				showMessage("Contraseña cambiada con éxito", SEVERITY_INFO);
			}else{
				showMessage("Contraseñas no son identicas", SEVERITY_ERROR);
			}
		}else{
			showMessage("Contraseña no coincide con la actual", SEVERITY_ERROR);
		}
		return "";
	}
}
