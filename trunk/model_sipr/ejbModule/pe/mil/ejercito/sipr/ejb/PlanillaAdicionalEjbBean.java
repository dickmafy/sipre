package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;

@Stateless
public class PlanillaAdicionalEjbBean extends GenericDAOImpl<SiprePlanillaAdicional>
		implements PlanillaAdicionalEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	@Override
	public BigDecimal verificarSiYaSePago(String cpersonaNroAdm, String ctgMesGuardia) {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT  o.npdMtoConcepto ");
		sb.append(" FROM SiprePlanillaAdicional o ");
		sb.append(" WHERE o.siprePlanillaAdicionalPK.cplanillaMesProceso=:ctgMesGuardia  ");
		sb.append(" and o.siprePlanillaAdicionalPK.cpersonaNroAdm=:cpersonaNroAdm ");
		Query q = em.createQuery(sb.toString());
		q.setParameter("cpersonaNroAdm", cpersonaNroAdm);
		q.setParameter("ctgMesGuardia", ctgMesGuardia);
		List list = q.getResultList();
		BigDecimal monto = (BigDecimal) list.get(0);
		return monto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBy(String ctpCodigo, Integer nplanillaNumProceso, String cplanillaMesProceso) {
		List<Object[]> list;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT o.siprePlanillaAdicionalPK.ctpCodigo ");
		sb.append(" , o.siprePlanillaAdicionalPK.cpersonaNroAdm ");
		sb.append(" , o.siprePlanillaAdicionalPK.cplanillaMesProceso ");
		sb.append(" , o.siprePlanillaAdicionalPK.nplanillaNumProceso ");
		sb.append(" , SUM(o.npdMtoConcepto) ");
		sb.append(" FROM SiprePlanillaAdicional o ");

		sb.append(" WHERE  o.siprePlanillaAdicionalPK.nplanillaNumProceso=:nplanillaNumProceso  ");
		sb.append(" and  o.siprePlanillaAdicionalPK.cplanillaMesProceso=:cplanillaMesProceso  ");

		sb.append(" GROUP BY o.siprePlanillaAdicionalPK.ctpCodigo ");
		sb.append(" ,o.siprePlanillaAdicionalPK.cpersonaNroAdm ");
		sb.append(" ,o.siprePlanillaAdicionalPK.cplanillaMesProceso ");
		sb.append(" ,o.siprePlanillaAdicionalPK.nplanillaNumProceso ");
		Query q = em.createQuery(sb.toString());
		// situacion administrativa
		// q.setParameter("ctpCodigo", ctpCodigo); /
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		list = q.getResultList();
		return list;
	}

}
