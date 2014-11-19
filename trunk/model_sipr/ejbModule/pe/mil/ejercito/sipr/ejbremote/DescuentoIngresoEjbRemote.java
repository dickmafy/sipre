package pe.mil.ejercito.sipr.ejbremote;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreDescuentoIngreso;
import pe.mil.ejercito.sipr.model.SipreTmpBanco;

@Remote
public interface DescuentoIngresoEjbRemote extends GenericDAO<SipreDescuentoIngreso> {

}
