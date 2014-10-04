package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface UsuarioEjbRemote {
	/**
	 * Retorna el objeto usuario si el usuario y contraseņa es correcta, caso contrario retorna un objeto nulo
	 * @param usuario
	 * @return El objeto Usrio
	 */
		SipreUsuario getUsuario(UsuarioDto usuario); 
	

	
}
