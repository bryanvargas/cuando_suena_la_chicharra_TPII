package conexion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Clase que provee una Conexion a diferentes Bases de datos
 * No es posible extender la clase
 * @author Shingo
 *
 */
public final class Conexion {
	public static Conexion instancia;
	private Connection  conn;
	
	/**	
	 * @return Retorna una conexion de tipo Connection
	 */
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private Conexion(){
		try {
			//conexion a MySQL
//			Class.forName("org.sqlite.JDBC");
//			setConn(DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","shingo", "shingo"));
//			JOptionPane.showMessageDialog(null, "Connection Successful");
			
			//Conexion a SQLite
			Class.forName("org.sqlite.JDBC");
//			setConn(DriverManager.getConnection("jdbc:sqlite:C:\\Mi espacio de trabajo\\reservas.sqlite"));
			setConn(DriverManager.getConnection("jdbc:sqlite:reservaciones.sqlite"));
//			JOptionPane.showMessageDialog(null, "Connection Successful");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Connection No Successful: "+ e.getMessage());
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null, "Connection No Successful: "+ e.getMessage());
		}
		
	}
	
	//synchronized lo que haces es crear una lista de espera
	//si es que existe una conexion masiva a la bases de datos
	//(muchos usuaris se estan conectand)en un
	//momento dado, sincroniza esos hilos
	/** 
	 * @return Retorna una sola instacia de la conexion sincronizada;
	 */
	public synchronized static Conexion saberEstado(){
		return instancia==null?new Conexion():instancia;
	}
	
	public  void cerrarConexion(){
		instancia = null;
	}
	
	/**  *************************************** **/
	/**  ************Codigo Obsoleto************ **/
	/**  *************************************** **/
	public String DirectorioActual(){
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }
	
}
