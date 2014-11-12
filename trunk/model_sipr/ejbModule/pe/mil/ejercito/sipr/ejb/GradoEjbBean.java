package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.GradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrado;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class GradoEjbBean  extends GenericDAOImpl<SipreGrado> implements GradoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public GradoEjbBean() {

	}

	

	

}
