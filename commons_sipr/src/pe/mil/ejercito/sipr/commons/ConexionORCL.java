package pe.mil.ejercito.sipr.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionORCL {
	
	  private static String IP;
	    private static String instancia;
	    private static String usuario;
	    private static String password;
	    private static String puerto;
	    private static boolean isInstance;
	    static {
	        IP = "";
	        instancia = "";
	        usuario = "";
	        password = "";
	        isInstance = false;
	        puerto = "";
	    }

	    public ConexionORCL() {
	        super();
	    }

	   
		public void setValores(String IP, String instancia, String puerto, String usuario, String password) {
	        this.IP = IP;
	        this.instancia = instancia;
	        this.usuario = usuario;
	        this.password = password;
	        this.puerto = puerto;
	        isInstance = true;
	    }

	    private static Connection CONEXION;

	    /**
	     * Metodo que me permite conectar a la base de datos
	     * @return
	     * @throws java.lang.ClassNotFoundException
	     * @throws java.sql.SQLException
	     */
	    public static boolean conectar() throws ClassNotFoundException, SQLException {
	        String url = "jdbc:oracle:thin:@" + IP + ":" + puerto + ":" + instancia;
	        String cadenaConexion = "oracle.jdbc.driver.OracleDriver";
	        Class.forName(cadenaConexion);
	        CONEXION = DriverManager.getConnection(url, usuario, password);
	        if (CONEXION != null) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static Connection getConexion() throws ClassNotFoundException, SQLException {
	        return CONEXION;
	    }

	    /**
	     * Cierra la conexion de un objeto tipo connection
	     * @throws java.sql.SQLException
	     */
	    public static void cerrarConexion() throws SQLException {
	        CONEXION.close();
	    }

	    public static boolean isInstance() {
	        return isInstance;
	    }

	    public static void setInstance(boolean instance) {
	        isInstance = instance;
	    }

}
