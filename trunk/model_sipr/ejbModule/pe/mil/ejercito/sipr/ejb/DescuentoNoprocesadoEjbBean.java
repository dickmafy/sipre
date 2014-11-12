package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.DescuentoNoprocesadoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoNoprocesado;

@Stateless
public class DescuentoNoprocesadoEjbBean extends
		GenericDAOImpl<SipreDescuentoNoprocesado> implements
		DescuentoNoprocesadoEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;
	

	
}
