package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyPersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyPersona;

@Stateless
public class DescuentoLeyPersonaEjbBean extends GenericDAOImpl<SipreDescuentoLeyPersona>
		implements DescuentoLeyPersonaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	public List<SipreDescuentoLeyPersona> findByCip(String cip) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o FROM SipreDescuentoLeyPersona o ");
		sb.append(" where o.sipreDescuentoLeyPersonaPK.cpersonaNroAdm=:cip");
		Query q = em.createQuery(sb.toString());
		q.setParameter("cip", cip);
		return q.getResultList();

	}

}
