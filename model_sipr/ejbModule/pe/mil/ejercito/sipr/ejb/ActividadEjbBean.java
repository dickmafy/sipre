package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ActividadEjbRemote;
import pe.mil.ejercito.sipr.model.SipreActividad;

@Stateless
public class ActividadEjbBean extends
		GenericDAOImpl<SipreActividad> implements
		ActividadEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;
	
}
