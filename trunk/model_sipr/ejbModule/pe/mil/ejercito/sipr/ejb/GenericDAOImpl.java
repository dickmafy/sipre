package pe.mil.ejercito.sipr.ejb;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAOImpl<T extends Serializable> implements GenericDAO<T> {
	
	
	private Class<T> clazz;
	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAOImpl(){
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class) genericSuperclass.getActualTypeArguments()[0];
	}
	
	
	@Override
	public T findById(Long id) {
		return em.find(clazz, id);
	}

	/*
	 * @Override public List<T> findAll(Pagination paginacion) { CriteriaBuilder
	 * cb = entityManager.getCriteriaBuilder(); CriteriaQuery<T> cq =
	 * cb.createQuery(clazz); Root<T> root = cq.from(clazz);
	 * 
	 * cq.select(root); if (paginacion != null) {
	 * cq.orderBy(paginacion.getOrders(root, cb)); }
	 * 
	 * Query q = entityManager.createQuery(cq); if (paginacion != null) {
	 * q.setFirstResult(paginacion.getStart());
	 * q.setMaxResults(paginacion.getEnd() - paginacion.getStart() + 1); }
	 * 
	 * return q.getResultList(); }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) em.createQuery("from " + clazz.getName())
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(int maxRowReturn) {
		return (List<T>) em.createQuery("from " + clazz.getName())
				.setMaxResults(maxRowReturn).getResultList();
	}

	@Override
	public long countAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(clazz)));

		return em.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public T persist(T object) {
		em.persist(object);
		return object;
	}

	@Override
	public void remove(T object) {
		em.remove(object);
	}

	@Override
	public void removeAll() {
		String jpql = String.format("delete from %s", clazz.getName());
		Query query = em.createQuery(jpql);
		query.executeUpdate();
	}

	@Override
	public T merge(T t) {
		return em.merge(t);
	}

	
}