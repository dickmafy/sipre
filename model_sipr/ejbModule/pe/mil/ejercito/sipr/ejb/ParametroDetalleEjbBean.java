package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ParametroDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreParametroDetalle;

@Stateless
public class ParametroDetalleEjbBean extends
		GenericDAOImpl<SipreParametroDetalle> implements
		ParametroDetalleEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	@Override
	public List<SipreParametroDetalle> findAllDetalleById(String id) {
		List<SipreParametroDetalle> pList = null;
        try{
            Query q = em.createQuery("select sp from SipreParametroDetalle sp where sp.cpdCodigo.cpdCodigo=:cod");
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            q.setParameter("cod", id);
            pList = q.getResultList();
            
        }catch(Exception e){}
        
        return pList;
	}

}
