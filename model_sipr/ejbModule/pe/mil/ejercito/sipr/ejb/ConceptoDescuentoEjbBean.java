package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;

@Stateless
public class ConceptoDescuentoEjbBean extends
		GenericDAOImpl<SipreConceptoDescuento> implements
		ConceptoDescuentoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

}
