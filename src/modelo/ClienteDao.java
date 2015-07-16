package modelo;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import conexion.Conexion;
import entidades.Cliente;
import entidades.ClienteNoRegistrado;
import entidades.ClienteRegistrado;
import entidades.TipoCliente;


public class ClienteDao implements Daoable<Cliente> {
	private static final String SQL_INSERTCLIENTE =
			"INSERT INTO cliente VALUES (?,?, ?,?, ?)";
	private static final String SQL_INSERTClLIENTEREGISTRADO =
			"INSERT INTO cliente_registrado(n_inventario,n_cliente,edad,email,foto) VALUES (?,?,?,?,?)";	
	private static final String SQL_INSERTClLIENTE_NO_REGISTRADO =
			"INSERT INTO cliente_noregistrado(n_inventario) VALUES (?)";
	private static final String SQL_DELETE = 
			"DELETE FROM cliente WHERE n_inventario = ?";
	private static final String SQL_UPDATE = 
			"UPDATE cliente_registrado SET telefono = ?,edad=?,email=?, foto=? WHERE n_inventario = ?";
	private static final String SQL_READ =
			"SELECT * FROM cliente c JOIN cliente_registrado cr USING(n_inventario) WHERE cr.n_inventario = ?";
	
	private static final String SQL_READ_DOS =
			"SELECT * FROM cliente c JOIN cliente_noregistrado cr USING(n_inventario) WHERE cr.n_inventario = ?";
	private static final String SQL_ISREGISTRADO =
			"SELECT * FROM cliente WHERE n_inventario = ? AND tipo = 'registrado'";
	private static final String SQL_READ_ID =
			"SELECT n_inventario FROM cliente c JOIN cliente_registrado cr USING(n_inventario) WHERE cr.n_cliente = ?";
	private static final String SQL_READ_ID_NOREGISTRADO =
			"SELECT n_inventario FROM cliente  WHERE nombre = ? AND telefono = ?";
	private static final String SQL_READ_N_CLENTES = "SELECT n_cliente FROM CLIENTE_REGISTRADO";
	private static final String SQL_READALL = 
			"SELECT * FROM cliente JOIN cliente_registrado USING(n_inventario)";	
	private static final String SQL_FOTO = 			
			"select * from cliente_registrado where n_inventario = ?";
	private static final String SQL_READALL_MENOS_CUMPLIDORESDOS = 
			"SELECT *, COUNT(*) AS MENOSCUMPLIDORES FROM cliente JOIN cliente_registrado USING(n_inventario)"
			+ " JOIN reserva USING(n_inventario)"
			+ "where estado_reservacion=0 AND tipo = 'registrado'  GROUP BY n_inventario HAVING MENOSCUMPLIDORES > 1 ORDER BY MENOSCUMPLIDORES DESC";
	public static final String[] $CABECERA_TABLA= 
			{"Inventario","tipo","Nombres","Apellidos","Telefono","Nro Cliente","edad","Email"};
	
	/**
	 * @param Recibe un parametro de tipo ClienteRegistrado,
	 * 			Ej: ClienteRegistrado(null,  ) 
	 */
	@Override
	public boolean upDate(Cliente $cliente) {
		PreparedStatement $preparedStatement = null;
		ClienteRegistrado $cliente_registrado = (ClienteRegistrado) $cliente;
		boolean $actualizacion_exitosa=false;
		Conexion con = Conexion.saberEstado();
		byte[] imageFileArr=getByteArrayImagen($cliente_registrado.getFoto());
		Object[] values = {
				$cliente.getTelefono(),
				$cliente_registrado.get$edad(),
				$cliente_registrado.get$email(),
				imageFileArr,
				$cliente_registrado.get$n_inventario()};		
		try{
			$preparedStatement =  UtilSQL.prepareStatement(con.getConn(), SQL_UPDATE, false, values);			
		    int affectedRows = $preparedStatement.executeUpdate();
            if (affectedRows != 0) {
            	JOptionPane.showMessageDialog(null, "UPDATE TRUE");
            	$actualizacion_exitosa = true;
            }          
	        } catch (SQLException e) {
	        	System.err.println("ERROR UPDATE: " + e.getMessage());
	        } finally {
	        	UtilSQL.close(con.getConn(), $preparedStatement);
	        }
	        return $actualizacion_exitosa;
	}

