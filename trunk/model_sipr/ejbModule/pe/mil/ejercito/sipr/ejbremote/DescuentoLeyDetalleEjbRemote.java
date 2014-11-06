package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;

@Remote
public interface DescuentoLeyDetalleEjbRemote extends GenericDAO<SipreDescuentoLeyDet>{

	List<SipreDescuentoLeyDet> findAllByIdDescuentoLey(String propiedad1);
	
	
	
	
	 
	

	
}
