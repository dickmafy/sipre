package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

@Remote
public interface GrupoGradoEjbRemote extends GenericDAO<SipreGrupoGrado>{
	
	

	
}
