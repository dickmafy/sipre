package pe.mil.ejercito.sipr.commons;

import java.io.Serializable;

public class ConfiguracionDefault implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String IP;
	public static final String INSTANCIA;
	public static final String USUARIO;
	public static final String PASSWORD;
	public static final String PUERTO;

	public static final String RUTA_FILE_SYSTEM;
	public static final String LOGO;
	public static final String IMAGES;
	public static final String REPORT_BOLETA;
	public static final String REPORT_BOLETA_JXML;
	public static final String REPORT_INGRESO;
	public static final String REPORT_DESCUENTO;
	public static final String RUTA_REPORT_FILE;

	// VARIABLES CORREO
	public static final String HOST;
	public static final String STARTTLS_ENABLE;
	public static final String PORT;
	public static final String USER;
	public static final String USER_ADMIN;
	public static final String AUTH;
	public static final String INTERNET_ADDRESS;
	public static final String BCC;
	public static final String TRANSPORT;
	public static final String EMAIL_CONNECT;
	public static final String EMAIL_PASSWORD;
	public static final String URL_CONFIRMACION;
	public static final int PORT_CONFIRMACION;
	public static final String  CHARSET;
			public static final String 		CONTENT_TYPE;
					public static final String 		VARIABLE_CONFIRMACION;

	static {

		RUTA_FILE_SYSTEM = "system_file/";
		LOGO = "images\\logo_ejercito.png";
		IMAGES = "images";
		REPORT_BOLETA = "jasper\\report_boleta.jasper";
		REPORT_INGRESO = "jasper\\report_percibo.jasper";
		REPORT_DESCUENTO = "jasper\\report_descuento.jasper";
		RUTA_REPORT_FILE = "reporte_file\\";
		
		REPORT_BOLETA_JXML= "jasper\\report_boleta.jrxml";

		IP = "localhost";
		INSTANCIA = "xe";
		USUARIO = "US_SIPRE";
		PASSWORD = "SIPRE";
		PUERTO = "1521";

		HOST = "11.160.121.76";
		STARTTLS_ENABLE = "false";
		PORT = "25";
		USER = "sistemasgart@osinerg.gob.pe";
		USER_ADMIN = "sistemasgart@osinerg.gob.pe";
		AUTH = "true";
		INTERNET_ADDRESS = "sistemasgart@osinerg.gob.pe";
		BCC = "sistemasgart@osinerg.gob.pe";
		TRANSPORT = "smtp";
		EMAIL_CONNECT = "sistemasgart@osinerg.gob.pe";
		EMAIL_PASSWORD = "sistgart2012";
		URL_CONFIRMACION = "192.168.101.27";
		PORT_CONFIRMACION = 23302;
		CHARSET="utf-8";
		CONTENT_TYPE="html";
		VARIABLE_CONFIRMACION="key";
		
	}

}
