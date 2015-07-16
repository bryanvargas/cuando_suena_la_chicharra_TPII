package negocio;

import java.awt.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import vistas.CanchaEvent;
import vistas.ClienteEvent;

import vistas.ReservaEvent;
import entidades.Cancha;
import entidades.Cliente;
import entidades.ClienteNoRegistrado;
import entidades.ClienteRegistrado;
import entidades.Reserva;
import entidades.TipoCesped;
import entidades.TipoCliente;
import modelo.CanchaDao;
import modelo.ClienteDao;
import modelo.ReservasDao;


public class Controlador {
	CanchaDao $canchas = new CanchaDao();
	ClienteDao $clientes = new ClienteDao();
	ReservasDao $reservas = new ReservasDao();
	
	public String[] getnombresColTabCanchas() {		
		return CanchaDao.$CABECERA_TABLA;
	}

	public String[] getNombresColTablaReservas() {
		return ReservasDao.$CABECERA_TABLA;
	}

	public String[] getNombresColTablaClientes() {		
		return ClienteDao.$CABECERA_TABLA;
	}

	public Object[][] getDatosTablaReservas() {	
		return $reservas.readAllArray($reservas.readAll());
	}

	public Object[][] getDatosTablaClientes() {
		return $clientes.readAllArray($clientes.readAll());
	}

	public Object[][] getDatosTablaCanchas() {
		return $canchas.readAllArray($canchas.readAll());
	}
	
	public Object[][] getDatosTablaCanchasDisp() {
		return $canchas.readAllArray($canchas.readCanchasDisponibles());
	}

	public boolean addCliente(ClienteEvent e) {
		String $ruta = "/images/defaultlarge.gif";	
		String $nombres = e.get$nombres();
		String $apellidos =  e.get$apellidos();
		String $telefono = e.get$telefono();
		int $n_cliente = e.get$num_cliente();
		int $edad = e.get$edad();
		String $email = e.get$email();		
		if (e.get$ruta()!= null) {
			$ruta = e.get$ruta();			
		}
		ClienteRegistrado cliente = new ClienteRegistrado(null,TipoCliente.registrado, $nombres, $apellidos, $telefono,
				$n_cliente, $edad, $email, $ruta);
		return $clientes.create(cliente);
		
	}

	public boolean addCancha(CanchaEvent e) {
		String $nombre = e.get$nombre();
		double $precio = e.get$precio();
		int $max_jugadores = e.get$cantidad_jugador();
		String $descrip = e.get$descripcion();
		int $categoria = e.get$cancha_cat();
		TipoCesped $cancha_categ = null;		
		switch ($categoria) {
		case 0:
			$cancha_categ = TipoCesped.sintetico;
			break;
		case 1:
			$cancha_categ = TipoCesped.pasto;
			break;
		case 2:
			$cancha_categ = TipoCesped.tierra;
			break;			
		}
		Cancha cancha = new Cancha(null, $nombre, $precio, $max_jugadores, $descrip, $cancha_categ);
		return $canchas.create(cancha);
	}

	public List<Integer> getNro_Cliente() {	
		return $clientes.readNro_Clientes();
	}

	public List<Cancha> getnombreCanchas() {	
		return $canchas.readAll();
	}

	public Cliente readCliente(int $n_cliente, boolean b) {	
		Cliente cliente = null;
		Integer id = null;
		Boolean registrado = null;		
		if (b) {
			id =  $clientes.getIdInventarioRegistrado($n_cliente);
						
			cliente = $clientes.read(id);
		}else{
			registrado = $clientes.isRegistrado($n_cliente);
			
		
			if (registrado) {
				
				cliente = $clientes.readRegistrado($n_cliente);
			}else{	
				
				cliente = $clientes.readClienteNoRegistrado($n_cliente);
				
			}
		}
		return cliente;
		
	}
	


