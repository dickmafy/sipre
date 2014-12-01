package pe.mil.ejercito.sipr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.TmpFamiliaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

/**
 * Session Bean implementation class beanEjbBean
 */
@Stateless
public class TmpFamiliaEjbBean extends GenericDAOImpl<SipreTmpFamilia>
		implements TmpFamiliaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SipreTmpFamilia> findAllByIdPersona(String propiedad1) {
		List<SipreTmpFamilia> list;
		String consulta = "SELECT s FROM SipreTmpFamilia s where s.cpersonaNroAdm=:propiedad1";
		Query q = em.createQuery(consulta);
		q = em.createQuery(consulta);
		q.setParameter("propiedad1", propiedad1);
		list = q.getResultList();
		return list;

	}

	
}
