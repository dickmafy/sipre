package pe.mil.ejercito.sipr.commons;

import java.util.Date;
import java.util.List;

import pe.mil.ejercito.sipr.ejbremote.AuditoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAuditoria;
import pe.mil.ejercito.sipr.model.SipreUsuario;
import pe.mil.ejercito.sipr.view.VwOpcionPerfil;



public class MainContext extends ServiceLocator {
	private static final long serialVersionUID = 1L;
	private SipreUsuario sessionUser;
	private List<VwOpcionPerfil> sessionOSList;
	private SipreAuditoria auditoria;

	
	protected AuditoriaEjbRemote ejbAuditoria;
	
	public MainContext() {
		super();
		setSessionUser((SipreUsuario) getVariable(UParametro.SSION_VRBLE_USRIO));
		setSessionOSList((List<VwOpcionPerfil>) getVariable(UParametro.SSION_VRBLE_PERFIL));
		ejbAuditoria = (AuditoriaEjbRemote)findServiceRemote(AuditoriaEjbRemote.class);
	}

	public SipreUsuario getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(SipreUsuario sessionUser) {
		this.sessionUser = sessionUser;
	}

	public SipreAuditoria getAuditoria(String accion, Date fecha, String tabla, String vnuevo,
			String vanterior, String resultadoAccion, String cusuario) {
		SipreAuditoria aud = new SipreAuditoria();
		aud.setAudAccion(accion);
		aud.setAudFecha(fecha);
		aud.setAudTabla(tabla);
		aud.setAudValorAntiguo(vanterior);
		aud.setAudValorNuevo(vnuevo);
		aud.setAudResultadoAccion(resultadoAccion);
		aud.setCusuarioCodigo(cusuario);
		return aud;
	}

	public List<VwOpcionPerfil> getSessionOSList() {
		return sessionOSList;
	}

	public void setSessionOSList(List<VwOpcionPerfil> sessionOSList) {
		this.sessionOSList = sessionOSList;
	}
}
