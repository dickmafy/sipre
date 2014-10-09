package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface UsuarioEjbRemote {
	/**
	 * Retorna el objeto usuario si el usuario y contraseña es correcta, caso contrario retorna un objeto nulo
	 * @param usuario
	 * @return El objeto Usrio
	 */
		SipreUsuario getUsuario(UsuarioDto usuario); 
		
		/**
		 * Retorna la lista de usuarios
		 * @param usuario
		 * @return 
		 */
		List<SipreUsuario> listUsuario(SipreUsuario usuario);
		
		
		/**
		 * inserta un usuario
		 * @param usuario
		 * @return 
		 */
		SipreUsuario insertUsuario(SipreUsuario usuario);
		
		/**
		 * update un usuario
		 * @param usuario
		 * @return 
		 */
		Boolean updateUsuario(SipreUsuario usuario);
		
		/**
		 * deshabilitar un usuario
		 * @param usuario
		 * @return 
		 */
		Boolean updateDeshabilitarUsuario(SipreUsuario usuario);
		
		/**
		 * buscar un usuario
		 * @param usuario
		 * @return 
		 */
		SipreUsuario getBean(SipreUsuario usuario);
	
		Integer cambiarContrasena(String contrasena, String idUsuario);
	
}
