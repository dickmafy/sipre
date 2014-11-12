package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.UnidadEjbRemote;
import pe.mil.ejercito.sipr.model.SipreUnidad;

@Stateless
public class UnidadEjbBean extends GenericDAOImpl<SipreUnidad>
		implements UnidadEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
