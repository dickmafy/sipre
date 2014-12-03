package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SiprePersona;

@Remote
public interface PersonaEjbRemote extends
		GenericDAO<SiprePersona> {

	List<SiprePersona> getPersonasEnActividad();

	List<SiprePersona> procesarPlanillaPrincipalList();

	Integer updatePersonaHijos(String cip, String numeroHijo);

	Boolean removePersonaHijosAZero();

}
