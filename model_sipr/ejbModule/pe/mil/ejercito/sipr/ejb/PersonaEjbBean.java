package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePersona;

@Stateless
public class PersonaEjbBean extends GenericDAOImpl<SiprePersona> implements PersonaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<SiprePersona> findAllBySituacionAdministrativaYActividad() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o FROM SiprePersona o ");
		sb.append(" where o.sipreSituacionAdm.csaCodigo=:csaCodigo");
		//sb.append(" and o.cpersonaNroAdm ='123652000'");
		Query q = em.createQuery(sb.toString());
		// situacion administrativa
		q.setParameter("csaCodigo", "01");
		return q.getResultList();
	}

	@Override
	public List<SiprePersona> findAllBySituacionAdministrativaDeListaRevista() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o FROM SiprePersona o ");
		sb.append(" where o.sipreSituacionAdm.csaCodigo=:p1");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p2");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p3");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p4");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p5");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p6");
		sb.append(" or o.sipreSituacionAdm.csaCodigo=:p7");
		Query q = em.createQuery(sb.toString());
		// //SQL parameter in :=parametros
		// List<String> parametros = Arrays.asList("60", "61");
		// q.setParameter("ctpCodigo", parametros);

		// situacion administrativa +01
		q.setParameter("p1", "01");
		q.setParameter("p2", "60");
		q.setParameter("p3", "57");
		q.setParameter("p4", "61");
		q.setParameter("p5", "50");
		q.setParameter("p6", "71");
		q.setParameter("p7", "77");
		return q.getResultList();
	}
	
	@Override
	public Integer updatePersonaHijos(String cip,String numeroHijo) {
		Query q = em.createQuery("UPDATE SiprePersona SET npersonaNroHijo=:numeroHijo where cpersonaNroAdm=:cip");
		q.setParameter("cip", cip);
		q.setParameter("numeroHijo", numeroHijo);
		return q.executeUpdate();
		
	}

}