	public Image imagen(int $n_cliente, boolean b){		
		Integer $n_inventario = null;
		if (b) {
			$n_inventario = $clientes.getIdInventarioRegistrado($n_cliente);
		}else{
			$n_inventario = $n_cliente;
		}	
		return $clientes.getImage($n_inventario);
	}

	
	public Cancha readCancha(int i) {
		return $canchas.read(i);
	}

	public void saveReserva(ReservaEvent ev) {
		Integer $n_inventario = null;
		if (ev.is$is_cliente()) {
			$n_inventario = $clientes.getIdInventarioRegistrado(ev.get$n_cliente());
		}else{
			$n_inventario = $clientes.getIdInventarioNoRegistrado(ev.get$nombre_noregistrado(),
					ev.get$telefono_noregistrado());
		}
		int $id_cancha =  ev.get$id_cancha();
		DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		String $fecha = fecha.format(ev.get$fecha());
		int $inicio = ev.get$hora_inicio();
		int $fin =  ev.get$hora_fin();
		double $monto = ev.get$monto();
		boolean $estado = false;
		int duracion = $fin - $inicio;
		double $senia =  ev.get$senia();
		Reserva $reserva = new Reserva($id_cancha, $n_inventario, $fecha, $inicio,
				$fin, $monto, $estado, duracion, $senia);
		$reservas.create($reserva);
	}

	public boolean getCanchaDisponible(ReservaEvent ev) {	
		if (!ev.is$is_cliente()) {
			String $nombre = ev.get$nombre_noregistrado();
			String $tel = ev.get$telefono_noregistrado();		
			Cliente $no_registrado = new ClienteNoRegistrado(null, TipoCliente.no_registrado,
					$nombre, "", $tel);
			$clientes.createNoRegistrado($no_registrado);
		}
		int $id_cancha = ev.get$id_cancha();	
		Integer $n_inventario= null;
		if (ev.get$n_cliente()!=null) {
			$n_inventario = $clientes.getIdInventarioRegistrado(ev.get$n_cliente());
		}else{
			$n_inventario = $clientes.getIdInventarioNoRegistrado(ev.get$nombre_noregistrado(),
					ev.get$telefono_noregistrado());
		}
		DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		String $fecha = fecha.format(ev.get$fecha());
		int $inicio = ev.get$hora_inicio();
		int $fin = ev.get$hora_fin();
		return $reservas.CanchaDisponible($id_cancha,$n_inventario, $fecha,$inicio,$fin);
	}
	
	public String sqliteDate(Date fecha)
	{
	  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  return sdf.format(fecha);
	}

	public int cantidadReservas(int $id_cliente) {
		return $reservas.cantidadReservas($id_cliente);
	}

	public Object[][] getDatosTablaCanchasDisp(Date $fecha) {
		DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		String $fecha_parseada = fecha.format($fecha);
		return $canchas.readAllArray($canchas.disponiblesPorFecha($fecha_parseada));
	}

	public void actualizarReservas(Object[][] dataTable) {	
		for (int i = 0; i < dataTable.length; i++) {
			$reservas.upDate(reserva(dataTable[i]));
		}	
	}

	private Reserva reserva(Object[] objects) {
		Reserva r = new Reserva();
		r.set$id_cancha(objects[0]);
		r.set$id_inventario(objects[1]);
		r.set$fecha((String) objects[2]);
		r.set$hora_inicio((int) objects[3]);
		r.set$hora_fin((int) objects[4]);
		r.set$monto_pagar((double) objects[5]);
		r.setEstado_reservacion((boolean) objects[6]);
		r.set$duracion((int) objects[7]);
		r.set$senia((double) objects[8]);
		return r;
	}

	public List<Map<Integer, ClienteRegistrado>> readMenosCumplidores() {
		return $clientes.readAllMenosCumplidoresTres();
	}

	public int getIdCancha() {
		return $canchas.get$id_cancha();
	}
}
