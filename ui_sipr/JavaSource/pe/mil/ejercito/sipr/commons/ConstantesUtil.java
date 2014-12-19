package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name = "constantesUtil")
public class ConstantesUtil {

	// PROCESO PLANILLA
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_INFO			= "info";
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_ERROR			= "error";
	public static final String	MENSAJE_GENERIC_TIPO_MENSAJE_WARNING		= "warning";

	public static final int		GENERIC_MENSAJE_DT_HIJO						= 1;
	public static final int		GENERIC_MENSAJE_DT_PADRE					= 0;

	//PLANILLA - PLANILLA DETALLE
	public static final String	PROCESAR_PLANILLA_CODIGO_PLANILLA_PRINCIPAL_01	= "01";
	public static final String	PROCESAR_PLANILLA_CODIGO_PRINCIPAL_CCI_0080		= "0080";
	public static final String	PROCESAR_PLANILLA_CODIGO_ESTADO_ACTIVIDAD	= "01";

	//Procesar Planilla - > codigos
	public static final String	PROCESAR_PLANILLA_CODIGO_1					= "01";
	public static final String	PROCESAR_PLANILLA_CODIGO_2					= "02";
	public static final String	PROCESAR_PLANILLA_CODIGO_3					= "03";
	public static final String	PROCESAR_PLANILLA_CODIGO_4					= "04";
	public static final String	PROCESAR_PLANILLA_CODIGO_11					= "11";
	public static final String	PROCESAR_PLANILLA_CODIGO_12					= "12";
	public static final String	PROCESAR_PLANILLA_CODIGO_15					= "15";
	public static final String	PROCESAR_PLANILLA_CODIGO_16					= "16";
	public static final String	PROCESAR_PLANILLA_CODIGO_17					= "17";

	public static final String	PROCESAR_PLANILLA_CODIGO_5					= "05";
	public static final String	PROCESAR_PLANILLA_CODIGO_6					= "06";
	public static final String	PROCESAR_PLANILLA_CODIGO_7					= "07";
	public static final String	PROCESAR_PLANILLA_CODIGO_8					= "08";
	public static final String	PROCESAR_PLANILLA_CODIGO_9					= "09";
	public static final String	PROCESAR_PLANILLA_CODIGO_10					= "10";
	public static final String	PROCESAR_PLANILLA_CODIGO_13					= "13";
	public static final String	PROCESAR_PLANILLA_CODIGO_14					= "14";

	//TEXTO
	public static final String	PROCESO_1										= "*Proceso 1 Numero Hijos";
	public static final String	PROCESO_2										= "*Proceso 2 Lista Revista";
	public static final String	PROCESO_3										= "*Proceso 3 Ingreso Por Persona";
	public static final String	PROCESO_4										= "*Proceso 4 Calcular Descuento de Ley";
	public static final String	PROCESO_11										= "*Proceso 11 Bonificacion DL 1132";
	public static final String	PROCESO_12										= "*Proceso 12 Bonificacion DU 040";
	public static final String	PROCESO_15										= "*Proceso 15 Calcular Impuesto a la renta";
	public static final String	PROCESO_16										= "*Proceso 16 Actualizar Descuento";
	public static final String	PROCESO_17										= "*Proceso 17 Calcular Boleta";

	public static final String	PROCESO_5									= "Proceso 5 Guardias Hospitalarias";
	public static final String	PROCESO_6									= "Proceso 6 Combustible";
	public static final String	PROCESO_7									= "Proceso 7 Chofer y Mayordomo";
	public static final String	PROCESO_8									= "Proceso 8 Ex Comabiente del 41";
	public static final String	PROCESO_9									= "Proceso 9 Ex Comabiente Cenepa";
	public static final String	PROCESO_10									= "Proceso 10 Subsidio DL 1132";
	public static final String	PROCESO_13									= "Proceso 13 Subsidio por invalidez o póstumo";
	public static final String	PROCESO_14									= "Proceso 14 Bonificación por Cumplir 65 años";

	// Estados Tablas
	public static final String	TABLA_ESTADO_ACTIVO							= "A";
	public static final String	TABLA_ESTADO_INACTIVO						= "I";

	// FINDALL DE TODOS LOS REGISTROS
	public static final Integer	LISTAR_EJB_REMOTE							= 500;

	// Mensajes Constantes
	public static final String	MENSAJE_RESPUESTA_ERROR_GENERAL				= "No se pudo completar la operacion.";
	public static final String	MENSAJE_RESPUESTA_CORRECTA					= "La operacion se realizo correctamente.";
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

	public static final int		TIPO_FILE_ENTIDAD							= 2;
	public static final int		TIPO_FILE_JUDICIAL							= 1;

	public static final int		EXCEL_ROW_INICIO_DTLL						= 2;

	public static final int		EXCEL_COLUMN_ANIO_MES						= 1;
	public static final int		EXCEL_COLUMN_CIP							= 2;
	public static final int		EXCEL_COLUMN_NOMBRES						= 3;
	public static final int		EXCEL_COLUMN_CONCEPTO						= 4;
	public static final int		EXCEL_COLUMN_MONTO							= 5;
	public static final int		EXCEL_COLUMN_SITUACION						= 6;
	public static final int		EXCEL_COLUMN_MES_BONIF					    = 7;
	public static final int		EXCEL_COLUMN_MES_REINTEGRO					= 8;
	public static final int		EXCEL_COLUMN_DEDUCCION						= 9;
	
