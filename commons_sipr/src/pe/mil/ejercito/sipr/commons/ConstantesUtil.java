package pe.mil.ejercito.sipr.commons;


public class ConstantesUtil {

	
	public static final String TABLA_ESTADO_ACTIVO = "A";
	public static final String TABLA_ESTADO_INACTIVO = "I";
	
	public static final String MENSAJE_RESPUESTA_ERROR_GENERAL = "No se pudo completar la operacion, Intentelo mas tarde.";
	public static final String MENSAJE_RESPUESTA_CORRECTA = "La operación se realizó correctamente.";

	
	public static final String URL_LOGIN = "/seguridad/cerrar-session.htm";
	// Datos Generales
	public static final String[] DATOS_GENERALES = { "USURENT", "FECEVAL", "EPLATORIG", "ETIPPROD", "EPROD", "ETIPSOL", "EIPOINMEN", "ETIPCAM", "EFCONSDUP" };
	public static class EstadoValidacion{
		public final static String VALIDACION_VALIDOS = "VALIDACION_IDENTIDAD";
		public final static String VALIDACION_INVALIDOS = "N_VALID_IDENT";

	}

}
