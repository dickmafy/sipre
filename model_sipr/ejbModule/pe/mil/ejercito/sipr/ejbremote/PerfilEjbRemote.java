package pe.mil.ejercito.sipr.ejbremote;

import java.util.List;

import javax.ejb.Remote;

import pe.mil.ejercito.sipr.model.SiprePerfil;

@Remote
public interface PerfilEjbRemote {
	List<SiprePerfil> getPerfilList();
	Long eliminarPerfil(SiprePerfil perfil);
	SiprePerfil addEditPerfil(SiprePerfil perfil);
}
