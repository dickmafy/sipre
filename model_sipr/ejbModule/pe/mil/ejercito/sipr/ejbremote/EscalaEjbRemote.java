package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreEscala;

@Remote
public interface EscalaEjbRemote extends GenericDAO<SipreEscala> {
	

	

	
}
