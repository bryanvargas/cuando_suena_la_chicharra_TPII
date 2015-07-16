package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import conexion.Conexion;
import entidades.Reserva;

public class ReservasDao implements Daoable<Reserva> {	
	private static final String SQL_INSERT =
			"INSERT INTO reserva VALUES (?,?, ?,?, ?,?,?,?,?)";	
	private static final String SQL_UPDATE = 
			"UPDATE reserva SET estado_reservacion = ? WHERE id_cancha = ? AND n_inventario = ? AND fecha =?";
	private static final String SQL_READ =
			"SELECT * FROM reserva WHERE id_cancha = ? ";
	private static final String SQL_CANCHA_DISPONIBLE =
			"SELECT * FROM reserva WHERE id_cancha = ? AND n_inventario=? AND fecha == ? AND hora_inicio ==? AND hora_fin ==?";	
	private static final String SQL_READALL = 
			"SELECT * FROM Reserva ";
	private static final String SQL_ACTUALIZAR_ESTADO_RESERVA =
			"UPDATE reserva SET estado_reservacion = ? WHERE id_cancha = ? AND n_inventario = ?";
	private static final String SQL_CANTIDAD_RESERVAS = 
			"SELECT n_inventario, COUNT(*) AS cantidad FROM reserva WHERE n_inventario  = ? GROUP BY n_inventario ";
	
	private static final Conexion conn = Conexion.saberEstado();
	
	public static final String[] $CABECERA_TABLA = 
			{"Id Cancha","Id Persona","Fecha","Hora Inicio","Hora Fin","Monto a Pagar",
			 "Estado Reservacion","Duracion", "Seña"};

