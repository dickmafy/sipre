package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;

@Stateless
public class PlanillaDetalleEjbBean extends GenericDAOImpl<SiprePlanillaDetalle>
		implements PlanillaDetalleEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

}
