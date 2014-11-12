package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.CedulaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCedula;

@Stateless
public class CedulaEjbBean extends
		GenericDAOImpl<SipreCedula> implements
		CedulaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	

	
}
