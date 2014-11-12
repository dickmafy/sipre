package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.SituacionCausalEjbRemote;
import pe.mil.ejercito.sipr.model.SipreSituacionCausal;

@Stateless
public class SituacionCausalEjbBean extends GenericDAOImpl<SipreSituacionCausal>
		implements SituacionCausalEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
