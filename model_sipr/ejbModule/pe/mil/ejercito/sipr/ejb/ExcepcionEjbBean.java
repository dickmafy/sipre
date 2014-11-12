package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ExcepcionEjbRemote;
import pe.mil.ejercito.sipr.model.SipreExcepcion;

@Stateless
public class ExcepcionEjbBean extends
		GenericDAOImpl<SipreExcepcion> implements
		ExcepcionEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;	

	

	
}
