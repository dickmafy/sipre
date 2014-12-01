package pe.mil.ejercito.sipr.commons;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDate extends Date implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FORMATO_DD_MM_AA;
	public static final String FORMATO_AA_MM;
	
	static {
		FORMATO_DD_MM_AA = "dd/MM/yyyy";
		FORMATO_AA_MM="yyyyMM ";
		
  }

	
	private static Date fecha;

	public UDate() {
		super();
		fecha = new Date();
	}
	
	
	public static String toStringfecha(Date d,String formato) {
		String f = "";
		try {
		    SimpleDateFormat sd = new SimpleDateFormat(formato);
		    f = sd.format(d);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return f;
	}
	
	public static Date toDatefecha(String d,String formato) {
		
		try {
		    SimpleDateFormat sd = new SimpleDateFormat(formato);
		    Date f = sd.parse(d);
		    return f;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		}
		
	}

	public Date getFecha() {
		return fecha;
	}
}
