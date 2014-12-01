package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.CargoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCargo;

@Stateless
public class CargoEjbBean extends
		GenericDAOImpl<SipreCargo> implements
		CargoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

}
