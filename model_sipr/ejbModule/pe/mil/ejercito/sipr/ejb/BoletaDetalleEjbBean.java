package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.BoletaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBoletaDetalle;

@Stateless
public class BoletaDetalleEjbBean extends
		GenericDAOImpl<SipreBoletaDetalle> implements
		BoletaDetalleEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;

	

	
}
