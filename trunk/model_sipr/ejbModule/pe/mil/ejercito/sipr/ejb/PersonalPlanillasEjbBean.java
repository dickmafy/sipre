package pe.mil.ejercito.sipr.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.mil.ejercito.sipr.ejbremote.PersonalPlanillasEjbRemote;
import pe.mil.ejercito.sipr.model.PersonalPlanillas;

@Stateless
public class PersonalPlanillasEjbBean extends PersonaGenericDAOImpl<PersonalPlanillas> implements PersonalPlanillasEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@PersistenceContext(unitName = "model_personal")
	EntityManager	emPersona;

}
