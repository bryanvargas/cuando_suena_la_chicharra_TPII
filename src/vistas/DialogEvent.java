package vistas;

import java.awt.Image;
import java.util.EventObject;

public class DialogEvent extends EventObject {
	private static final long serialVersionUID = 3589961239785315950L;
	private Integer $n_cliente;
	private String $nombre;
	private String $apellido;
	private String $telefono;
	private Image $foto;
	private Integer $cantidad_reservas;
	public DialogEvent(Object source) {
		super(source);		
	}
	
	
	
	public DialogEvent(Object source, Integer $n_cliente, String $nombre,
			String $apellido, String $telefono, Image $foto,
			Integer $cantidad_reservas) {
		super(source);
		this.$n_cliente = $n_cliente;
		this.$nombre = $nombre;
		this.$apellido = $apellido;
		this.$telefono = $telefono;
		this.$foto = $foto;
		this.$cantidad_reservas = $cantidad_reservas;
	}



	public Integer get$n_cliente() {
		return $n_cliente;
	}
	public void set$n_cliente(Integer $n_cliente) {
		this.$n_cliente = $n_cliente;
	}
	public String get$nombre() {
		return $nombre;
	}
	public void set$nombre(String $nombre) {
		this.$nombre = $nombre;
	}
	public String get$apellido() {
		return $apellido;
	}
	public void set$apellido(String $apellido) {
		this.$apellido = $apellido;
	}
	public String get$telefono() {
		return $telefono;
	}
	public void set$telefono(String $telefono) {
		this.$telefono = $telefono;
	}
	public Image get$foto() {
		return $foto;
	}
	public void set$foto(Image $foto) {
		this.$foto = $foto;
	}
	public Integer get$cantidad_reservas() {
		return $cantidad_reservas;
	}
	public void set$cantidad_reservas(Integer $cantidad_reservas) {
		this.$cantidad_reservas = $cantidad_reservas;
	}
	
	
	
}
