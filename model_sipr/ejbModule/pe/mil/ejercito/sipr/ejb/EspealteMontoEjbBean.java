package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.EspealteMontoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreEspealteMonto;

@Stateless
public class EspealteMontoEjbBean extends
		GenericDAOImpl<SipreEspealteMonto> implements
		EspealteMontoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	

	
}
