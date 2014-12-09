package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;

@Stateless
public class PlanillaDetalleEjbBean extends GenericDAOImpl<SiprePlanillaDetalle> implements PlanillaDetalleEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	public BigDecimal getSueldoPorPersona(String cpersonaNroAdm, Integer nplanillaNumProceso, String cplanillaMesProceso) {

		StringBuilder sb = new StringBuilder();
		/*sb.append(" SELECT o.siprePlanillaDetallePK.ctpCodigo ");
		sb.append(" , o.siprePlanillaDetallePK.cpersonaNroAdm ");
		sb.append(" , o.siprePlanillaDetallePK.cplanillaMesProceso ");
		sb.append(" , o.siprePlanillaDetallePK.nplanillaNumProceso ");
		*/
		sb.append(" SELECT ");
		sb.append(" SUM(o.npdMtoConcepto) ");
		sb.append(" FROM SiprePlanillaDetalle o ");

		sb.append(" WHERE  o.siprePlanillaDetallePK.nplanillaNumProceso=:nplanillaNumProceso  ");
		sb.append(" and  o.siprePlanillaDetallePK.cplanillaMesProceso=:cplanillaMesProceso  ");
		sb.append(" and  o.siprePlanillaDetallePK.ctpCodigo=:ctpCodigo ");
		sb.append(" and  o.siprePlanillaDetallePK.cpersonaNroAdm=:cpersonaNroAdm ");

		sb.append(" GROUP BY o.siprePlanillaDetallePK.ctpCodigo ");
		sb.append(" ,o.siprePlanillaDetallePK.cpersonaNroAdm ");
		sb.append(" ,o.siprePlanillaDetallePK.cplanillaMesProceso ");
		sb.append(" ,o.siprePlanillaDetallePK.nplanillaNumProceso ");
		Query q = em.createQuery(sb.toString());
		// situacion administrativa
		// q.setParameter("ctpCodigo", ctpCodigo); /
		q.setParameter("cpersonaNroAdm", cpersonaNroAdm);
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		q.setParameter("ctpCodigo", "01");
		q.getResultList();
		BigDecimal resultado = (BigDecimal) q.getSingleResult();
		return resultado;
	}

}
