package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.CalculoQuintaCategoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreCalculoQuintaCategoria;

@Stateless
public class CalculoQuintaCategoriaEjbBean extends
		GenericDAOImpl<SipreCalculoQuintaCategoria> implements
		CalculoQuintaCategoriaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager	em;
	

	
}
