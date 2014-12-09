package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.CalculoQuintaCategoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCalculoQuintaCategoria;

@Stateless
public class CalculoQuintaCategoriaEjbBean extends GenericDAOImpl<SipreCalculoQuintaCategoria> implements CalculoQuintaCategoriaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	public void removeDelMes(String cplanillaMesProceso, Integer nplanillaNumProceso) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from SipreCalculoQuintaCategoria o ");
		sb.append(" where o.sipreCalculoQuintaCategoriaPK.cplanillaMesProceso=:cplanillaMesProceso");
		sb.append(" and o.sipreCalculoQuintaCategoriaPK.nplanillaNumProceso=:nplanillaNumProceso");
		Query q = em.createQuery(sb.toString());
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.executeUpdate();

	}

}
