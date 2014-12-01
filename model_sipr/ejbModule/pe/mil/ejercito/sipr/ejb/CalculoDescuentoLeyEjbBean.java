package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.CalculoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLey;

@Stateless
public class CalculoDescuentoLeyEjbBean extends
		GenericDAOImpl<SipreCalculoDescuentoLey> implements
		CalculoDescuentoLeyEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	public void removeDelMes(String cplanillaMesProceso, Integer nplanillaNumProceso) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from SipreCalculoDescuentoLey o ");
		sb.append(" where o.sipreCalculoDescuentoLeyPK.cplanillaMesProceso=:cplanillaMesProceso");
		sb.append(" and o.sipreCalculoDescuentoLeyPK.nplanillaNumProceso=:nplanillaNumProceso");
		Query q = em.createQuery(sb.toString());
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.executeUpdate();

	}

}
