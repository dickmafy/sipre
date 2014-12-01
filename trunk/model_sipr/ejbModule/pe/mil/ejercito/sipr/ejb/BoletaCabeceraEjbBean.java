package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.BoletaCabeceraEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBoletaCabecera;

@Stateless
public class BoletaCabeceraEjbBean extends
		GenericDAOImpl<SipreBoletaCabecera> implements
		BoletaCabeceraEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	

	
}
