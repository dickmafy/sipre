package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ParametroDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreParametroDetalle;

@Stateless
public class ParametroDetalleEjbBean extends
		GenericDAOImpl<SipreParametroDetalle> implements
		ParametroDetalleEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

}
