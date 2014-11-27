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
		contenidoDefault = UProperties.getMessage(UParametro.PROP_CONFIGURACIONES, UParametro.CTNDO_DFULT);
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
			menuLateral = "/modules/menu/gestion";
			contenidoDefault = "/modules/blank_page";
			break;
		case 3:
			menuLateral = "/modules/menu/planilla";
			contenidoDefault = "/modules/blank_page";
			break;
		case 4:
			menuLateral = "/modules/menu/ingresoDescuento";
			contenidoDefault = "/modules/blank_page";
			break;
		case 10:
			menuLateral = "/modules/menu/seguridad";
			contenidoDefault = "/modules/blank_page";
			break;
		case 11:
			menuLateral = "/modules/menu/reporte";
			contenidoDefault = "/modules/blank_page";
			break;
		}
		return "";
	}

	public String redireccionaContent(Long id) {
		switch (id.intValue()) {
		case 11:
			contenidoDefault = "/modules/mi_cuenta/cambiar_contrasena";
			break;
		case 20:
			contenidoDefault = "/modules/blank_page";
			break;
		case 30:
			contenidoDefault = "/modules/registro/grupoGrado";
			break;
		case 29:
			contenidoDefault = "/modules/registro/grado";
			break;
		case 31:
			contenidoDefault = "/modules/planilla/reintegroPersonal_list";
			break;
		case 32:
			contenidoDefault = "/modules/registro/ingresoConceptoPersonal";
			break;
		case 33:
			contenidoDefault = "/modules/gestion/bonificacionPersonal";
			break;
		case 40:
			contenidoDefault = "/modules/seguridad/gestionar_usuario/usuario";
			break;
		case 41:
			contenidoDefault = "/modules/seguridad/gestionar_perfil/perfil";
			break;
		case 42:
			contenidoDefault = "/modules/seguridad/auditoria/auditoria";
			break;
		// GESTION
		case 50:
			contenidoDefault = "/modules/gestion/verificarCodigoBanco";
			break;
		case 51:
			contenidoDefault = "/modules/gestion/concepto";
			break;
		case 52:
			contenidoDefault = "/modules/gestion/familia";
			break;
		// PLANILLA
		case 60:
			contenidoDefault = "/modules/planilla/procesarPlanilla";
			break;
		case 61:
			contenidoDefault = "/modules/planilla/crediticia";
			break;
		case 62:
			contenidoDefault = "/modules/planilla/judicial";
			break;
		// PLANILLA
		case 70:
			contenidoDefault = "/modules/ingresoDescuento/conceptoDescuentoLey";
			break;
		case 71:
			contenidoDefault = "/modules/ingresoDescuento/conceptoDescuento";
			break;
		case 72:
			contenidoDefault = "/modules/ingresoDescuento/descuentoIngreso";
			break;
		case 73:
			contenidoDefault = "/modules/ingresoDescuento/descuentoLeyDetalle";
			break;
		case 74:
			contenidoDefault = "/modules/ingresoDescuento/descuentoLey";
			break;

		// PARAMETRIZACION
		case 80:
			contenidoDefault = "/modules/parametro/parametro";
			break;
		case 81:
			contenidoDefault = "/modules/parametro/parametroDetalle";
			break;
	    //reportes
		case 82:
			contenidoDefault = "/modules/reporte/rpt_aprt_fondo_salud";
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
