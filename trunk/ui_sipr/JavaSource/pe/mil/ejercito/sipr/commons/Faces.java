package pe.mil.ejercito.sipr.commons;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class Faces extends FacesMessage {
	private static final long serialVersionUID = 1L;

	public static void showMessage(String texto, Severity severity) {
		FacesMessage fm = new FacesMessage(severity, texto, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		
	}
	
	public static void showMessage(String texto, Severity severity,String detail) {
		FacesMessage fm = new FacesMessage(severity, texto,detail);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		
	}

	public static void showDialog(String widgetVar) {
		RequestContext.getCurrentInstance().execute(
				"PF('" + widgetVar + "').show();");
	}

	public static void hideDialog(String widgetVar) {
		RequestContext.getCurrentInstance().execute(
				"PF('" + widgetVar + "').hide();");
	}

	public static void updateComponente(String id) {
		RequestContext.getCurrentInstance().update(id);
	}

	public static final void registrarVariable(String nombre, Object variable) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(nombre, variable);
	}

	public static final Object getVariable(String nombre) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(nombre);
	}

	public static final Object removeVariable(String nombre) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().remove(nombre);
	}

	public static String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		return redirecciona("/index");
		
	}

	public static String redirecciona(String url) {
		return url.concat("?faces-redirect=true");
	}

	public static String rutaServidor() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("");
	}
}
