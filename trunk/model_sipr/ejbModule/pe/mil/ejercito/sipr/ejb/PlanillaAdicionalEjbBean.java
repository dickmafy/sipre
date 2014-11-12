package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;

@Stateless
public class PlanillaAdicionalEjbBean extends GenericDAOImpl<SiprePlanillaAdicional>
		implements PlanillaAdicionalEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
