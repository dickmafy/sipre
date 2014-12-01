package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.EscalaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEscala;

@Stateless
public class EscalaEjbBean extends
		GenericDAOImpl<SipreEscala> implements
		EscalaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	

	
}
