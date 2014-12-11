/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.commons.convert;

import java.nio.charset.Charset;

/**
 *
 * @author DIEGO
 */
public class MainRead {

    /**
     * @param args the command line arguments
     */
   public static void main(String args[])
        throws Exception
    {
        //DBFReader dbfreader = new DBFReader((new URL("http://www.svcon.com/us48st.dbf")).openStream());
        //DBFReader dbfreader = new DBFReader("F:\\work\\book2.dbf");
        DBFReader dbfreader = new DBFReader("./testwrite.dbf");
        //DBFReader dbfreader = new DBFReader("E:\\hexiongshare\\test.dbf");
        int i;
        for (i=0; i<dbfreader.getFieldCount(); i++) {
          System.out.print(dbfreader.getField(i).getName()+"  ");
        }
        System.out.print("\n");
        for(i = 0; dbfreader.hasNextRecord(); i++)
        {
            Object aobj[] = dbfreader.nextRecord(Charset.forName("GBK"));
            for (int j=0; j<aobj.length; j++)
              System.out.print(aobj[j]+"  |  ");
            System.out.print("\n");
        }

        System.out.println("Total Count: " + i);
    }
    
}
