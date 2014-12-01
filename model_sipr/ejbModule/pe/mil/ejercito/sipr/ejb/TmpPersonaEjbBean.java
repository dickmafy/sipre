package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.TmpPersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpPersona;


@Stateless
public class TmpPersonaEjbBean extends GenericDAOImpl<SipreTmpPersona> implements TmpPersonaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	

	

}
