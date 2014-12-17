package pe.mil.ejercito.sipr.commons;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class UValidacion {
	private static SimpleDateFormat	DATE_FORMAT	= null;
	private static int				contador	= 1;

	public UValidacion() {

	}

	/* validar nulo o vacio un String */
	public static boolean esNuloOVacio(String valor) {
		if (valor != null && !valor.equals("")) {
			return false;
		} else {
			return true;
		}

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

	public static String getFechaActual() {
		DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
		Date fechaDate = new Date();
		return DATE_FORMAT.format(fechaDate);
	}

	public static int getDiasDiferencia(Date inicio, Date fin) {
		long segInicio = inicio.getTime();
		long segFin = fin.getTime();
		long diferencia = segFin - segInicio;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static int getDiasDiferencia(String sInicio, String sFin) throws ParseException {
		Date inicio = UValidacion.ConvertStringToDate2(sInicio);
		Date fin = UValidacion.ConvertStringToDate2(sFin);

		long segInicio = inicio.getTime();
		long segFin = fin.getTime();
		long diferencia = segFin - segInicio;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static boolean fechaEnRango(Date fecha, Date rangoInicio, Date rangoFin) {
		if (fecha == null || rangoInicio == null || rangoFin == null) {
			return false;
		}

		System.out.println("Chequeo Rango de Fechas -> Fecha:" + UValidacion.ConvertDateToString(fecha) + " en rango["
				+ UValidacion.ConvertDateToString(rangoInicio) + "/" + UValidacion.ConvertDateToString(rangoFin) + "]");

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

	public static boolean horaEnRango(String hora, String horaInicio, String horaFin) throws ParseException {
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
		DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
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

	// Devuelve la fecha pasada por 	// parametros pero sin horas ni minutos 	// (fecha en hora cero)
	public static Date getToday(Date d) {
		// el dia de hoy sin horas ni nada.
		GregorianCalendar ddate = new GregorianCalendar();
		ddate.setTime(d);
		GregorianCalendar ddateday = new GregorianCalendar(ddate.get(GregorianCalendar.YEAR), ddate.get(GregorianCalendar.MONTH),
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

	public static String ConvertDateToString(Date d) {
		if (d != null) {
			DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
			DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
			return DATE_FORMAT.format(d);
		}
		return "";
	}

	public static Date ConvertStringToDate2(String fecha) throws ParseException {
		DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(-5, "GMT"));
		return DATE_FORMAT.parse(fecha);
	}

	public static Date ConvertStringToDate3(String fecha) {
		Date fechaDate = new Date();
		try {
			DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
			fechaDate = DATE_FORMAT.parse(fecha);
		} catch (Exception e) {
			new Exception();
		}
		return fechaDate;
	}

	public static Date ConvertStringToDateSinHora(String fecha) {

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

	public static Date ConvertStringToDateConHora(String fecha) {

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

	public static String getFecha_ddMMyyyy() {

		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");

		return formato.format(calendar.getTime());
	}

	public static Double ConcertStringToDouble(String valor) {

		if (valor.equals("")) {
			return 0.0;
		} else {
			valor = valor.replace(",", "");
			return Double.parseDouble(valor);
		}
	}

	public static Integer ConvertStringToInteger(String valor) {

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

	public static int getDiasDiferenciaEntreFechas(Date fechaInicial, Date fechaFinal) {
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
		ruta = ruta + obtenerSeparadorCarpetas() + "standalone" + obtenerSeparadorCarpetas() + "tmp" + obtenerSeparadorCarpetas();

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

	/**
	 * ejemplo dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public static Integer getEdad(String fecha) {
		Date fechaNac = null;
		try {
			/**
			 * Se puede cambiar la mascara por el formato de la fecha que se
			 * quiera recibir, por ejemplo a�o mes d�a "yyyy-MM-dd" en este caso
			 * es dia mes ano
			 */
			fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (Exception ex) {
			System.out.println("Error:" + ex);
		}
		Calendar fechaNacimiento = Calendar.getInstance();
		// Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Se asigna la fecha recibida a la fecha de nacimiento.
		fechaNacimiento.setTime(fechaNac);
		// Se restan la fecha actual y la fecha de nacimiento
		int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		// System.out.println("anio :" + anio);
		int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
		// System.out.println("mes :" + mes);
		int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
		// System.out.println("dia :" + dia);
		// Se ajusta el anio dependiendo el mes y el dia
		if (mes < 0 || (mes == 0 && dia < 0)) {
			anio--;
		}
		// Regresa la edad en base a la fecha de nacimiento
		return anio;
	}

	public static boolean isFechaValida(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static Integer barraProgreso(int contadorActual, int contadorTotal) {

		try {
			Double valorActual = (double) contadorActual;
			Double valorTotal = (double) contadorTotal;
			Double porcentaje = new Double(valorActual / valorTotal);
			contador = (int) (porcentaje * 100);
			if (contador > 100) {
				contador = 100;
			}
		} catch (Exception e) {
			contador = 0;
		}

		return contador;

	}

	public static String multiplicar(String valor1, String valor2) {
		BigDecimal deci1 = new BigDecimal(Double.parseDouble(valor1));
		BigDecimal deci2 = new BigDecimal(Double.parseDouble(valor2));
		deci1 = deci1.setScale(2, BigDecimal.ROUND_HALF_UP);
		deci2 = deci2.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal resultado = deci1.multiply(deci2);
		resultado = resultado.setScale(2, BigDecimal.ROUND_HALF_UP);
		return resultado + "";
	}

	public static String sumar(String valor1, String valor2) {
		BigDecimal deci1 = new BigDecimal(Double.parseDouble(valor1));
		BigDecimal deci2 = new BigDecimal(Double.parseDouble(valor2));
		deci1 = deci1.setScale(2, BigDecimal.ROUND_HALF_UP);
		deci2 = deci2.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal resultado = deci1.add(deci2);

		return resultado.toString();
	}

	public static String restar(String valor1, String valor2) {
		BigDecimal deci1 = new BigDecimal(Double.parseDouble(valor1));
		BigDecimal deci2 = new BigDecimal(Double.parseDouble(valor2));
		deci1 = deci1.setScale(2, BigDecimal.ROUND_HALF_UP);
		deci2 = deci2.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal resultado = deci1.subtract(deci2);

		return resultado.toString();
	}

	public static String dividir(String valor1, String valor2) {
		BigDecimal deci1 = new BigDecimal(Double.parseDouble(valor1));
		BigDecimal deci2 = new BigDecimal(Double.parseDouble(valor2));
		BigDecimal resultado = deci1.divide(deci2, 2, BigDecimal.ROUND_HALF_UP);
		return resultado + "";
	}

	public static String getDateYMDHMSEntreFechasString(String vinicio, String vfinal) {
		Date dinicio = null, dfinal = null;
		long milis1, milis2, diff;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			// PARSEO STRING A DATE
			dinicio = sdf.parse(vinicio);
			dfinal = sdf.parse(vfinal);
		} catch (ParseException e) {
			System.out.println("Se ha producido un error en el parseo");
		}
		//INSTANCIA DEL CALENDARIO GREGORIANO
		Calendar cinicio = Calendar.getInstance();
		Calendar cfinal = Calendar.getInstance();
		//ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
		cinicio.setTime(dinicio);
		cfinal.setTime(dfinal);
		milis1 = cinicio.getTimeInMillis();
		milis2 = cfinal.getTimeInMillis();
		diff = milis2 - milis1;
		// calcular la diferencia en segundos
		long diffSegundos = Math.abs(diff / 1000);
		// calcular la diferencia en minutos
		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;
		// calcular la diferencia en horas
		long diffHoras = (diff / (60 * 60 * 1000));
		// calcular la diferencia en dias
		long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));
		//System.out.println("En segundos: " + diffSegundos + " segundos.");
		//System.out.println("En minutos: " + diffMinutos + " minutos.");
		//System.out.println("En horas: " + diffHoras + " horas.");
		//System.out.println("En dias: " + diffdias + " dias.");
		String devolver = String.valueOf(diffHoras + "H " + restominutos + "m " + diffSegundos + "s ");
		return devolver;
	}

	/**
	 * da la fecha hasta el segundo asi : CONVERTIDO_TXT_A_DBF_2014-12-15_11-47-20.dbf 
	 * @return
	 */
	public static String getDateNameForFileSavedYMD_HMS() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));
		return df.format(new Date());
	}

	/** 
	 * da la fecha hasta el segundo asi : CONVERTIDO_TXT_A_DBF_20141215234729.dbf 
	 * @return
	 */
	public static String getDateNameForFileyyyyMMddHHmmss() {
		SimpleDateFormat fileId = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileNewName = fileId.format(new Date());
		//EJEMPLO 20141215234729
		return fileNewName;
	}

}