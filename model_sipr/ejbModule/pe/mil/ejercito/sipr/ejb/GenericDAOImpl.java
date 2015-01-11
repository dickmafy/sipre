package pe.mil.ejercito.sipr.ejb;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDAOImpl<T extends Serializable> implements GenericDAO<T> {

	private String			nombreClazz;
	private final Class<T>	clazz;
	@PersistenceContext(unitName = "model_sipre")
	EntityManager			em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class) genericSuperclass.getActualTypeArguments()[0];

	}


	@Override
	public T findById(Long id) {
		return em.find(clazz, id);
	}

	@Override
	public T findById(String id) {
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
		return em.createQuery("from " + clazz.getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(int maxRowReturn) {
		return em.createQuery("from " + clazz.getName()).setMaxResults(maxRowReturn).getResultList();
	}

	@Override
	public List<T> findAllSort(int maxRowReturn, String propiedad1) {
		List<T> list;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);

		Root<T> from = query.from(clazz);
		query.orderBy(criteriaBuilder.asc(from.get(propiedad1)));

		query.select(from);

		list = em.createQuery(query).setMaxResults(maxRowReturn).getResultList();
		return list;
	}

	@Override
	public List<T> findAllSortDes(int maxRowReturn, String propiedad1) {
		List<T> list;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);

		Root<T> from = query.from(clazz);
		query.orderBy(criteriaBuilder.desc(from.get(propiedad1)));

		query.select(from);

		list = em.createQuery(query).setMaxResults(maxRowReturn).getResultList();
		return list;
	}

	@Override
	public long countAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(clazz)));

		return em.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public T persist(T object) throws EJBTransactionRolledbackException {
		em.persist(object);
		return object;
	}

	@Override
	public void remove(T object) {
		em.remove(em.contains(object) ? object : em.merge(object));
		// em.remove(object);
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

	@Override
	public boolean findPkExist(String nombreClasePadre, Object clasePkHija) throws ClassNotFoundException {
		Class<?> cls = Class.forName("pe.mil.ejercito.sipr.model." + nombreClasePadre);
		if (em.find(cls, clasePkHija) != null) {
			return true;
		}
		return false;
		/*
		 * Remplazar en tu Ejb*Bean si no se tiene esos parametros. Ejemplo:
		 * 
		 * @Override public boolean findPk(SiprePlanillaDetallePK
		 * siprePlanillaDetallePK) { if (em.find(SiprePlanillaDetalle.class,
		 * siprePlanillaDetallePK) != null) { return true; } return false; }
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByPkCompuesta(String nombreClasePadre, Object clasePkHija) throws ClassNotFoundException {
		Class<?> cls = Class.forName("pe.mil.ejercito.sipr.model." + nombreClasePadre);
		return (T) em.find(cls, clasePkHija);
	}

	/*
			public T findObjectByField(String nombreCampo, Object valorCampo) {
				Criteria criteria = em.createCriteria(clazz.getName());
				T object = criteria.add(Restrictions.eq(nombreCampo, valorCampo)).uniqueResult();
				return object;
			}
	*/

	@SuppressWarnings("unchecked")
	public List<T> findObjectByField(String nombreCampo, Object valorCampo) {
		final Query query = em.createQuery("SELECT o FROM +" + clazz.getName() + " + as m WHERE m." + nombreCampo + " = :" + nombreCampo);
		query.setParameter(nombreCampo, valorCampo);
		return query.getResultList();
	}

	@Override
	public List<T> findObjectByFieldCriteria(String nombreCampo, Object valorCampo) {
		List<T> list;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
		Root<T> from = query.from(clazz);
		query.where(criteriaBuilder.equal(from.get(nombreCampo), valorCampo));
		//query.where(criteriaBuilder.equal(from.get(nombreCampo), valorCampo));
		query.select(from);
		list = em.createQuery(query).getResultList();
		return list;
	}

	@Override
	public void deleteProcesoDelMes(String nombreTabla, String nombreCampoMes, String nombreCampoNumero, String cplanillaMesProceso,
			Integer nplanillaNumProceso) {

		StringBuilder sb = new StringBuilder();
		sb.append("delete from " + nombreTabla + " o ");
		sb.append(" where o." + nombreCampoMes + "=:" + "nombreCampoMes");
		sb.append(" and o." + nombreCampoNumero + "=:" + "nombreCampoNumero");
		Query q = em.createQuery(sb.toString());
		q.setParameter("nombreCampoMes", cplanillaMesProceso);
		q.setParameter("nombreCampoNumero", nplanillaNumProceso);
		q.executeUpdate();

	}

}