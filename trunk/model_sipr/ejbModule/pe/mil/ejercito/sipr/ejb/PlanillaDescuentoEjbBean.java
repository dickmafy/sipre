package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDescuentoEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanillaDescuento;
 
@Stateless
public class PlanillaDescuentoEjbBean extends GenericDAOImpl<SiprePlanillaDescuento>
		implements PlanillaDescuentoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	@Override
	public List<SiprePlanillaDescuento> getListPlanillaDescuento(String mesproceso,Integer tipo) {
		try{
			StringBuilder sb=new StringBuilder();
			sb.append("Select p from SiprePlanillaDescuento p where 1=1 ");
			if(mesproceso!=null && !mesproceso.isEmpty()){
				sb.append(" and p.siprePlanillaDescuentoPK.cplanillaMesProceso =:mes ");
			}
			if(tipo == 1){//ley judical
				sb.append(" and p.sipreEntidadCrediticia.cecIndClas in (1,2) ");
			}else if(tipo == 2){//todas
				sb.append(" and p.sipreEntidadCrediticia.cecIndClas in (3,4) ");
			}
			
			sb.append(" order by p.sipreEntidadCrediticia.cecIndClas,p.sipreEntidadCrediticia.cecIndPrio ");
			
			Query q=em.createQuery(sb.toString());
			if(mesproceso!=null && !mesproceso.isEmpty()){
				q.setParameter("mes",mesproceso.trim());
			}
			
			
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

	
}
