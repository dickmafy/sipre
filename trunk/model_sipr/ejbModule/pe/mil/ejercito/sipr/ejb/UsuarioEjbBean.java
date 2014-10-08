package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
			usrio = (SipreUsuario) em
					.createNamedQuery("SipreUsuario.validarUsuario")
					.setParameter("nickname", usuario.getNickname())
					.setParameter("clave", usuario.getClave())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usrio;
	}

	@Override
	public Integer cambiarContrasena(String contrasena, String idUsuario) {
		try {
			String sql = "update SipreUsuario s set s.vusuarioPass=:vusuarioPass where s.cusuarioCodigo=:cusuarioCodigo";
			Query q = em.createQuery(sql);
			q.setParameter("vusuarioPass", contrasena);
			q.setParameter("cusuarioCodigo", idUsuario);
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

}
