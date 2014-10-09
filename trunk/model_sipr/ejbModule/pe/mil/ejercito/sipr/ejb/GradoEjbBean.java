package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class GradoEjbBean implements GradoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public GradoEjbBean() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreGrado> listGrado(SipreGrado sipreGrado) {
		List<SipreGrado> list = new ArrayList<SipreGrado>();
		try {
			list = (List<SipreGrado>) em.createNamedQuery("SipreGrado.findAll")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
