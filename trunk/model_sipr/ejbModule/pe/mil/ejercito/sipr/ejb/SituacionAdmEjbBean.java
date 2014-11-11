package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.SituacionAdmEjbRemote;
import pe.mil.ejercito.sipr.model.SipreSituacionAdm;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class SituacionAdmEjbBean extends GenericDAOImpl<SipreSituacionAdm> implements SituacionAdmEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

}
