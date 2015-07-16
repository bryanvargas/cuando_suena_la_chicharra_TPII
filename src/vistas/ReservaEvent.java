package vistas;


import java.util.Date;
import java.util.EventObject;


public class ReservaEvent extends EventObject{


	private static final long serialVersionUID = -5044567569131070604L;
	private boolean $is_cliente;
	private Integer $n_cliente;
	private String $nombre_cliente;
	private String $apellido_cliente;
	private String $telefono_cliente;
	
	private String $nombre_noregistrado;
	private String $telefono_noregistrado;
	
	private int $id_cancha;
	private String $nombre_cancha;
	private Date $fecha;
	private int $hora_inicio;
	private int $hora_fin;
	private double $senia;
	private double $monto;
	
	
	private Integer $id;
	
	public ReservaEvent(Object source) {
		super(source);		
	}
	
	public ReservaEvent(Object source, boolean $is_cliente, Integer $n_cliente,
			String $nombre_cliente, String $apellido_cliente,
			String $telefono_cliente, String $nombre_noregistrado,
			String $telefono_noregistrado, int $id_cancha,String $nombre_cancha, Date $fecha,
			int $hora_inicio, int $hora_fin, double $senia, double $monto) {
		super(source);
		this.$is_cliente = $is_cliente;
		this.$n_cliente = $n_cliente;
		this.$nombre_cliente = $nombre_cliente;
		this.$apellido_cliente = $apellido_cliente;
		this.$telefono_cliente = $telefono_cliente;
		this.$nombre_noregistrado = $nombre_noregistrado;
		this.$telefono_noregistrado = $telefono_noregistrado;
		this.$id_cancha = $id_cancha;
		this.$nombre_cancha = $nombre_cancha;
		this.$fecha = $fecha;
		this.$hora_inicio = $hora_inicio;
		this.$hora_fin = $hora_fin;
		this.$senia = $senia;
		this.$monto = $monto;
	}
	
	public ReservaEvent(Object source, Integer $id){
		this(source);
		this.$id = $id;
	}
	


	public Integer get$n_cliente() {
		return $n_cliente;
	}

	
	
	public boolean is$is_cliente() {
		return $is_cliente;
	}



	public void set$is_cliente(boolean $is_cliente) {
		this.$is_cliente = $is_cliente;
	}



	public void set$n_cliente(Integer $n_cliente) {
		this.$n_cliente = $n_cliente;
	}


	public String get$nombre_cliente() {
		return $nombre_cliente;
	}


	public void set$nombre_cliente(String $nombre_cliente) {
		this.$nombre_cliente = $nombre_cliente;
	}


	public String get$apellido_cliente() {
		return $apellido_cliente;
	}


	public void set$apellido_cliente(String $apellido_cliente) {
		this.$apellido_cliente = $apellido_cliente;
	}


	public String get$telefono_cliente() {
		return $telefono_cliente;
	}


	public void set$telefono_cliente(String $telefono_cliente) {
		this.$telefono_cliente = $telefono_cliente;
	}


	public String get$nombre_noregistrado() {
		return $nombre_noregistrado;
	}


	public void set$nombre_noregistrado(String $nombre_noregistrado) {
		this.$nombre_noregistrado = $nombre_noregistrado;
	}


	public String get$telefono_noregistrado() {
		return $telefono_noregistrado;
	}


	public void set$telefono_noregistrado(String $telefono_noregistrado) {
		this.$telefono_noregistrado = $telefono_noregistrado;
	}


	public int get$id_cancha() {
		return $id_cancha;
	}


	public void set$id_cancha(int $id_cancha) {
		this.$id_cancha = $id_cancha;
	}


	public Date get$fecha() {
		return $fecha;
	}


	public void set$fecha(Date $fecha) {
		this.$fecha = $fecha;
	}


	public int get$hora_inicio() {
		return $hora_inicio;
	}


	public void set$hora_inicio(int $hora_inicio) {
		this.$hora_inicio = $hora_inicio;
	}


	public int get$hora_fin() {
		return $hora_fin;
	}


	public void set$hora_fin(int $hora_fin) {
		this.$hora_fin = $hora_fin;
	}


	public double get$senia() {
		return $senia;
	}


	public void set$senia(double $senia) {
		this.$senia = $senia;
	}


	public double get$monto() {
		return $monto;
	}


	public void set$monto(double $monto) {
		this.$monto = $monto;
	}



	public String get$nombre_cancha() {
		return $nombre_cancha;
	}



	public void set$nombre_cancha(String $nombre_cancha) {
		this.$nombre_cancha = $nombre_cancha;
	}

	public Integer get$id() {
		return $id;
	}

	public void set$id(Integer $id) {
		this.$id = $id;
	}	
	
	
	

}