	@Override
	public boolean delete(Object t) {
		PreparedStatement $preparedStatement = null;
		Conexion con = Conexion.saberEstado();
		boolean $actualizacion_exitosa = false;
		try{
			$preparedStatement =  UtilSQL.prepareStatement(con.getConn(), SQL_DELETE, false);
			$preparedStatement.setString(1, t.toString());
//			pst.execute("CREATE TRIGGER borradoClientes BEFORE DELETE " +
//					"ON cliente FOR EACH ROW BEGIN DELETE FROM cliente_registrado " +
//					"WHERE cliente_registrado.n_inventario = OLD.n_inventario;END;");
		int affectedRows = $preparedStatement.executeUpdate();
		if (affectedRows != 0) {
			JOptionPane.showMessageDialog(null, "UPDATE TRUE");
			$actualizacion_exitosa = true;
		}          
		} catch (SQLException e) {
			System.err.println("ERROR UPDATE: " + e.getMessage());
		} finally {
			UtilSQL.close(con.getConn(), $preparedStatement);
		}
		return $actualizacion_exitosa;
	}
	@Override
	public boolean create(Cliente $cliente)   {
		PreparedStatement $cliente_pst = null;
		PreparedStatement clienteRegistrado = null;
		ResultSet $rs = null;
		boolean $creacion_exitosa = false;
		Conexion con = Conexion.saberEstado();
		ClienteRegistrado cr= (ClienteRegistrado) $cliente; 
		Object[] $values_cliente = {
			$cliente.get$n_inventario(),
			$cliente.getTipo().toString(),
			$cliente.getNombres(),
			$cliente.getApellidos(),
			$cliente.getTelefono()};	 
		int key = 0;
		try{						
			con.getConn().setAutoCommit(false);		
			$cliente_pst = UtilSQL.prepareStatement(con.getConn(), SQL_INSERTCLIENTE, true, $values_cliente);
//			$cliente_pst = con.getConn().prepareStatement(SQL_INSERTCLIENTE,Statement.RETURN_GENERATED_KEYS);
			$cliente_pst.executeUpdate();
			$rs = $cliente_pst.getGeneratedKeys();
			if ($rs != null && $rs.next()) {
			    key = (int) $rs.getLong(1);
			    System.out.println(key + "esto es el key: " + $cliente.get$n_inventario());
			}
			
			byte[] imageFileArr=getByteArrayImagen(cr.getFoto());
			Object[] $values_registrado = {
				key,
				cr.get$n_cliente(),
				cr.get$edad(),
				cr.get$email(),
				imageFileArr};
			clienteRegistrado = UtilSQL.prepareStatement(con.getConn(), SQL_INSERTClLIENTEREGISTRADO, false, $values_registrado);       
			clienteRegistrado.executeUpdate();	
			con.getConn().commit();
			$creacion_exitosa= true;
		}catch(SQLException e){
			System.err.println("ERROR CREATE CLIENTE: " + e.getMessage());
			if (con.getConn() != null) {
				try {
					System.err.print("La trasaccion se deshace ");
					con.getConn().rollback();
				} catch (SQLException ex) {
					e.getMessage();
				}
			}
		}
		finally{
			
			try {
				con.getConn().setAutoCommit(true);
				UtilSQL.close(con.getConn(),$cliente_pst,$rs);		
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return $creacion_exitosa;
	}	
	@Override
	public Cliente read(Object t) {		
		PreparedStatement pst = null;
		Cliente cliente = null;
		ResultSet res = null;
		Conexion con = Conexion.saberEstado();
		Object[] values = {t};
		try{			
			pst = UtilSQL.prepareStatement(con.getConn(), SQL_READ, false, values);			
			res = pst.executeQuery();
			if (res.next()) { 
            	cliente = mapeoCLiente(res);
            }
		}catch(SQLException e){
			System.out.println("ERROR READ CLIENTE: " + e.getMessage());
		}finally{
			UtilSQL.close(con.getConn(),pst, res);
		}
		return cliente;
	}
	public Cliente readRegistrado(Object t) {
		PreparedStatement pst = null;
		ClienteRegistrado cliente = null;
		ResultSet res = null;
		Conexion con = Conexion.saberEstado();
		Object[] values = {t};
		try{				
			pst = UtilSQL.prepareStatement(con.getConn(), SQL_READ, false, values);
			res = pst.executeQuery();
			if (res.next()) { 
            	cliente = (ClienteRegistrado) mapeoCLiente(res);    
            }
		}catch(SQLException e){
			System.out.println("ERROR readRegistrado:" + e.getMessage());
		}finally{
			UtilSQL.close(con.getConn(),pst, res);
		}
		return cliente;
	}
	
	
	public Cliente readClienteNoRegistrado(Object t) {
		PreparedStatement pst = null;
		Cliente cliente = null;
		ResultSet res = null;
		Conexion con = Conexion.saberEstado();
		Object[] values = {t};
		try{		
			pst = UtilSQL.prepareStatement(con.getConn(), SQL_READ_DOS, false, values);			
			res = pst.executeQuery();
			if (res.next()) { 
            	cliente = mapeoNoCliente(res);              	
            }
		}catch(SQLException e){
			System.err.println("ERROR readClienteNoRegistrado:" + e.getMessage());
		}finally{
			UtilSQL.close(con.getConn(),pst, res);
		}
		return cliente;
	}

	@Override
	public List<Cliente> readAll() {
		PreparedStatement pst = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet res = null;
		Conexion con = Conexion.saberEstado();
		try{
			pst = UtilSQL.prepareStatement(con.getConn(), SQL_READALL, false);			
			res = pst.executeQuery();
			while(res.next()){
				clientes.add(mapeoCLiente(res));
			}			
			return clientes;
		}catch(SQLException e){
			System.err.println("ERROR readAll:" + e.getMessage());		
		}finally{
			UtilSQL.close(con.getConn(),pst, res);	
		}
		return clientes;
	
	}	

	public List<ClienteRegistrado> readAllMenosCumplidores() {		
        PreparedStatement pst = null;
        ResultSet resultSet = null;      
        List<ClienteRegistrado> clientes = new ArrayList<>();
        Conexion con = Conexion.saberEstado();
        try {                  
        	pst = UtilSQL.prepareStatement(con.getConn(), SQL_READALL_MENOS_CUMPLIDORESDOS, false);	
            resultSet = pst.executeQuery();
            while (resultSet.next()) {            	
                clientes.add((ClienteRegistrado) mapeoCLiente(resultSet));
            }
        } catch (SQLException e) {
        	System.err.println("ERROR readAllMenosCumplidores:" + e.getMessage());
        } finally {
        	UtilSQL.close( con.getConn(),pst, resultSet);
        }
        return clientes;
	}
	

	public List<Map<Integer, ClienteRegistrado>> readAllMenosCumplidoresTres() {		
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Conexion con = Conexion.saberEstado();
        List<Map<Integer, ClienteRegistrado>> clientes = new ArrayList<Map<Integer, ClienteRegistrado>>();
        try {    
        	pst = UtilSQL.prepareStatement(con.getConn(), SQL_READALL_MENOS_CUMPLIDORESDOS, false);
            resultSet = pst.executeQuery();           	
            while (resultSet.next()) {  
            	Map<Integer, ClienteRegistrado> map = new TreeMap<Integer, ClienteRegistrado>();            	
                    Integer key =resultSet.getInt("MENOSCUMPLIDORES");
                    Cliente value = mapeoCLiente(resultSet);                   
                    map.put(key, (ClienteRegistrado) value);                
            	clientes.add(map);
            }
            return clientes;
        } catch (SQLException e) {
        	System.err.println("ERROR readAllMenosCumplidoresTres: " +e.getMessage());
        	e.printStackTrace();
        } finally {
        	UtilSQL.close(con.getConn(),pst, resultSet);
        }
        return clientes;
	}
	
	
	

	
//    {"Inventario","tipo","Nombres","Apellidos","Edad","Telefono","Nro Cliente","Email","foto"};
	private Cliente mapeoCLiente(ResultSet resultSet) throws SQLException {	
		TipoCliente t = null;
		if (resultSet.getString("tipo").equals("registrado")) {			
			t = TipoCliente.registrado;
		}else if (resultSet.getString("tipo").equals("no registrado")) {
			t = TipoCliente.no_registrado;
		}
		return new ClienteRegistrado(
	            resultSet.getObject("n_inventario"),
	            t,
	            resultSet.getString("nombre"),
	            resultSet.getString("apellido"),	           
	            resultSet.getString("telefono"),
	            resultSet.getInt("n_cliente"),
	            resultSet.getInt("edad"),
	            resultSet.getString("email"),
	            getImage(resultSet.getInt("n_inventario")));				
	}
	private Cliente mapeoNoCliente(ResultSet resultSet) throws SQLException {	
		TipoCliente t = null;
		if (resultSet.getString("tipo").equals("registrado")) {			
			t = TipoCliente.registrado;
		}else if (resultSet.getString("tipo").equals("no registrado")) {
			t = TipoCliente.no_registrado;
		}
		return new ClienteNoRegistrado(
				resultSet.getObject("n_inventario"),
				t, 
				resultSet.getString("nombre"),
				resultSet.getString("apellido"),
				resultSet.getString("telefono"));
	
	}

    public Object[] arregloReserva(ClienteRegistrado $clientes){
		Object[] o = {$clientes.get$n_inventario(),	
					$clientes.getTipo(),
					$clientes.getNombres(),
					$clientes.getApellidos(),					
					$clientes.getTelefono(),
					$clientes.get$n_cliente(),
					$clientes.get$edad(),
					$clientes.get$email()};		
		return o;
	}
	public Object[][] readAllArray(List<Cliente> list){
		Object[][] reservasArray = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			reservasArray[i] = arregloReserva((ClienteRegistrado)list.get(i));
		}
		return reservasArray;
	}

	public List<Integer> readNro_Clientes() {
		PreparedStatement pst = null;
        ResultSet resultSet = null;       
        List<Integer> clientes = new ArrayList<>();
        Conexion con = Conexion.saberEstado();
        try {   
        	pst = UtilSQL.prepareStatement(con.getConn(), SQL_READ_N_CLENTES, false);           
            resultSet = pst.executeQuery();
            while (resultSet.next()) { 
            	clientes.add(resultSet.getInt("n_cliente"));
            }
        } catch (SQLException e) {
        	System.err.println("ERROR readNro_Clientes: " +e.getMessage());
        } finally {
        }
        return clientes;
	}
	

	
	private byte[] getByteArrayImagen(String $ruta_imagen){  
        byte[] result=null;  
        FileInputStream $fileInStr=null;  
        try{  
            File imgFile=new File($ruta_imagen);  
            $fileInStr=new FileInputStream(imgFile);  
            long imageSize=imgFile.length();               
            if(imageSize>0){  
                result=new byte[(int)imageSize];  
                $fileInStr.read(result);  
            } 
            $fileInStr.close(); 
        }catch(Exception e){  
        	JOptionPane.showMessageDialog(null, "Seleccione una FUCKING!!! foto");
        }
        return result;  
    }  

	      
	public Image getImage(int numero){  
	    Image img=null;  	       
	    PreparedStatement stmt=null;  
	    ResultSet rs = null;	
	    Conexion con = Conexion.saberEstado();
	    Object[] values = {numero};
	    try {
	    	stmt = UtilSQL.prepareStatement(con.getConn(), SQL_FOTO, false, values); 
		    rs = stmt.executeQuery();  
		if(rs.next()){  
		    byte[] imgArr=rs.getBytes("foto");  
		img=Toolkit.getDefaultToolkit().createImage(imgArr);     
		}  
	         
	    }catch(Exception e){  
	        System.err.println("ERROR Imagen: " + e.getMessage());
	        e.printStackTrace();
	    }finally{  
	    	UtilSQL.close(con.getConn(), stmt, rs);
	       
	    }  
	      
	    return img;  
	}  
	    
	public int getIdInventarioRegistrado(Integer $n_cliente){
		Object[] values = {$n_cliente};	
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Conexion con = Conexion.saberEstado();
	    int $n_inventario = -1;
	    try {            
	        preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READ_ID, false, values);          
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) { 
	        	$n_inventario = resultSet.getInt("n_inventario");          	 
	        }           
	    } catch (SQLException e) {
	        
	    } finally {
	    	UtilSQL.close(con.getConn(), preparedStatement, resultSet);
	    }
	
