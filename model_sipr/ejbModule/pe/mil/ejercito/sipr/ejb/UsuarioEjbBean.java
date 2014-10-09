package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreUsuario> listUsuario(SipreUsuario usuario) {
		List<SipreUsuario> list = null;
		try {
			list = (List<SipreUsuario> ) em.createNamedQuery("SipreUsuario.findAll").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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

	
	public String getMax() {
		String idretorno = null;
		try {
			String consulta = "select max(a.cusuarioCodigo = :cusuarioCodigo) +1 from SipreUsuario a";
			Query q = em.createQuery(consulta);
			idretorno = (String) q.getSingleResult();
		} catch (Exception e) {

		}
		return idretorno;
	}
	
	@Override
	public SipreUsuario insertUsuario(SipreUsuario usuario) {
		try{
			if(usuario.getCusuarioCodigo()==null){
				usuario.setCusuarioCodigo(getMax());
				em.persist(usuario);
			}else{
				em.merge(usuario);
			}
		}catch(Exception e){
			e.printStackTrace();
			return usuario;
		}
		return usuario;

	}

	@Override
	public Boolean updateUsuario(SipreUsuario usuario) {
		try {
			String sql = "update SipreUsuario s set "
					+ " s.cusuarioEst=:cusuarioEst, "
					+ " dusuarioFecReg=:dusuarioFecReg,"
					+ " vusuarioNom=:vusuarioNom,"
					+ " vusuarioPass=:vusuarioPass "
					+ " where s.cusuarioCodigo=:cusuarioCodigo";
			Query q = em.createQuery(sql);
			q.setParameter("cusuarioEst", usuario.getCusuarioEst());
			q.setParameter("dusuarioFecReg", usuario.getDusuarioFecReg());
			q.setParameter("vusuarioNom", usuario.getVusuarioNom());
			q.setParameter("vusuarioPass", usuario.getVusuarioPass());
			q.setParameter("cusuarioCodigo", usuario.getCusuarioCodigo());
			
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateDeshabilitarUsuario(SipreUsuario usuario) {
		try {
			String sql = "update SipreUsuario s set s.cusuarioEst=:cusuarioEst where s.cusuarioCodigo=:cusuarioCodigo";
			Query q = em.createQuery(sql);
			q.setParameter("cusuarioEst", usuario.getCusuarioEst());
			q.setParameter("cusuarioCodigo", usuario.getCusuarioCodigo());
			
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SipreUsuario getBean(SipreUsuario usuario) {
		SipreUsuario usrio = null;
		try {
			usrio = (SipreUsuario) em
					.createNamedQuery("SipreUsuario.findByCusuarioCodigo")
					.setParameter("cusuarioCodigo", usuario.getCusuarioCodigo())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usrio;
	}

	

}
