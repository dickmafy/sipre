package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@Remote
public interface PersonaEjbRemote extends
		GenericDAO<SiprePersona> {
	
	List<SiprePersona> findAllBySituacionAdministrativaYActividad();
	List<SiprePersona> findAllBySituacionAdministrativaDeListaRevista();

	

}
