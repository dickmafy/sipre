package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;

import pe.mil.ejercito.sipr.model.SiprePlanillaDescuento;

@Remote
public interface PlanillaDescuentoEjbRemote extends GenericDAO<SiprePlanillaDescuento> {
	

	List<SiprePlanillaDescuento> getListPlanillaDescuento(String mesproceso,Integer tipo);

	
}
