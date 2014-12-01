package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.IngresoOtroEjbRemote;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;

@Stateless
public class IngresoOtroEjbBean extends GenericDAOImpl<SipreIngresoOtro>
		implements IngresoOtroEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	
}
