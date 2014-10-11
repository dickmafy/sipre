package pe.mil.ejercito.sipr.seguridad;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UParametro;
import pe.mil.ejercito.sipr.commons.UProperties;

@ManagedBean(name = "mainMb")
@ViewScoped
public class Main extends MainContext {

	private static final long serialVersionUID = 1L;

	private String menuLateral;
	private String contenidoDefault;

	public Main() {
		super();
		/* Para efectos de presentacion */
		menuLateral = UProperties.getMessage(UParametro.PROP_CONFIGURACIONES,
				UParametro.MNU_LTRAL_DFULT);
		contenidoDefault = UProperties.getMessage(
				UParametro.PROP_CONFIGURACIONES, UParametro.CTNDO_DFULT);
	}

	/* Inicio: Para efectos de presentación, la logica se regenerará */
	public String redireccionaMnu(Long id) {
		switch (id.intValue()) {
		case 0:
			menuLateral = "/modules/menu/mi_cuenta";
			contenidoDefault = "/modules/blank_page";
			break;
		case 1:
			menuLateral = "/modules/menu/parametrizacion";
			contenidoDefault = "/modules/blank_page";
			break;
		case 2:
			menuLateral = "/modules/menu/seguridad";
			contenidoDefault = "/modules/blank_page";
			break;
		}
		return "";
	}
	
	public String redireccionaContent(Long id){
		switch (id.intValue()) {
		case 11:
			contenidoDefault = "/modules/mi_cuenta/cambiar_contrasena";
			break;
		case 20:
			contenidoDefault = "/modules/blank_page";
			break;
		case 30:
			contenidoDefault = "/modules/registro/familiaEC__list";
			break;
		case 31:
			contenidoDefault = "/modules/registro/reintegroPersonal_list";
			break;
		case 32:
			contenidoDefault = "/modules/registro/ingresoConceptoPersonal";
			break;
		
		case 40:
			contenidoDefault = "/modules/seguridad/gestionar_usuario/usuario";
			break;
		case 41:
			contenidoDefault = "/modules/seguridad/gestionar_perfil/perfil";
			break;
		case 42:
			contenidoDefault="/modules/seguridad/auditoria/auditoria";
			break;
		
		}
		return "";
	}
	/* Fin: Para efectos de presentación, la logica se regenerará */
	

	public String getMenuLateral() {
		return menuLateral;
	}

	public void setMenuLateral(String menuLateral) {
		this.menuLateral = menuLateral;
	}

	public String getContenidoDefault() {
		return contenidoDefault;
	}

	public void setContenidoDefault(String contenidoDefault) {
		this.contenidoDefault = contenidoDefault;
	}

}
