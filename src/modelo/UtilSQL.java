package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilSQL {
	
    public static PreparedStatement prepareStatement
        (Connection connection, String sql, boolean returnGeneratedKeys, Object... values)
            throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(sql,
            returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(preparedStatement, values);
        return preparedStatement;
    }

   
    public static void setValues(PreparedStatement preparedStatement, Object... values)
        throws SQLException{
        for (int i = 0; i < values.length; i++) {        	
        		preparedStatement.setObject(i + 1, values[i]);
        }
    }

    
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Closing Connection failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Closing Statement failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

   
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("Closing ResultSet failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

   
    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }
    


  
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }
    
    /**
	 * @return un arreglo de tipo String con los nombres de las columnas de la tabla 
	 * cancha
	 * @throws SQLException
	 */
    public static String[] getNombreColumnas(Connection conexion,String sql) throws SQLException{        
      
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int Columnas = resultSet.getMetaData().getColumnCount();
        String [] nombres = new String [Columnas];
        for(int i = 0 ; i < Columnas; i++){
            nombres[i]=resultSet.getMetaData().getColumnName(i+1);
        }
        close(preparedStatement);
        close(resultSet);
        return nombres;
        
    }
    /**
     * 
     * @param columna de tipo ResulSet
     * @return el numero de columas de una tabla
     * @throws SQLException
     */
    public static int getColumnas(ResultSet $columna) throws SQLException{
        return  (int)$columna.getMetaData().getColumnCount();
                
    }
    
    /**
     * @param columna de tipo ResultSet
     * @return el numero de filas de una consulta
     * @throws SQLException
     */
    public static int getFilas(ResultSet $columna) throws SQLException{
        int $cantidadFilas = 0;
        while($columna.next()){
            $cantidadFilas++;
        }
        return $cantidadFilas;
    
    }
    
    public static  Object[][] GetStructTable(Connection conexion, String sql) throws SQLException{
    	PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        ResultSet t2 = preparedStatement.executeQuery();   
        int Filas = getFilas(t2);
        ResultSet t3 = preparedStatement.executeQuery(); 
        int Columnas = getColumnas(t3);
        Object[][] Datos = new Object[Filas][Columnas];
      
        for(int i = 0; i < Filas;i++){
            while(t3.next()){
                int cont =1;
                while(cont <= Columnas){
                Datos[i][cont-1] = t3.getObject(cont);
                System.out.println(t3.getObject(cont));
                cont++;
                }
                i++;
            }
        }    
       close(preparedStatement);
       close(t2);
       close(t3);
       return Datos;
    }


	public static void close(PreparedStatement preparedStatement,
			ResultSet resultSet) {
		 close(resultSet);
	        close(preparedStatement);
	        
	}
}
