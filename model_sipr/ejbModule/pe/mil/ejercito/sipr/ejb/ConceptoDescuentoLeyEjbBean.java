package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLey;

@Stateless
public class ConceptoDescuentoLeyEjbBean extends
		GenericDAOImpl<SipreConceptoDescuentoLey> implements
		ConceptoDescuentoLeyEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

}
