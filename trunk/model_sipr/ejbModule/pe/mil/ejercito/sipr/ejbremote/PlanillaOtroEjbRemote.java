package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SiprePlanillaOtro;

@Remote
public interface PlanillaOtroEjbRemote extends
		GenericDAO<SiprePlanillaOtro> {

	List<SiprePlanillaOtro> procesarPlanillaOtrosList();

}
