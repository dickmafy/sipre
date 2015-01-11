package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PermanenteEjbRemote;
import pe.mil.ejercito.sipr.model.SiprePermanente;
 
@Stateless
public class PermanenteEjbBean extends GenericDAOImpl<SiprePermanente>
 implements PermanenteEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;


}
