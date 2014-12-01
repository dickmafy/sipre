package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.VerificarCodigoBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class VerificarCodigoBancoEjbBean extends GenericDAOImpl<SipreTmpBanco>
		implements VerificarCodigoBancoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreTmpBanco> listBean(SipreTmpBanco bean) {
		List<SipreTmpBanco> list = null;
		try {
			list = (List<SipreTmpBanco>) em.createNamedQuery(
					"SipreTmpBanco.findAll").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
