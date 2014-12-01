package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PerfilEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePerfil;

@Stateless
public class PerfilEjbBean implements PerfilEjbRemote{

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePerfil> getPerfilList() {
		List<SiprePerfil> perfilLst=null;
		try{
			Query q = em.createNamedQuery("SiprePerfil.findAll");
			perfilLst = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return perfilLst;
	}

	@Override
	public Long eliminarPerfil(SiprePerfil perfil){
		Long ret = 1L;
		try{
			String sql = "delete from SiprePerfil s where s.codigoPerfil=:codigoPerfil";
			Query q = em.createQuery(sql);
			q.setParameter("codigoPerfil", perfil.getCodigoPerfil());
			q.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return -1L;
		}
		return ret;
	}
	
	@Override
	public SiprePerfil addEditPerfil(SiprePerfil perfil){
		try{
			if(perfil.getCodigoPerfil()==null){
				perfil.setCodigoPerfil(getMaxPerfil());
				em.persist(perfil);
			}else{
				em.merge(perfil);
			}
		}catch(Exception e){
			e.printStackTrace();
			return perfil;
		}
		return perfil;
	}
	
	public Long getMaxPerfil() {
		Long idretorno = null;
		try {
			String consulta = "select max(a.codigoPerfil) +1 from SiprePerfil a";
			Query q = em.createQuery(consulta);
			idretorno = (Long) q.getSingleResult();
		} catch (Exception e) {

		}
		return idretorno;
	}
	
	
}
