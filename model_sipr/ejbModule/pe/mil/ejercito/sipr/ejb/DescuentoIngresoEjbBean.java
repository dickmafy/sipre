package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.DescuentoIngresoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;

@Stateless
public class DescuentoIngresoEjbBean extends
		GenericDAOImpl<SipreDescuentoIngreso> implements
		DescuentoIngresoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;
}
