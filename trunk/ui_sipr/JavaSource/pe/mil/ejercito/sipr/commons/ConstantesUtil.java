package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantesUtil")
public class ConstantesUtil {

	// Estados Tablas
	public static final String TABLA_ESTADO_ACTIVO = "A";
	public static final String TABLA_ESTADO_INACTIVO = "I";

	// Mensajes Constantes
	public static final String MENSAJE_RESPUESTA_ERROR_GENERAL = "No se pudo completar la operacion.";
	public static final String MENSAJE_RESPUESTA_CORRECTA = "La operaci�n se realiz� correctamente.";
	public static final String MENSAJE_FECHA_FORMATO_MAL = "La fecha encontrada tiene un formato incorrecto.";

	public static final String MENSAJE_EXCEL_ERROR = "No se leyeron correctamente los datos del excel.";

	public static final String MENSAJE_VACIO = "";

	// GESTION FAMILIA
	public static final String MENSAJE_RESPUESTA_ERROR_FAMILIA = MENSAJE_RESPUESTA_ERROR_GENERAL
			+ "Recuerde que Codigo CIF no se repite y Persona debe estar registrada.";

	// VERIFICAR_BANCO
	public static final String MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO = MENSAJE_RESPUESTA_ERROR_GENERAL
			+ " No puede repetirse le personal.";

	// CONCEPTO DE INGRESO
	public static final String MENSAJE_RESPUESTA_ERROR_CONCEPTO_INGRESO = MENSAJE_RESPUESTA_ERROR_GENERAL
			+ " No se puede repetir el Codigo Concepto."
			+ " . Debe existir el Tipo Planilla";

	// Listas
	public String getLIST_EMPTY() {
		return "No se encontraron resultados.";
	}

	public int getLIST_SIZE() {
		return 15;
	}

}
