package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.SituacionCedulaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreSituacionCedula;

@Stateless
public class SituacionCedulaEjbBean extends GenericDAOImpl<SipreSituacionCedula>
		implements SituacionCedulaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	
}
