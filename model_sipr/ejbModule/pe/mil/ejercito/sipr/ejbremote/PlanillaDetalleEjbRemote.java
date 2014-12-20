package pe.mil.ejercito.sipr.ejbremote;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;

@Remote
public interface PlanillaDetalleEjbRemote extends GenericDAO<SiprePlanillaDetalle> {

	List<SiprePlanillaDetalle> getListPlanillaDetalle(String mesproceso);

	BigDecimal getSueldoPorPersona(String cpersonaNroAdm, Integer nplanillaNumProceso, String cplanillaMesProceso);

}
