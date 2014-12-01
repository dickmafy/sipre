package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.SalidaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreSalida;

@Stateless
public class SalidaEjbBean extends GenericDAOImpl<SipreSalida>
		implements SalidaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	
}
