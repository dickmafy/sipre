package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@Remote
public interface VerificarCodigoBancoEjbRemote extends GenericDAO<SipreTmpBanco> {
	
	
}
