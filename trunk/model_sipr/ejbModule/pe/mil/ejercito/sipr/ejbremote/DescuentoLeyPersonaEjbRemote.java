package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyPersona;

@Remote
public interface DescuentoLeyPersonaEjbRemote extends GenericDAO<SipreDescuentoLeyPersona> {

	List<SipreDescuentoLeyPersona> findByCip(String cip);

}
