package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.TmpPersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpPersona;


@Stateless
public class TmpPersonaEjbBean extends GenericDAOImpl<SipreTmpPersona> implements TmpPersonaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	@Override
	public List<SipreTmpPersona> findByGrado(String csaCodigo) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o FROM SipreTmpPersona o ");
		sb.append(" where o.ctpCodSitAdm=:csaCodigo");
		Query q = em.createQuery(sb.toString());
		q.setParameter("csaCodigo", csaCodigo);
		return q.getResultList();

	}
	

	

}
