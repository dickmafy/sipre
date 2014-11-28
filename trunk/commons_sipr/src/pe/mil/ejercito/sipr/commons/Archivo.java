package pe.mil.ejercito.sipr.commons;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Archivo implements Serializable{

    
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public byte[] obtenerBytes(InputStream is, int tope) {
	        DataInputStream dis = new DataInputStream(is);
	        byte[] bytes = new byte[tope];
	        try {
	            dis.readFully(bytes);
	            if (dis != null) {
	                dis.close();
	                is.close();
	            }
	        } catch (java.io.IOException ioe) {
	            ioe.printStackTrace();
	        }
	        return bytes;
	    }
	  
	   public void guardarArchivo(byte[] data, String path, String fileName)  {
	        OutputStream out;
	        File file = new File(path);
	        file.mkdirs();
	        try {
	            out = new FileOutputStream(path + fileName);
	            out.write(data);

	            out.flush();
	            out.close();
	            data = new byte[1];
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	   
	       public boolean copiarArchivo(String destino, String origen) {
	        boolean est = false;
	        try {
	            File inFile = new File(origen);
	            File outFile = new File(destino);
	            FileInputStream in = new FileInputStream(inFile);
	            FileOutputStream out = new FileOutputStream(outFile);
	            int c;
	            while ((c = in.read()) != -1) {
	                out.write(c);
	            }
	            in.close();
	            out.close();
	        } catch (IOException e) {
	        }
	        return est;
	    }
}