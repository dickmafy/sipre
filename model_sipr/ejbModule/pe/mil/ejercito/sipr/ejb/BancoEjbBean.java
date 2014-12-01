package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.BancoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBanco;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class BancoEjbBean extends GenericDAOImpl<SipreBanco>
		implements BancoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	
}
