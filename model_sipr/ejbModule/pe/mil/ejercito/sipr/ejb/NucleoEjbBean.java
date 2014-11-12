package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.NucleoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreNucleo;

@Stateless
public class NucleoEjbBean extends GenericDAOImpl<SipreNucleo>
		implements NucleoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
