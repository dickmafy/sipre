package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.EstadoCivilEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEstadoCivil;
@Stateless
public class EstadoCivilEjbBean extends
		GenericDAOImpl<SipreEstadoCivil> implements
		EstadoCivilEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;
	

	
}
