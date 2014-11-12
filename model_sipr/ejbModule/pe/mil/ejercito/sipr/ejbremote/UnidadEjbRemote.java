package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreUnidad;

@Remote
public interface UnidadEjbRemote extends GenericDAO<SipreUnidad> {
	

	

	
}
