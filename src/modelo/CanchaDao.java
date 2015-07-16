package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import conexion.Conexion;
import entidades.Cancha;
import entidades.TipoCesped;

/**
 * 
 * @author Bryan Vargas	
 * @version 0.2
 */
public class CanchaDao implements Daoable<Cancha> {
	private static final String SQL_INSERT =
			"INSERT INTO cancha VALUES (?,?,?,?,?,?)";
	private static final String SQL_DELETE = 
			"DELETE FROM cancha WHERE id_cancha=?";
	private static final String SQL_UPDATE = 
			"UPDATE cancha SET nombre = ?,precio=?,cantidad_max_jugadores=?, descripcion=?,tipo=? WHERE id_cancha = ?";
	private static final String SQL_READ = 
			"SELECT * FROM cancha WHERE id_cancha=?";
	private static final String SQL_READALL = 
			"SELECT * FROM cancha ";
	private static final String SQL_READCANCHASDISPONIBLES = 
			"SELECT * FROM cancha WHERE id_cancha NOT IN(SELECT id_cancha FROM reserva )";
	private static final String SQL_READCANCHASDISPONIBLES_POR_FECHA = 
//			"SELECT * FROM cancha c JOIN reserva r USING(id_cancha) WHERE fecha != ?";
			"SELECT * FROM cancha WHERE id_cancha NOT IN(SELECT id_cancha FROM reserva WHERE fecha == ? )";	
	private static final String SQL_READNOMBRECANCHASDISPONIBLES = 
			"SELECT nombre FROM cancha WHERE id_cancha NOT IN(SELECT id_cancha FROM reserva )";	
	public static final String[] $CABECERA_TABLA =
			{"Inventario","Nombre","Precio","Cantidad Maxima Jugadores",
			 "Descripcion"};
	private int $id_cancha = 0;
	
