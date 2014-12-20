package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SiprePlanilla;

@Remote
public interface PlanillaEjbRemote extends GenericDAO<SiprePlanilla> {

	Long siPersonaExisteEnPlanillaPrincipal(String cip);

	List<SiprePlanilla> getListPlanillaByNroAdm(String mesProceso);

}