	    return $n_inventario;		
	}

	public boolean createNoRegistrado(Cliente cnr) {
		 Conexion con = Conexion.saberEstado();
		 PreparedStatement cliente = null;
		 PreparedStatement clienteNoRegistrado = null;
	try{						
		con.getConn().setAutoCommit(false);		
		cliente = con.getConn().prepareStatement(SQL_INSERTCLIENTE,Statement.RETURN_GENERATED_KEYS);
		clienteNoRegistrado =  con.getConn().prepareStatement(SQL_INSERTClLIENTE_NO_REGISTRADO);
		cliente.setObject(1, cnr.get$n_inventario());			
		cliente.setString(2, cnr.getTipo().toString());
		cliente.setString(3, cnr.getNombres());
		cliente.setString(4, cnr.getApellidos());
		cliente.setString(5, cnr.getTelefono());
		cliente.executeUpdate();
		int key = 0;
		ResultSet rs = cliente.getGeneratedKeys();
		if (rs != null && rs.next()) {
		    key = (int) rs.getLong(1);
		}
		clienteNoRegistrado.setObject(1,key);					     
		clienteNoRegistrado.executeUpdate();
	
		con.getConn().commit();
		return true;
	}catch(SQLException e){
		e.printStackTrace();
		if (con.getConn() != null) {
			try {
				System.err.print("La trasaccion se deshace ");
				con.getConn().rollback();
					} catch (SQLException ex) {
						e.getMessage();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			finally{
									
				try {
					con.getConn().setAutoCommit(true);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				UtilSQL.close(cliente);
				UtilSQL.close(clienteNoRegistrado);	
				UtilSQL.close(con.getConn());
			}
			return false;
		
		
	}

	public Integer getIdInventarioNoRegistrado(
			String $nombre_no_registrado, String $tel_no_registrado) {
		Object[] values = {$nombre_no_registrado,
				           $tel_no_registrado};		
		
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Conexion con = Conexion.saberEstado();
	    Integer $n_inventario = null;
	    try {            
	        preparedStatement = UtilSQL.prepareStatement(con.getConn(), SQL_READ_ID_NOREGISTRADO, false, values);          
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) { 
	        	$n_inventario = resultSet.getInt("n_inventario");          	 
	        }           
	    } catch (SQLException e) {
	    	System.err.println("ERROR getIdInventarioNoRegistrado:" + e.getMessage());
	    } finally {
	    	UtilSQL.close( preparedStatement, resultSet);
	    }
	
	    return $n_inventario;	
	}

	public Boolean isRegistrado(int $n_inventario) {
		Object[] values ={$n_inventario};
		PreparedStatement pst = null;		
		ResultSet res = null;
		Conexion con = Conexion.saberEstado();
		boolean $fucking_bandera = false;
		try{		
			pst = UtilSQL.prepareStatement(con.getConn(), SQL_ISREGISTRADO, false, values);				
			res = pst.executeQuery();
			if (res.next()) { 
				$fucking_bandera = true;             	
	        }			
		}catch(SQLException e){
			System.err.println("ERROR isRegistrado:" + e.getMessage());
		
		}finally{
			UtilSQL.close(con.getConn(),pst, res);
		}
		return $fucking_bandera;
	}
	
}
