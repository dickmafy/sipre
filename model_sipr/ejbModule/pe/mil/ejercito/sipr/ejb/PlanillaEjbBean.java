package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanilla;

@Stateless
public class PlanillaEjbBean extends GenericDAOImpl<SiprePlanilla> implements PlanillaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	
	@Override
	public Long siPersonaExisteEnPlanillaPrincipal(String cip) {
		Query q = em.createQuery("Select COUNT(o.id.cpersonaNroAdm) from SiprePlanilla o where o.id.cpersonaNroAdm=:cip");
		q.setParameter("cip", cip);
		Long count = (Long)q.getSingleResult();
		return count;
		
	}

	
	
}
