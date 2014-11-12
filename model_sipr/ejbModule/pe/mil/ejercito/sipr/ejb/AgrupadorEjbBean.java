package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.AgrupadorEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAgrupador;

@Stateless
public class AgrupadorEjbBean extends
		GenericDAOImpl<SipreAgrupador> implements
		AgrupadorEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

}
