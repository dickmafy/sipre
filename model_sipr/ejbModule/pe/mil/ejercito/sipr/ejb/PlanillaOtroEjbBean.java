package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaOtroEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;

@Stateless
public class PlanillaOtroEjbBean extends GenericDAOImpl<SiprePlanillaOtro> implements PlanillaOtroEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePlanillaOtro> procesarPlanillaOtrosList() {
		StringBuilder sb = new StringBuilder();
		// NATIVE QUERY (planilla-no otro(planilla x otro)otro-no planillax)
		sb.append(" SELECT B.* FROM SIPRE_PLANILLA A RIGHT JOIN SIPRE_PLANILLA_OTRO B ON A.CPERSONA_NRO_ADM = B.CPERSONA_NRO_ADM"
				+ " inner join SIPRE_PERSONA C ON C.CPERSONA_NRO_ADM = B.CPERSONA_NRO_ADM WHERE A.CPERSONA_NRO_ADM IS NULL"
				+ " and (B.CTP_CODIGO in ('60','61')) ");
		sb.append(" and C.DPERSONA_FEC_ING BETWEEN '01/01/14' AND '31/12/14'");

		Query q = em.createNativeQuery(sb.toString(), SiprePlanillaOtro.class);
		/*
		 * SQL parameter , usar : where id=? query.setParameter(1, "Bob");
		 * query.setParameter(2, "Smith");
		 */
		List<SiprePlanillaOtro> result = q.getResultList();
		return result;
	}

}