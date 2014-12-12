package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePlanilla;

@Stateless
public class PlanillaEjbBean extends GenericDAOImpl<SiprePlanilla> implements PlanillaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	
	@Override
	public Long siPersonaExisteEnPlanillaPrincipal(String cip) {
		Query q = em.createQuery("Select COUNT(o.id.cpersonaNroAdm) from SiprePlanilla o where o.id.cpersonaNroAdm=:cip");
		q.setParameter("cip", cip);
		Long count = (Long)q.getSingleResult();
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePlanilla> getListPlanillaByNroAdm(String mesProceso) {
		try{
			StringBuilder sb=new StringBuilder();
			sb.append("Select p from SiprePlanilla p where 1=1 ");
			if(mesProceso!=null && !mesProceso.isEmpty()){
				sb.append(" and p.id.cplanillaMesProceso =:mes ");
			}
			sb.append(" order by p.vplanillaApeNom ");
			System.out.println( q.getResultList().size());
			Query q=em.createQuery(sb.toString());
			if(mesProceso!=null && !mesProceso.isEmpty()){
				q.setParameter("mes",mesProceso.trim());
			}
			
			
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	
	
}
