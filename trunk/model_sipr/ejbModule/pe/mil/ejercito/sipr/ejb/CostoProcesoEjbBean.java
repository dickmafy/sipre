package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.CostoProcesoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCostoProceso;

@Stateless
public class CostoProcesoEjbBean extends
		GenericDAOImpl<SipreCostoProceso> implements
		CostoProcesoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

}
