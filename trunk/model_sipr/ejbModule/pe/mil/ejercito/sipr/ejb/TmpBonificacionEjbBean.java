package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TmpBonificacionEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;

@Stateless
public class TmpBonificacionEjbBean extends GenericDAOImpl<SipreTmpBonificacion>
		implements TmpBonificacionEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;

	

	
}
