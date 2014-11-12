package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TmpGuardiaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpGuardia;

@Stateless
public class TmpGuardiaEjbBean extends GenericDAOImpl<SipreTmpGuardia>
		implements TmpGuardiaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
