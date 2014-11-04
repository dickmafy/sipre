package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ParametroEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreParametro;

@Stateless
public class ParametroEjbBean extends
		GenericDAOImpl<SipreParametro> implements
		ParametroEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

}
