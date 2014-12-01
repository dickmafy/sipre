package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ConceptoIngresoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class ConceptoIngresoEjbBean extends GenericDAOImpl<SipreConceptoIngreso>
		implements ConceptoIngresoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

}
