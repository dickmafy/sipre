package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.DescuentoIngresoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;
import pe.mil.ejercito.sipr.model.SiprePersona;

@Stateless
public class PersonaEjbBean extends GenericDAOImpl<SiprePersona> implements
		PersonaEjbRemote {

	@PersistenceContext(name = "model_sipre")
	EntityManager em;
}
