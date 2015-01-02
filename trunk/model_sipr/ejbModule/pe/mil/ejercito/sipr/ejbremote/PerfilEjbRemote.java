package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.view.VwOpcionPerfil;

@Remote
public interface PerfilEjbRemote {
	List<SiprePerfil> getPerfilList();
	Long eliminarPerfil(SiprePerfil perfil);
	SiprePerfil addEditPerfil(SiprePerfil perfil);
	List<VwOpcionPerfil> getPerfil(Long idPerfil);
}
