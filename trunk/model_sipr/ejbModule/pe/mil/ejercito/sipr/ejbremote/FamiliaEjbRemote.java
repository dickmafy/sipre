package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;

@Remote
public interface FamiliaEjbRemote extends GenericDAO<SipreTmpFamilia> {
	
	
}
