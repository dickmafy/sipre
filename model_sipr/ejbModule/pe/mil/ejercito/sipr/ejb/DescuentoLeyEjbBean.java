package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;

@Stateless
public class DescuentoLeyEjbBean extends GenericDAOImpl<SipreDescuentoLey>
		implements DescuentoLeyEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

}
