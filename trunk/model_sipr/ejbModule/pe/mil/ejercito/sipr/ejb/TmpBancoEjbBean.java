package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TmpBancoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@Stateless
public class TmpBancoEjbBean extends GenericDAOImpl<SipreTmpBanco>
		implements TmpBancoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	
}
