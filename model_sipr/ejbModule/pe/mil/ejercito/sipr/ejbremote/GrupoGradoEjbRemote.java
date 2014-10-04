package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface GrupoGradoEjbRemote {
	
	List<SipreGrupoGrado> listGrupoGrado(SipreGrupoGrado sipreGrupoGrado);

	

	
}
