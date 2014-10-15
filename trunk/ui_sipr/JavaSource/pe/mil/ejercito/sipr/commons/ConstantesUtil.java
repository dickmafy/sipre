package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="constantesUtil")
public class ConstantesUtil {

	//Estados Tablas
	public static final String TABLA_ESTADO_ACTIVO = "A";
	public static final String TABLA_ESTADO_INACTIVO = "I";

	//Mensajes Constantes
	public static final String MENSAJE_RESPUESTA_ERROR_GENERAL = "No se pudo completar la operacion, Intentelo mas tarde.";
	public static final String MENSAJE_RESPUESTA_CORRECTA = "La operación se realizó correctamente.";
	public static final String MENSAJE_FECHA_FORMATO_MAL= "La fecha encontrada tiene un formato incorrecto.";
	
	public static final String MENSAJE_EXCEL_ERROR= "No se leyeron correctamente los datos del excel.";
	
	public static final String MENSAJE_VACIO = "";
	
	
	//Listas
	 public String getLIST_EMPTY() { return "No se encontraron resultados."; } 
	 public int getLIST_SIZE() { return 10; }


	
	 


}
