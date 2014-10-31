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
//Accedemos al metodo estatico a través del nombre de nuestra clase
      System.out.println(CalculaFecha.calcularEdad("23/12/1987"));
/**Podemos quitar el static al metodo y se accedería así:
CalculaFecha cal= new CalculaFecha();
cal.calcularEdad("01-01-1999");*/
    }
//Este es el método calcularEdad que se manda a llamar en el main
    public static Integer calcularEdad(String fecha){
    Date fechaNac=null;
        try {
            /**Se puede cambiar la mascara por el formato de la fecha
            que se quiera recibir, por ejemplo año mes día "yyyy-MM-dd"
            en este caso es día mes año*/
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
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        System.out.println("año :" + año);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        System.out.println("mes :" + mes);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        System.out.println("dia :" + dia);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }
}