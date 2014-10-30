package pe.mil.ejercito.sipr.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;

@Stateless
public class DescuentoLeyDetalleEjbBean extends
		GenericDAOImpl<SipreDescuentoLeyDet> implements
		DescuentoLeyDetalleEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SipreDescuentoLeyDet> findAllByIdDescuentoLey(String propiedad1) {
		String consulta = "SELECT s FROM SipreDescuentoLeyDet s where s.sipreDescuentoLeyDetPK.cdlCodigo=:propiedad1";
		Query q = em.createQuery(consulta);
		q = em.createQuery(consulta);
		q.setParameter("propiedad1", propiedad1);
		return q.getResultList();

	}

}
