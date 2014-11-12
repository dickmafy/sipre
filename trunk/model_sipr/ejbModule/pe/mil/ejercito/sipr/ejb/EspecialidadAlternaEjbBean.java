package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.EspecialidadAlternaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEspecialidadAlterna;
@Stateless
public class EspecialidadAlternaEjbBean extends
		GenericDAOImpl<SipreEspecialidadAlterna> implements
		EspecialidadAlternaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;	

	

	
}
