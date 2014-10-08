package pe.mil.ejercito.sipr.commons;

import pe.mil.ejercito.sipr.model.SipreUsuario;

public class MainContext extends ServiceLocator{
	private static final long serialVersionUID = 1L;
	private SipreUsuario sessionUser;
	
	
	public MainContext() {
		super();
		setSessionUser((SipreUsuario)getVariable(UParametro.SSION_VRBLE_USRIO));
	}

	public SipreUsuario getSessionUser() {
		return sessionUser;
	}


	public void setSessionUser(SipreUsuario sessionUser) {
		this.sessionUser = sessionUser;
	}
	
}
