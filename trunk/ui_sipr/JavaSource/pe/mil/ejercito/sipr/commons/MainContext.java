package pe.mil.ejercito.sipr.commons;

import java.util.Date;

import pe.mil.ejercito.sipr.ejbremote.AuditoriaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAuditoria;
import pe.mil.ejercito.sipr.model.SipreUsuario;



public class MainContext extends ServiceLocator {
	private static final long serialVersionUID = 1L;
	private SipreUsuario sessionUser;
	private SipreAuditoria auditoria;

	
	protected AuditoriaEjbRemote ejbAuditoria;
	
	public MainContext() {
		super();
		setSessionUser((SipreUsuario) getVariable(UParametro.SSION_VRBLE_USRIO));
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
}