	public static final String		EXCEL_NAME_ANIO_MES						= "B";
	public static final String		EXCEL_NAME_CIP							= "C";
	public static final String		EXCEL_NAME_NOMBRES						= "D";
	public static final String		EXCEL_NAME_CONCEPTO						= "E";
	public static final String		EXCEL_NAME_MONTO						= "F";
	public static final String		EXCEL_NAME_SITUACION					= "G";
	public static final String		EXCEL_NAME_MES_BONIF					= "H";
	public static final String		EXCEL_NAME_MES_REINTEGRO				= "I";
	public static final String		EXCEL_NAME_DEDUCCION					= "J";
	
	public static final int		EXCEL_COLUMN_DESCUENTO						= 6;
	public static final int		EXCEL_COLUMN_PAGAR					        = 7;
	public static final int		EXCEL_COLUMN_MES_GUARDIA				    = 8;
	public static final int		EXCEL_COLUMN_MES_REINTEGRO_GUARDIA			= 9;
	public static final int		EXCEL_COLUMN_SITUACION_GUARDIA						= 10;

	
	public static final String		EXCEL_NAME_DESCUENTO					= "G";
	public static final String		EXCEL_NAME_PAGAR					= "H";
	public static final String		EXCEL_NAME_MES_GUARDIA				= "I";
	public static final String		EXCEL_NAME_MES_REINTEGRO_GUARDIA					= "J";
	public static final String		EXCEL_NAME_SITUACION_GUARDIA					= "K";
	
	public static final int		TIPO_LEY_JUDICIAL							= 1;
	public static final int		TIPO_GENERAL							= 2;


	public static String getRutaFiles(FacesContext facesContext, String carpeta) {
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String realPath = servletContext.getRealPath("/") + carpeta;
		System.out.println("Ruta logo y/o jasper: " + realPath);
		return realPath;
	}

	// Listas
	public String getLIST_EMPTY() {
		return "No se encontraron resultados.";
	}

	public int getLIST_SIZE() {
		return 15;
	}

	public String getProceso1PlanillaNumeroHijos() {
		return PROCESO_1;
	}

	public String getProceso2PlanillaListaRevista() {
		return PROCESO_2;
	}

	public String getMensajeGenericTipoMensajeInfo() {
		return MENSAJE_GENERIC_TIPO_MENSAJE_INFO;
	}

	public String getMensajeGenericTipoMensajeError() {
		return MENSAJE_GENERIC_TIPO_MENSAJE_ERROR;
	}

	public String getMensajeGenericTipoMensajeWarning() {
		return MENSAJE_GENERIC_TIPO_MENSAJE_WARNING;
	}

	public int getGenericMensajeDtHijo() {
		return GENERIC_MENSAJE_DT_HIJO;
	}

	public int getGenericMensajeDtPadre() {
		return GENERIC_MENSAJE_DT_PADRE;
	}

	public String getTablaEstadoActivo() {
		return TABLA_ESTADO_ACTIVO;
	}

	public String getTablaEstadoInactivo() {
		return TABLA_ESTADO_INACTIVO;
	}

	public Integer getListarEjbRemote() {
		return LISTAR_EJB_REMOTE;
	}

	public String getMensajeRespuestaErrorGeneral() {
		return MENSAJE_RESPUESTA_ERROR_GENERAL;
	}

	public String getMensajeRespuestaCorrecta() {
		return MENSAJE_RESPUESTA_CORRECTA;
	}

	public String getMensajeFechaFormatoMal() {
		return MENSAJE_FECHA_FORMATO_MAL;
	}

	public String getMensajeExcelError() {
		return MENSAJE_EXCEL_ERROR;
	}

	public String getMensajeVacio() {
		return MENSAJE_VACIO;
	}

	public String getMensajeRespuestaErrorFamilia() {
		return MENSAJE_RESPUESTA_ERROR_FAMILIA;
	}

	public String getMensajeRespuestaErrorVerificarBanco() {
		return MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO;
	}

	public String getMensajeRespuestaErrorConceptoIngreso() {
		return MENSAJE_RESPUESTA_ERROR_CONCEPTO_INGRESO;
	}

	public String getMensajeRespuestaErrorDescuentoLey() {
		return MENSAJE_RESPUESTA_ERROR_DESCUENTO_LEY;
	}

	public String getProceso1() {
		return PROCESO_1;
	}

	public String getProceso2() {
		return PROCESO_2;
	}

	public String getProceso3() {
		return PROCESO_3;
	}

	public String getProceso4() {
		return PROCESO_4;
	}

	public String getProceso11() {
		return PROCESO_11;
	}

	public String getProceso12() {
		return PROCESO_12;
	}

	public String getProceso15() {
		return PROCESO_15;
	}

	public String getProceso16() {
		return PROCESO_16;
	}

	public String getProceso17() {
		return PROCESO_17;
	}

	public String getProceso5() {
		return PROCESO_5;
	}

	public String getProceso6() {
		return PROCESO_6;
	}

	public String getProceso7() {
		return PROCESO_7;
	}

	public String getProceso8() {
		return PROCESO_8;
	}

	public String getProceso9() {
		return PROCESO_9;
	}

	public String getProceso10() {
		return PROCESO_10;
	}

	public String getProceso13() {
		return PROCESO_13;
	}

	public String getProceso14() {
		return PROCESO_14;
	}
	
	
	


}


