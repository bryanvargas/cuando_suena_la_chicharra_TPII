package vistas;

import java.util.EventObject;

public class CanchaEvent extends EventObject {

	private static final long serialVersionUID = -7206192259593339338L;
	private String $nombre;
	private double $precio;
	private int $cantidad_jugador;
	private String $descripcion;
	private int $cancha_cat;
	
	public CanchaEvent(Object source) {
		super(source);		
	}		
	
	public CanchaEvent(Object arg0, String $nombre, double $precio,
			int $cantidad_jugador, String $descripcion, int $cancha_cat) {
		super(arg0);
		this.$nombre = $nombre;
		this.$precio = $precio;
		this.$cantidad_jugador = $cantidad_jugador;
		this.$descripcion = $descripcion;
		this.$cancha_cat = $cancha_cat;
	}


	public String get$nombre() {
		return $nombre;
	}

	public void set$nombre(String $nombre) {
		this.$nombre = $nombre;
	}

	public double get$precio() {
		return $precio;
	}

	public void set$precio(double $precio) {
		this.$precio = $precio;
	}

	public int get$cantidad_jugador() {
		return $cantidad_jugador;
	}

	public void set$cantidad_jugador(int $cantidad_jugador) {
		this.$cantidad_jugador = $cantidad_jugador;
	}

	public String get$descripcion() {
		return $descripcion;
	}

	public void set$descripcion(String $descripcion) {
		this.$descripcion = $descripcion;
	}

	public int get$cancha_cat() {
		return $cancha_cat;
	}

	public void set$cancha_cat(int $cancha_cat) {
		this.$cancha_cat = $cancha_cat;
	}


}