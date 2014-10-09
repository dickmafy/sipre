package pe.mil.ejercito.sipr.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.AuditoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAuditoria;

@Stateless
public class AuditoriaEjbBean implements AuditoriaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreAuditoria> getAuditoriaList() {
		List<SipreAuditoria> audList = null;
		try {
			Query q = em.createNamedQuery("SipreAuditoria.findAll");
			audList = (List<SipreAuditoria>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SipreAuditoria> getAuditoriaList(String accion, String tabla,
			String resultado) {
		List<SipreAuditoria> audList = null;
		try {
			String consulta = "select a from SipreAuditoria a where (a.audAccion=:accion or a.audTabla=:tabla) or a.audResultadoAccion=:resultado";
			if (accion == null || accion.length() == 0)
				if (tabla == null || tabla.length() == 0)
					if (resultado == null || resultado.length() == 0)
						consulta = "SELECT s FROM SipreAuditoria s";
			Query q = em.createQuery(consulta);
			q.setParameter("accion", accion);
			q.setParameter("tabla", tabla);
			q.setParameter("resultado", resultado);
			audList = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audList;
	}
	
	@Override
	public SipreAuditoria persist(SipreAuditoria s){
		s.setAudCodigo(getMaxAuditoria());
		em.persist(s);
		return s;
	}

	public Long getMaxAuditoria() {
		Long idretorno = null;
		try {
			String consulta = "select max(a.audCodigo) +1 from SipreAuditoria a";
			Query q = em.createQuery(consulta);
			idretorno = (Long) q.getSingleResult();
		} catch (Exception e) {

		}
		return idretorno;
	}
}
