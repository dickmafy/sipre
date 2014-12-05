package pe.mil.ejercito.sipr.planilla;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class CalculaFecha {
	//TEST
	public static void main(String[] args) {
		//Accedemos al metodo estatico a travnis del nombre de nuestra clase
		System.out.println(CalculaFecha.calcularEdad("23/12/1987"));
		/**Podemos quitar el static al metodo y se accedernia asni:
		CalculaFecha cal= new CalculaFecha();
		cal.calcularEdad("01-01-1999");*/

	}

	//Este es el metodo calcularEdad
	public static Integer calcularEdad(String fecha) {
		Date fechaNac = null;
		try {
			/**Se puede cambiar la mascara por el formato de la fecha
			que se quiera recibir, por ejemplo anio mes dnia "yyyy-MM-dd"
			en este caso es dnia mes anio*/
			fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (Exception ex) {
			System.out.println("Error:" + ex);
		}
		Calendar fechaNacimiento = Calendar.getInstance();
		//Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		//Se asigna la fecha recibida a la fecha de nacimiento.
		fechaNacimiento.setTime(fechaNac);
		//Se restan la fecha actual y la fecha de nacimiento
		int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		System.out.println("anio :" + anio);
		int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
		System.out.println("mes :" + mes);
		int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
		System.out.println("dia :" + dia);
		//Se ajusta el anio dependiendo el mes y el dnia
		if (mes < 0 || (mes == 0 && dia < 0)) {
			anio--;
		}
		//Regresa la edad en base a la fecha de nacimiento
		return anio;
	}
}