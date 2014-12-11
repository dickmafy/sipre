/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.commons.convert;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author DIEGO
 */
public class MainTxtToDBF {

    private static Integer LONGITUD_OBLIGATORIA = 30;
    private static String cip;
    private static String anio;
    private static String banco;

    public static void main(String[] args) {
        try {
            int numeroColumnas = 3;
            int numeroFilas = 100;
            //C STRING;N NUMBER ; L BOOLEAN , D DECIMAL , F FLOAT
            JDBField[] fields = {
                new JDBField("ID", 'C', 100, 0),
                new JDBField("Name", 'C', 100, 0),
                new JDBField("Numero", 'C', 100, 0), //new JDBField("TestF", 'F', 20, 6),
            //new JDBField("TestD", 'D', 8, 0)
            };

            //DBFReader dbfreader = new DBFReader("E:\\hexiong\\work\\project\\book2.dbf");
			String nombreDBF = "test_text_to_dbf.dbf";
			String nombreText = "test.txt";

			DBFWriter dbfwriter = new DBFWriter(nombreDBF, fields);
            Object[][] records = new Object[numeroFilas][numeroColumnas];
            int contador = 0;
			BufferedReader br = new BufferedReader(new FileReader(nombreText));
            String texto;
            while ((texto = br.readLine()) != null) {
                contador++;
                if (texto.trim().length() > 0 && texto.length() == LONGITUD_OBLIGATORIA) {
                    System.out.println("###FILA : " + contador);
                    cip = texto.substring(0, 9);// texto >= 0 && >=10
                    System.out.println(cip);
                    anio = texto.substring(9, 15);
                    System.out.println(anio);
                    banco = texto.substring(15, 30);
                    System.out.println(banco);
                    System.out.println("################");

                    //Object[][] matrix = new Object[rows][cols];
                    Object v1 = cip;
                    Object v2 = anio;
                    Object v3 = banco;

                    //for (int filas = 0; filas < numeroFilas; filas++) {
                    // for (int i = 0; i < 3; i++) {
                    records[contador - 1][0] = v1;
                    records[contador - 1][1] = v2;
                    records[contador - 1][2] = v3;
                        //}
                    //}

                } else {
                    System.out.println("El archivo no cumple la longitud permitida. El Archivo tiene: " + texto.length());
                }
            }

            for (Object[] record : records) {
                dbfwriter.addRecord(record);
            }

             dbfwriter.close();
            System.out.println("DBF CREADO EN LA RAIZ.......");

        } catch (Exception e) {
            e.printStackTrace();
         
        }
     
    }
}
