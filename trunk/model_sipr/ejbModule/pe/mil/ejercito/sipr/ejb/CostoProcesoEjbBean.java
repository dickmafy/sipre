package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.CalculoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLey;

@Stateless
public class CostoProcesoEjbBean extends
		GenericDAOImpl<SipreCalculoDescuentoLey> implements
		CalculoDescuentoLeyEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;
	

	
}
