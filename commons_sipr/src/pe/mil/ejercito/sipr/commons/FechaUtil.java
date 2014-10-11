package pe.mil.ejercito.sipr.commons;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class FechaUtil {
	private static SimpleDateFormat DATE_FORMAT = null;

	public FechaUtil() {

	}

	public static String getMaskedDate(String mascara, Date fecha) {
		DATE_FORMAT = new SimpleDateFormat(mascara, new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));

		return DATE_FORMAT.format(fecha);
	}

	public static String getYear() {
		DATE_FORMAT = new SimpleDateFormat("yyyy", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));

		Date fechaDate = new Date();
		return DATE_FORMAT.format(fechaDate);
	}

	public static String getDateToString(Date d) {
		if (d != null) {
			DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy",
					new Locale("es_ES"));
			DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
			return DATE_FORMAT.format(d);
		}
		return "";
	}

	public static String getFechaActual() {
		DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
		Date fechaDate = new Date();
		return DATE_FORMAT.format(fechaDate);
	}

	public static Date getStringToDate(String fecha) throws ParseException {
		DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
		return DATE_FORMAT.parse(fecha);
	}

	public static int diasDiferencia(Date inicio, Date fin) {
		long segInicio = inicio.getTime();
		long segFin = fin.getTime();
		long diferencia = segFin - segInicio;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static int diasDiferencia(String sInicio, String sFin)
			throws ParseException {
		Date inicio = FechaUtil.getStringToDate(sInicio);
		Date fin = FechaUtil.getStringToDate(sFin);

		long segInicio = inicio.getTime();
		long segFin = fin.getTime();
		long diferencia = segFin - segInicio;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static boolean fechaEnRango(Date fecha, Date rangoInicio,
			Date rangoFin) {
		if (fecha == null || rangoInicio == null || rangoFin == null) {
			return false;
		}

		System.out.println("Chequeo Rango de Fechas -> Fecha:"
				+ FechaUtil.getDateToString(fecha) + " en rango["
				+ FechaUtil.getDateToString(rangoInicio) + "/"
				+ FechaUtil.getDateToString(rangoFin) + "]");

		long f = fecha.getTime();
		long ri = rangoInicio.getTime();
		long rf = rangoFin.getTime();

		if (f - ri >= 0 && rf - f >= 0) {
			return true;
		}
		return false;
	}

	public static boolean fechaMayorOIgualQue(Date fecha, Date aComparar) {
		long f = fecha.getTime();
		long ri = aComparar.getTime();

		if (f - ri >= 0) {
			return true;
		}
		return false;
	}

	public static boolean fechaMayorQue(Date fecha, Date aComparar) {
		long f = fecha.getTime();
		long ri = aComparar.getTime();

		if (f - ri > 0) {
			return true;
		}
		return false;
	}

	public static boolean horaEnRango(String hora, String horaInicio,
			String horaFin) throws ParseException {
		DATE_FORMAT = new SimpleDateFormat("H:m", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));

		Long horaAux = DATE_FORMAT.parse(hora).getTime();
		Long horaInicioAux = DATE_FORMAT.parse(horaInicio).getTime();
		Long horaFinAux = DATE_FORMAT.parse(horaFin).getTime();

		if (horaAux >= horaInicioAux && horaAux <= horaFinAux) {
			return true;
		}
		return false;
	}

	public static String getFechaYHoraActual() {
		DATE_FORMAT = new SimpleDateFormat("H:m:s dd/MM/yyyy", new Locale(
				"es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT")); // TODO: Ver
																// despues como
																// hacer para no
																// hardcodear
																// esto
		Date fecha = new Date();
		return DATE_FORMAT.format(fecha);
	}

	public static Date fechaMas(Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, dias);
		return new Date(cal.getTimeInMillis());
	}

	public static Date fechaMenos(Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, -dias);
		return new Date(cal.getTimeInMillis());
	}

	public static Date getToday(Date d) // Devuelve la fecha pasada por
										// parametros pero sin horas ni minutos
										// (fecha en hora cero)
	{
		// el dia de hoy sin horas ni nada.
		GregorianCalendar ddate = new GregorianCalendar();
		ddate.setTime(d);
		GregorianCalendar ddateday = new GregorianCalendar(
				ddate.get(GregorianCalendar.YEAR),
				ddate.get(GregorianCalendar.MONTH),
				ddate.get(GregorianCalendar.DAY_OF_MONTH));
		return ddateday.getTime();
	}

	public static int getYearFromDate(Date date) {
		int result = -1;
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			result = cal.get(Calendar.YEAR);
		}
		return result;
	}

	public static Date fechaStringToDate(String fecha) {
		Date fechaDate = new Date();
		try {
			DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy",
					new Locale("es_ES"));
			fechaDate = DATE_FORMAT.parse(fecha);
		} catch (Exception e) {
			new Exception();
		}
		return fechaDate;
	}

	public static String getFecha_ddMMyyyy() {

		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");

		return formato.format(calendar.getTime());
	}

	public static Date StringToDate(String fecha) {

		DateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
		Date horaActual = new Date();

		fecha = fecha + " " + horaFormato.format(horaActual);

		DateFormat fechaFormato;
		Date dateActual = null;
		fechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (fecha == null || fecha.equals("")) {
			return null;
		} else {
			try {
				dateActual = fechaFormato.parse(fecha);
			} catch (ParseException e) {
				// e.printStackTrace();
			}

			return dateActual;
		}

	}

	public static Date StringToDateSinHora(String fecha) {

		DateFormat fechaFormato;
		Date dateActual = null;
		fechaFormato = new SimpleDateFormat("dd/MM/yyyy");

		if (fecha == null || fecha.equals("")) {
			return null;
		} else {
			try {
				dateActual = fechaFormato.parse(fecha);
			} catch (ParseException e) {
				// e.printStackTrace();
			}

			return dateActual;
		}

	}

	public static Double StringToDouble(String valor) {

		if (valor.equals("")) {
			return 0.0;
		} else {
			valor = valor.replace(",", "");
			return Double.parseDouble(valor);
		}
	}

	public static Integer StringToInteger(String valor) {

		if (valor.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(valor);
		}
	}

	public static boolean IntegerToBoolean(Integer valor) {

		if (valor == 1) {
			return true;
		} else {
			return false;
		}

	}

	public static String IntegerToResponse(Integer valor) {

		if (valor == 1) {
			return "Si";
		} else {
			return "No";
		}

	}

	public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (ParseException ex) {
		}
		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (ParseException ex) {
		}
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static String obtenerRutaTemporales() {

		File currentDirectory = new File(new File(".").getAbsolutePath());

		String ruta = currentDirectory.getAbsolutePath();
		int indice = ruta.indexOf("bin");
		ruta = ruta.substring(0, indice - 1) + "\\standalone\\tmp\\";

		System.out.println("Ruta temporales: " + ruta);

		return ruta;
	}

	public static String obtenerRutaTemporal() {
		File currentDirectory = new File(new File(".").getAbsolutePath());

		String ruta = currentDirectory.getPath();
		int indice = ruta.indexOf("bin");
		ruta = ruta.substring(0, indice - 1);
		ruta = ruta + obtenerSeparadorCarpetas() + "standalone"
				+ obtenerSeparadorCarpetas() + "tmp"
				+ obtenerSeparadorCarpetas();

		System.out.println("Ruta temporales: " + ruta);

		return ruta;
	}

	public static boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("win") >= 0);

	}

	public static String obtenerSeparadorCarpetas() {
		if (isWindows()) {
			return "\\";
		} else {
			return "/";
		}
	}

}
