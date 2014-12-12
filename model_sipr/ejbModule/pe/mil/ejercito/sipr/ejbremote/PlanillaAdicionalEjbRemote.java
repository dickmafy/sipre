package pe.mil.ejercito.sipr.ejbremote;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicionalPK;

@Remote
public interface PlanillaAdicionalEjbRemote extends GenericDAO<SiprePlanillaAdicional> {

	BigDecimal verificarSiYaSePago(SiprePlanillaAdicionalPK pk);

	List<Object[]> findMontoBy(Integer nplanillaNumProceso, String cplanillaMesProceso);

}