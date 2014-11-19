package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreBanco;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreParametroDetalle;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@Remote
public interface ParametroDetalleEjbRemote extends GenericDAO<SipreParametroDetalle> {
	
	List<SipreParametroDetalle> findAllDetalleById(String id);
	
	
}
