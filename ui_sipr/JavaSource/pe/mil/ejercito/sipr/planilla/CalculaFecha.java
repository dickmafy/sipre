package pe.mil.ejercito.sipr.planilla;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author www.JaverosAnonimos.tk
 */
 class CalculaFecha {
    public static void main(String []args){
//Accedemos al metodo estatico a trav�s del nombre de nuestra clase
      System.out.println(CalculaFecha.calcularEdad("23/12/1987"));
/**Podemos quitar el static al metodo y se acceder�a as�:
CalculaFecha cal= new CalculaFecha();
cal.calcularEdad("01-01-1999");*/
    }
//Este es el m�todo calcularEdad que se manda a llamar en el main
    public static Integer calcularEdad(String fecha){
    Date fechaNac=null;
        try {
            /**Se puede cambiar la mascara por el formato de la fecha
            que se quiera recibir, por ejemplo a�o mes d�a "yyyy-MM-dd"
            en este caso es d�a mes a�o*/
            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:"+ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int a�o = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        System.out.println("a�o :" + a�o);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        System.out.println("mes :" + mes);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        System.out.println("dia :" + dia);
        //Se ajusta el a�o dependiendo el mes y el d�a
        if(mes<0 || (mes==0 && dia<0)){
            a�o--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return a�o;
    }
}