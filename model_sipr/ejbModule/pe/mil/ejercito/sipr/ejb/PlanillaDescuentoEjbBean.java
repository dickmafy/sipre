package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDescuentoEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaDescuento;
 
@Stateless
public class PlanillaDescuentoEjbBean extends GenericDAOImpl<SiprePlanillaDescuento>
		implements PlanillaDescuentoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
