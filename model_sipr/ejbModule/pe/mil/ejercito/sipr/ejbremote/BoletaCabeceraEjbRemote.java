package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreBoletaCabecera;

@Remote
public interface BoletaCabeceraEjbRemote extends GenericDAO<SipreBoletaCabecera> {
	

	SipreBoletaCabecera saveCabecera(SipreBoletaCabecera boleta);

	
}