	@Override
	public boolean create(Reserva reserva){
		Object[] values = {
				reserva.get$id_cancha(),
				reserva.get$id_inventario(),
				reserva.get$fecha(),
				reserva.get$hora_inicio(),
				reserva.get$hora_fin(),
				reserva.get$monto_pagar(),
				reserva.isEstado_reservacion(),
				reserva.get$duracion(),
				reserva.get$senia()
			};	  
		boolean $creacion_exitosa = false;
	        PreparedStatement preparedStatement = null;
	        ResultSet generatedKeys = null; 
	        Conexion con = Conexion.saberEstado();
	        try {
	        	
	             preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_INSERT, true, values);
	             int $filasAfectadas = preparedStatement.executeUpdate();
	             if ($filasAfectadas != 0) {
            	  	generatedKeys = preparedStatement.getGeneratedKeys();
            	  	$creacion_exitosa = true;
	             }	           
	        } catch (SQLException e) {
	        	System.err.println("ERROR create:" + e.getMessage());
	        } finally {
	        	UtilSQL.close(con.getConn(),preparedStatement,generatedKeys);
	        }
	        return $creacion_exitosa;
	}

	@Override
	public boolean delete(Object id) {		
		try {
			throw new Exception("Viola Integridad por referecia. No hay filas afectadas.");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean upDate(Reserva reserva) {
		Conexion con = Conexion.saberEstado();
		Object[] values = {
				reserva.isEstado_reservacion(),
				reserva.get$id_cancha(),
				reserva.get$id_inventario(),
				reserva.get$fecha()				
			};			
		
	        PreparedStatement preparedStatement = null;
	        
	        try {
	            
	            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_UPDATE, false, values);
	            int affectedRows = preparedStatement.executeUpdate();
	            if (affectedRows == 0) {
//	                throw new DAOException("Updating user failed, no rows affected.");
	            	System.err.println("La actualizacion de la Reserva ha fallado, no hay filas afectadas");
	            }
	            return true;
	        } catch (SQLException e) {
	        	System.err.println("ERROR: " + e.getMessage());
	        } finally {
	        	UtilSQL.close(con.getConn(), preparedStatement);
	        }
	        return false;
	}

	@Override
	public Reserva read(Object id) { 
		System.out.println("esto es el id de cancha desde dentro de DAO " + id);
		Conexion con = Conexion.saberEstado();
		Object[] values = { id };		
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reserva reserva = null;
        try {            
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READ, false, values);          
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {             	
            	reserva = mapeoCancha(resultSet);          	 
            }
           return reserva;
        } catch (SQLException e) {
        	System.err.println("ERROR: " + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
        }
        return reserva;
    }


	public boolean CanchaDisponible(int $id_cancha,int $n_invenrario, String $fecha, int $inicio,
			int $fin) { 	
		Conexion con = Conexion.saberEstado();
		Object[] values = {$id_cancha,
						   $n_invenrario,
						   $fecha,
						   $inicio,
						   $fin};		
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean $reserva = false;
        try {            
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_CANCHA_DISPONIBLE, false, values);          
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {             	
            	  $reserva = true;    	 
            }
           
        } catch (SQLException e) {
        	System.err.println("ERROR: " + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
        }
        return $reserva;
    }
	
	
	@Override
	public List<Reserva> readAll() {
		Conexion con =  Conexion.saberEstado();
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;        
        List<Reserva> reserva = new ArrayList<>();
        try { 
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { 
            	reserva.add(mapeoCancha(resultSet));
            }
        } catch (SQLException e) {
        	System.err.println("ERROR: " + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
        }
        return reserva;
	}
	
	
	public boolean upDateControlReserva(boolean reserva, int id_cancha, int n_inventario) {
		Object[] values = {reserva, id_cancha, n_inventario};		
	        PreparedStatement preparedStatement = null;
	        
	        try {
	           
	            preparedStatement = UtilSQL.prepareStatement(conn.getConn(), SQL_ACTUALIZAR_ESTADO_RESERVA, false, values);
	            int affectedRows = preparedStatement.executeUpdate();
	            if (affectedRows == 0) {
	            	System.err.println("La actualizacion de la Reserva ha fallado, no hay filas afectadas");
	            }
	            return true;
	        } catch (SQLException e) {
	        	System.err.println("ERROR: " + e.getMessage());
	        } finally {
	        	UtilSQL.close(conn.getConn(), preparedStatement);
	        }
	        return false;
	}
	
	 /**
	 * @return un arreglo de tipo String con los nombres de las columnas de la tabla 
	 * reserva
	 * @throws SQLException
	 */
    public static String[] getNombreColumnas() throws SQLException{
		return UtilSQL.getNombreColumnas(conn.getConn(),SQL_READALL);        
    }
    public Object[][] tabla() throws SQLException{
   	 	return UtilSQL.GetStructTable(conn.getConn(),SQL_READALL);
    }
    
	private Reserva mapeoCancha(ResultSet resultSet) throws SQLException {		
		return new Reserva(
	            resultSet.getObject("id_cancha"),
	            resultSet.getObject("n_inventario"),
	            resultSet.getString("fecha"),
	            resultSet.getInt("hora_inicio"),
	            resultSet.getInt("hora_fin"),
	            resultSet.getDouble("monto_pagar"),
	            resultSet.getBoolean("estado_reservacion"),
	            resultSet.getInt("duracion"),
	            resultSet.getDouble("senia"));
	}
	
	public Object[] arregloReserva(Reserva reservas){
		Object[] o = {reservas.get$id_cancha(),
					reservas.get$id_inventario(),					
					reservas.get$fecha(),
					reservas.get$hora_inicio(),
					reservas.get$hora_fin(),
					reservas.get$monto_pagar(),				
					reservas.isEstado_reservacion(),
					reservas.get$duracion(),
					reservas.get$senia()};		
		return o;
	}
	public Object[][] readAllArray(List<Reserva> reservas){
		Object[][] reservasArray = new Object[reservas.size()][];
		for (int i = 0; i < reservas.size(); i++) {
			reservasArray[i] = arregloReserva(reservas.get(i));
		}
		return reservasArray;
	}
	public String sqliteDate(Date fecha)
	{
		Date jDate = new Date(fecha.getTime());
	  DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	  return sdf.format(jDate);
	}

	public int cantidadReservas(Object id) {
		Conexion con = Conexion.saberEstado();
		Object[] values = { id };		
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cantidad = 0;
        try {            
            preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_CANTIDAD_RESERVAS, false, values);          
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { 
            	cantidad = resultSet.getInt("cantidad");          	 
            }
           
        } catch (SQLException e) {
           System.err.println("ERROR:" + e.getMessage());
        } finally {
        	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
        }
        return cantidad;

	}

	
}
