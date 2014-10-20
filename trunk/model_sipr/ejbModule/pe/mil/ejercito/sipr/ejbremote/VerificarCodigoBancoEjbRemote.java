package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface VerificarCodigoBancoEjbRemote extends GenericDAO<SipreTmpBanco> {

	List<SipreTmpBanco> listBean(SipreTmpBanco bean);
	
	
	
}
