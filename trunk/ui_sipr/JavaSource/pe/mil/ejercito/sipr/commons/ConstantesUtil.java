package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantesUtil")
public class ConstantesUtil {

	// PROCESO PLANILLA
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_INFO			= "info";
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_ERROR			= "error";
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_WARNING		= "advertencia";

	public static final String	PROCESO_1_PLANILLA_NUMERO_HIJOS				= "Proceso 1 Numero Hijos";
	public static final String	PROCESO_2_PLANILLA_LISTA_REVISTA			= "Proceso 2 Lista Revista";

	// Estados Tablas
	public static final String	TABLA_ESTADO_ACTIVO							= "A";
	public static final String	TABLA_ESTADO_INACTIVO						= "I";

	// FINDALL DE TODOS LOS REGISTROS
	public static final Integer	LISTAR_EJB_REMOTE							= 500;

	// Mensajes Constantes
	public static final String	MENSAJE_RESPUESTA_ERROR_GENERAL				= "No se pudo completar la operacion.";
	public static final String	MENSAJE_RESPUESTA_CORRECTA					= "La operación se realizó correctamente.";
	public static final String	MENSAJE_FECHA_FORMATO_MAL					= "La fecha encontrada tiene un formato incorrecto.";

	public static final String	MENSAJE_EXCEL_ERROR							= "No se leyeron correctamente los datos del excel.";

	public static final String	MENSAJE_VACIO								= "";

	// GESTION FAMILIA
	public static final String	MENSAJE_RESPUESTA_ERROR_FAMILIA				= MENSAJE_RESPUESTA_ERROR_GENERAL
																					+ "Recuerde que Codigo CIF no se repite y Persona debe estar registrada.";

	// VERIFICAR_BANCO
	public static final String	MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO		= MENSAJE_RESPUESTA_ERROR_GENERAL
																					+ " No puede repetirse le personal.";

	// CONCEPTO DE INGRESO
	public static final String	MENSAJE_RESPUESTA_ERROR_CONCEPTO_INGRESO	= MENSAJE_RESPUESTA_ERROR_GENERAL
																					+ " No se puede repetir el Codigo Concepto."
																					+ " . Debe existir el Tipo Planilla";

	// 43 Descuent
	public static final String	MENSAJE_RESPUESTA_ERROR_DESCUENTO_LEY		= MENSAJE_RESPUESTA_ERROR_GENERAL
																					+ " No se puede repetir el Codigo del Descuento Ley.";

	// Listas
	public String getLIST_EMPTY() {
		return "No se encontraron resultados.";
	}

	public int getLIST_SIZE() {
		return 15;
	}

}
