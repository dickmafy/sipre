package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreUsuario;

/**
 * Session Bean implementation class UsuarioEjbBean
 */
@Stateless
public class UsuarioEjbBean implements UsuarioEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UsuarioEjbBean() {

	}

	@Override
	public SipreUsuario getUsuario(UsuarioDto usuario) {
		SipreUsuario usrio = null;
		try {
			usrio = (SipreUsuario) em.createNamedQuery("SipreUsuario.validarUsuario")
					.setParameter("nickname", usuario.getNickname())
					.setParameter("clave", usuario.getClave())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usrio;
	}

}
