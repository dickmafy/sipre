/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.commons.convert;

/**
 *
 * @author DIEGO
 */
public class MainWrite {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
            throws Exception {
        
        //C STRING;N NUMBER ; L BOOLEAN , D DECIMAL , F FLOAT
        int numeroColumnas = 3;
        int numeroFilas=100;
        JDBField[] fields = {
            new JDBField("ID", 'C', 8, 0),
            new JDBField("Name", 'C', 32, 0),
            new JDBField("Numero", 'N', 20, 0),
            //new JDBField("TestF", 'F', 20, 6),
            //new JDBField("TestD", 'D', 8, 0)
        };
        //DBFReader dbfreader = new DBFReader("E:\\hexiong\\work\\project\\book2.dbf");
        DBFWriter dbfwriter = new DBFWriter("./testwrite3.dbf", fields);

        //Object[][] matrix = new Object[rows][cols];
        Object v1 = "Texto1";
        Object v2 = "Texto2";
        Object v3 = 1000;
        
        Object[][] records = new Object[numeroFilas][numeroColumnas];
        for (int filas = 0; filas < numeroFilas; filas++) {
           // for (int i = 0; i < 3; i++) {
                records[filas][0] = v1;
                records[filas][1] = v2;
                records[filas][2] = v3;
            //}
        }
        for (Object[] record : records) {
            dbfwriter.addRecord(record);
        }
        dbfwriter.close();
        System.out.println("testwrite.dbf write finished.......");
    }

}
