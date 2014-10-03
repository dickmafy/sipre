package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface UsuarioEjbRemote {
	/**
	 * Retorna el objeto usuario si el usuario y contrase�a es correcta, caso contrario retorna un objeto nulo
	 * @param usuario
	 * @return El objeto Usrio
	 */
	 SipreUsuario getUsuario(UsuarioDto usuario);
}
