package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.DiferenciaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDiferenciaDetalle;

@Stateless
public class DiferenciaDetalleEjbBean extends
		GenericDAOImpl<SipreDiferenciaDetalle> implements
		DiferenciaDetalleEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	

	
}
