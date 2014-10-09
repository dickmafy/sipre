package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class GrupoGradoEjbBean implements GrupoGradoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public GrupoGradoEjbBean() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreGrupoGrado> listGrupoGrado(SipreGrupoGrado sipreGrupoGrado) {
		List<SipreGrupoGrado> list = new ArrayList<SipreGrupoGrado>();
		try {
			list = (List<SipreGrupoGrado>) em.createNamedQuery("SipreGrupoGrado.findAll")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
