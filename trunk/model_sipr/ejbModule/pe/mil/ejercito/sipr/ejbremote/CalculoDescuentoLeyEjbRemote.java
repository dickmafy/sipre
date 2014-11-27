package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreCalculoDescuentoLey;

@Remote
public interface CalculoDescuentoLeyEjbRemote extends GenericDAO<SipreCalculoDescuentoLey> {

	void removeDelMes(String cplanillaMesProceso, Integer nplanillaNumProceso);

}
