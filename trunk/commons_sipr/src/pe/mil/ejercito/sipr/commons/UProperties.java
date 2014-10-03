package pe.mil.ejercito.sipr.commons;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class UProperties {
	public UProperties() {
		super();
	}

	public static String getMessage(String fichero, String key, String[] args) {
		try {
			ResourceBundle res = ResourceBundle.getBundle(fichero);
			MessageFormat format = new MessageFormat((String) res.getObject(key));
			return format.format(args);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	/**
	 * Resumen. Recuperar el mensaje ubicado en la llave presente en el archivo
	 * fichero.
	 * 
	 * @param fichero
	 *            : Nombre del archivo fichero, tipo String.
	 * @param key
	 *            : Llave presente en el archivo fichero, tipo String.
	 * @return getMessage(fichero, key, (String [])null) : Mensaje del fichero.
	 */
	public static String getMessage(String fichero, String key) {
		return getMessage(fichero, key, (String[]) null);
	}

	/**
	 * Resumen. Recuperar el mensaje ubicado en la llave presente en el archivo
	 * fichero.
	 * 
	 * @param fichero
	 *            : Nombre del archivo fichero, tipo String.
	 * @param key
	 *            : Llave presente en el archivo fichero, tipo String.
	 * @param arg0
	 *            : Argumento a presentar en el mensaje, tipo String.
	 * @return getMessage(fichero, key, args) : Mensaje del Fichero.
	 */
	public static String getMessage(String fichero, String key, String arg0) {
		String[] args = new String[] { arg0 };
		return getMessage(fichero, key, args);
	}

	/**
	 * Resumen. Recuperar el mensaje ubicado en la llave presente en el archivo
	 * fichero.
	 * 
	 * @param fichero
	 *            : Nombre del archivo fichero, tipo String.
	 * @param key
	 *            : Llave presente en el archivo fichero, tipo String.
	 * @param arg0
	 *            : Primer argumento a presentar en el mensaje, tipo String.
	 * @param arg1
	 *            : Segundo argumento a presentar en el mensaje, tipo String.
	 * @return getMessage(fichero, key, args) : Mensaje del fichero.
	 */
	public static String getMessage(String fichero, String key, String arg0, String arg1) {
		String[] args = new String[] { arg0, arg1 };
		return getMessage(fichero, key, args);
	}

	/**
	 * Resumen. Recuperar el mensaje ubicado en la llave presente en el archivo
	 * fichero.
	 * 
	 * @param fichero
	 *            : Nombre del archivo fichero, tipo String.
	 * @param key
	 *            : Llave presente en el archivo fichero, tipo String.
	 * @param arg0
	 *            : Primer argumento a presentar en el mensaje, tipo String.
	 * @param arg1
	 *            : Segundo argumento a presentar en el mensaje, tipo String.
	 * @param arg2
	 *            : Tercer argumento a presentar en el mensaje, tipo String.
	 * @return getMessage(fichero, key, args) : Mensaje del fichero.
	 */
	public static String getMessage(String fichero, String key, String arg0, String arg1, String arg2) {
		String[] args = new String[] { arg0, arg1, arg2 };
		return getMessage(fichero, key, args);
	}

	/**
	 * Resumen. Recuperar el mensaje ubicado en la llave presente en el archivo
	 * fichero.
	 * 
	 * @param fichero
	 *            : Nombre del archivo fichero.
	 * @param key
	 *            : Llave presente en el archivo fichero, tipo String.
	 * @param arg0
	 *            : Primer argumento a presentar en el mensaje, tipo String.
	 * @param arg1
	 *            : Segundo argumento a presentar en el mensaje, tipo String.
	 * @param arg2
	 *            : Tercer argumento a presentar en el mensaje, tipo String.
	 * @param arg3
	 *            : Cuarto argumento a presentar en el mensaje, tipo String.
	 * @return getMessage(fichero, key, args) : Mensaje del fichero.
	 */
	public static String getMessage(String fichero, String key, String arg0, String arg1, String arg2, String arg3) {
		String[] args = new String[] { arg0, arg1, arg2, arg3 };
		return getMessage(fichero, key, args);
	}
}