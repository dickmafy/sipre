package pe.mil.ejercito.sipr.ejb;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable> {
	 
	T findById(Long id);

	// List<T> findAll(Pagination paginacion);
	
	long countAll();

	T persist(T bean);

	void remove(T bean);

	void removeAll();

	T merge(T t);

	List<T> findAll();

	List<T> findAll(int maxRowReturn);

	List<T> findAllSort(int maxRowReturn, String propiedad1);
	
	List<T> findAllSortDes(int maxRowReturn, String propiedad1);


}