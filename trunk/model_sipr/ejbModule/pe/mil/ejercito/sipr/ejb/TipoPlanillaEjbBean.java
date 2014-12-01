package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TipoPlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class TipoPlanillaEjbBean extends GenericDAOImpl<SipreTipoPlanilla>
		implements TipoPlanillaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	
}