	/**
	 * @param cancha de tipo Cancha.<br>
	 * 			Ejemplo: Cancha(null, "Las Palmas I", 120.00, 16,
	 * 			 "alguna descripcion",TipoCesped.sintetico )
	 * @return TRUE si la creacion a sido exitosa,<br>
	 * 		   FALSE si la ocurrio algun problema. 
	 */
	@Override
	public boolean create(Cancha cancha) {
		PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;  
        Conexion con = Conexion.saberEstado();
        boolean $creacion_exitosa = false;
		Object[] values = {
			cancha.getId_cancha(),
			cancha.getNombre(),
			cancha.getPrecio(),
			cancha.getMax_jugadores(),
			cancha.getDescripcion(),
			cancha.getTipoCancha()};              
        try {        	
             preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_INSERT, true, values);
             int $filasAfectadas = preparedStatement.executeUpdate();
             if ($filasAfectadas != 0) {
            	  generatedKeys = preparedStatement.getGeneratedKeys();
            	  cancha.setId_cancha(generatedKeys.getInt(1));
            	  set$id_cancha((int) cancha.getId_cancha());
            	  $creacion_exitosa = true;
             }         
        } catch (SQLException e) {
        	System.err.println("ERROR CREATE: " + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(),preparedStatement,generatedKeys);
        }
        return $creacion_exitosa;
	}
	
	/**
	 * @param id de tipo Object de la fila a borrar 			
	 * @return retorna TRUE si la eliminacion  a sido exitosa,<br>
	 * 		   FALSE si la ocurrio algun problema. 
	 */
	@Override
	public boolean delete(Object id) {
		Object[] values = {id};        
        PreparedStatement preparedStatement = null;
        Conexion con=  Conexion.saberEstado();
        boolean $borrado_exitoso = false;
        try {                     
            preparedStatement = UtilSQL.prepareStatement(con.getConn() , SQL_DELETE, false, values);            
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows != 0) {            
            	JOptionPane.showMessageDialog(null, "DELETE TRUE");
				$borrado_exitoso= true;
            }            
        } catch (SQLException e) {
        	System.err.println("ERROR DELETE: "+ e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement);
        }
		return $borrado_exitoso;
	}

	/**
	 * Metodo utilizado para actualizar un campo seleccionado.
	 * @param cancha de tipo Cancha.<br>
	 * 			Ejemplo: Cancha(null, "Las Palmas I", 120.00, 16,
	 * 			 "alguna descripcion",TipoCesped.sintetico )
	 * @return TRUE si la actualizacion a sido exitosa,<br>
	 * 		   FALSE si la ocurrio algun problema. 
	 */
	@Override
	public boolean upDate(Cancha cancha) {
		Object[] values = {
			cancha.getNombre(),
			cancha.getPrecio(),
			cancha.getMax_jugadores(),
			cancha.getDescripcion(),
			cancha.getTipoCancha(),
			cancha.getId_cancha()};	
        PreparedStatement preparedStatement = null;
        Conexion con = Conexion.saberEstado();   
        boolean $actualizacion_exitosa=false;
        try {        
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_UPDATE, false, values);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows != 0) {
            	JOptionPane.showMessageDialog(null, "UPDATE TRUE");
            	$actualizacion_exitosa = true;
            }          
        } catch (SQLException e) {
        	System.err.println("ERROR UPDATE: " + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement);
        }
        return $actualizacion_exitosa;
	}
	/**
	 * Metodo utilizado para obtener un registro de la tabla Cancha.
	 * @param  id de tipo Objeto.<br>  
	 * @return Cancha con el ID pasado como paramentro, <br>
	 * 		   NULL e el caso de que el ID no exista.
	 */
	@Override
	public Cancha read(Object id) {
		Object[] values = { id };
		Conexion con = Conexion.saberEstado();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cancha cancha = null;
        try {            
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READ, false, values);          
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { 
            	cancha = mapeoCancha(resultSet);          	 
            }           
        } catch (SQLException e) {
            System.err.println("ERROR READ: " + e.getMessage() + " Nro:" + e.getErrorCode());
        } finally {
        	UtilSQL.close(con.getConn(),preparedStatement, resultSet);
        }
        return cancha;		
	}	

	/**
	 * Metodo utilizado para obtener todos las filas de la tabla Cancha.	 *  
	 * @return Una lista con todas las filas de la tabla Cancha, <br>
	 * 		   NULL e el caso de que el ID no exista.
	 */
	@Override
	public List<Cancha> readAll() {		
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;       
        List<Cancha> canchas = new ArrayList<>();
        Conexion con = Conexion.saberEstado();
        try {      
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {  
                canchas.add(mapeoCancha(resultSet));
            }           
        } catch (SQLException e) {
        	System.err.println("ERROR READALL: " + e.getErrorCode() + " "+ e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(),preparedStatement, resultSet);
        }
        return canchas;
	}
	
	/**
	 * Metodo utilizado para obtener todas las filas de la tabla Cancha 
	 * en el cual la cancha este disponible. 
	 * @return Una lista con todas las filas de la tabla Cancha, <br>
	 * 		   NULL e el caso de que el ID no exista.
	 */
	public List<Cancha> readCanchasDisponibles() {		
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Conexion con = Conexion.saberEstado();
        List<Cancha> canchas = new ArrayList<>();
        try {          
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READCANCHASDISPONIBLES, false);
//            preparedStatement = con.getConn().prepareStatement(SQL_READCANCHASDISPONIBLES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {  
                canchas.add(mapeoCancha(resultSet));
            }
            return canchas;
        } catch (SQLException e) {
        	System.err.println("ERROR READ CANCHAS DISPONIBLES: " + 
        			e.getErrorCode() + " " + e.getMessage());        	
        } finally {
        	UtilSQL.close(con.getConn(),preparedStatement, resultSet);
        }
        return canchas;
	}
	

	private Cancha mapeoCancha(ResultSet resultSet) throws SQLException {
		TipoCesped t = null;
		if (resultSet.getString("tipo").equals("cesped")) {			
			t = TipoCesped.pasto;
		}else if (resultSet.getString("tipo").equals("sintetico")) {
			t = TipoCesped.sintetico;
		}else if (resultSet.getString("tipo").equals("tierra")) {
			t = TipoCesped.tierra;			
		}	
		return new Cancha(
	            resultSet.getInt("id_cancha"),
	            resultSet.getString("nombre"),
	            resultSet.getDouble("precio"),
	            resultSet.getInt("cantidad_max_jugadores"),
	            resultSet.getString("descripcion"), 
	            t);
	}	 
	public Object[] arregloCanchas(Cancha canchas){
		Object[] o = {canchas.getId_cancha(),
				canchas.getNombre(),					
				canchas.getPrecio(),
				canchas.getMax_jugadores(),
				canchas.getDescripcion()};
		return o;
	}
	
	public Object[][] readAllArray(List<Cancha> canchas){
		System.out.println("cuantas veces entras");
		Object[][] reservasArray = new Object[canchas.size()][];
		for (int i = 0; i < canchas.size(); i++) {
			reservasArray[i] = arregloCanchas(canchas.get(i));
		}
		return reservasArray;
	}
	public List<String> getNombreCanchas() {
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;	       
        List<String> canchas = new ArrayList<>();
        Conexion con = Conexion.saberEstado();
        try {          
		    preparedStatement = UtilSQL.prepareStatement(con.getConn(),
		    		SQL_READNOMBRECANCHASDISPONIBLES, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {  
            	canchas.add(resultSet.getString("nombre"));
            }           
        } catch (SQLException e) {
        	System.err.println("ERROR NOMBRE CANCHAS: " + e.getErrorCode() + " " +
        						e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
        }
        return canchas;
	}
	
	public List<Cancha> disponiblesPorFecha(String $fecha_parseada) {		
		Object[] values = { $fecha_parseada };		
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Conexion con = Conexion.saberEstado();
	    List<Cancha> canchas = new ArrayList<>();
	    try {  
	        preparedStatement = UtilSQL.prepareStatement(con.getConn(), 
	        		SQL_READCANCHASDISPONIBLES_POR_FECHA, false, values);          
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {  
	            canchas.add(mapeoCancha(resultSet));
	        }       
	    } catch (SQLException e) {
//	        System.err.println("Error: " + e.getMessage());
	    } finally {
	    	UtilSQL.close(con.getConn(),preparedStatement, resultSet);
	    }		      
	    return canchas;
	}

	public int get$id_cancha() {
		return $id_cancha;
	}

	public void set$id_cancha(int $id_cancha) {
		this.$id_cancha = $id_cancha;
	}
}
