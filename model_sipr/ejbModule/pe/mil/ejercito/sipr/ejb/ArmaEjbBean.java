package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ArmaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreArma;

@Stateless
public class ArmaEjbBean extends
		GenericDAOImpl<SipreArma> implements
		ArmaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	

	
}
