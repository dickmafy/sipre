package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;






import org.hibernate.validator.internal.util.CollectionHelper;

import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.ejbremote.PlanillaAdicionalEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicionalPK;


@Stateless
public class PlanillaAdicionalEjbBean extends GenericDAOImpl<SiprePlanillaAdicional>
		implements PlanillaAdicionalEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	@Override
	public BigDecimal verificarSiYaSePago(SiprePlanillaAdicionalPK pk) {

		BigDecimal monto;

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT  o.npdMtoConcepto ");
		sb.append(" FROM SiprePlanillaAdicional o ");
		sb.append(" WHERE o.siprePlanillaAdicionalPK.cplanillaMesProceso=:cplanillaMesProceso  ");
		sb.append(" and o.siprePlanillaAdicionalPK.cpaMesAdicional=:cpaMesAdicional ");
		sb.append(" and o.siprePlanillaAdicionalPK.nplanillaNumProceso=:nplanillaNumProceso ");

		sb.append(" and o.siprePlanillaAdicionalPK.cpersonaNroAdm=:cpersonaNroAdm ");
		sb.append(" and o.siprePlanillaAdicionalPK.ctpCodigo=:ctpCodigo ");
		sb.append(" and o.siprePlanillaAdicionalPK.cciCodigo=:cciCodigo ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("cplanillaMesProceso", pk.getCplanillaMesProceso());
		q.setParameter("cpaMesAdicional", pk.getCpaMesAdicional());
		q.setParameter("nplanillaNumProceso", pk.getNplanillaNumProceso());

		q.setParameter("cpersonaNroAdm", pk.getCpersonaNroAdm());
		q.setParameter("ctpCodigo", pk.getCtpCodigo());
		q.setParameter("cciCodigo", pk.getCciCodigo());
		try {
			
			// validar nullpoint
			if(null!=q.getResultList() || q.getResultList().size()>0){
				monto = (BigDecimal)q.getResultList().get(0);	
			}else{
				monto = new BigDecimal(-1);
			}

			return monto;
		} catch (Exception e) {
			monto = new BigDecimal(-1);
		}
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
