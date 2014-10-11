package pe.mil.ejercito.sipr.ejbremote;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface IngresoConceptoPersonalEjbRemote extends GenericDAO<SipreIngresoOtro> {

	/**
	 * Retorna la lista de usuarios
	 * 
	 * @param usuario
	 * @return
	 */
	List<SipreIngresoOtro> listIngresoConceptoPersonal(SipreIngresoOtro usuario);

	/**
	 * inserta un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Boolean registrarIngresoConceptoPersonal(SipreIngresoOtro usuario);

	/**
	 * deshabilitar un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Boolean deshabilitarIngresoConceptoPersonal(SipreIngresoOtro usuario);

	/**
	 * buscar un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	SipreIngresoOtro getBean(SipreIngresoOtro usuario);

}
