package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.IngresoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreIngresoGrado;
import pe.mil.ejercito.sipr.model.SipreIngresoGradoPK;

@Stateless
public class IngresoGradoEjbBean extends GenericDAOImpl<SipreIngresoGrado> implements IngresoGradoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

}
