package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.model.SipreGrado;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicional;
import pe.mil.ejercito.sipr.model.SiprePlanillaAdicionalPK;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@Remote
public interface ReintegroPersonalEjbRemote {
	/**
	 * Lista la primera grilta de reintegro del personal
	 * @param sipreTmpBonificacion
	 * @return
	 */
	List<SipreTmpBonificacion> listReintegroPersonal(SipreTmpBonificacion sipreTmpBonificacion);
	
	/**
	 * Lista la segunda grilla de mes adicional
	 * @param sipreTmpBonificacion
	 * @return
	 */
	List<SiprePlanillaAdicional> listReintegroMesAdicional(SiprePlanillaAdicional sipreTmpBonificacion);
	
	
	/**
	 * Lista la tercera grilla de registro
	 * @param sipreTmpBonificacion
	 * @return
	 */
	List<SiprePlanillaAdicional> listReintegroRegistro(SiprePlanillaAdicional sipreTmpBonificacion);

	
	
	/**
	 * insertar o actualizar planilla - tab1 - registro personal
	 * @param sipreTmpBonificacion
	 * @return
	 */
	SiprePlanilla registrar(SiprePlanilla beanPlanilla);
	
	

	

	
}
