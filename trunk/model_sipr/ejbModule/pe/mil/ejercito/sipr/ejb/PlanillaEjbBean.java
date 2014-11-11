package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanilla;

@Stateless
public class PlanillaEjbBean extends GenericDAOImpl<SiprePlanilla> implements PlanillaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

}
