package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.ImportarInfoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreImportarInfo;

@Stateless
public class ImportarInfoEjbBean extends GenericDAOImpl<SipreImportarInfo>
		implements ImportarInfoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	
}
