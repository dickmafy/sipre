package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.DiferenciaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDiferencia;

@Stateless
public class DiferenciaEjbBean extends
		GenericDAOImpl<SipreDiferencia> implements
		DiferenciaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	

	
}
