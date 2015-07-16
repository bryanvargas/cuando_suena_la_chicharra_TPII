package vistas;

import java.util.EventObject;

public class ClienteEvent extends EventObject{

	private static final long serialVersionUID = -6912306970001300306L;
	private int $num_cliente;
	private String $nombres;
	private String $apellidos;
	private String $telefono;
	private int $edad;
	private String $email;
	private String $ruta;
	
	
	public ClienteEvent(Object source) {
		super(source);		
	}	
	
	public ClienteEvent(Object arg0, int $num_cliente, String $nombres, String $apellidos,
			String $telefono, int $edad, String $email,String $ruta) {
		super(arg0);
		this.$num_cliente = $num_cliente;
		this.$nombres = $nombres;
		this.$apellidos = $apellidos;
		this.$telefono = $telefono;
		this.$edad = $edad;
		this.$email = $email;
		this.$ruta = $ruta;
	}



	public int get$num_cliente() {
		return $num_cliente;
	}

	public void set$num_cliente(int $num_cliente) {
		this.$num_cliente = $num_cliente;
	}

	public String get$nombres() {
		return $nombres;
	}

	public void set$nombres(String $nombres) {
		this.$nombres = $nombres;
	}

	public String get$apellidos() {
		return $apellidos;
	}

	public void set$apellidos(String $apellidos) {
		this.$apellidos = $apellidos;
	}

	public String get$telefono() {
		return $telefono;
	}

	public void set$telefono(String $telefono) {
		this.$telefono = $telefono;
	}

	public int get$edad() {
		return $edad;
	}

	public void set$edad(int $edad) {
		this.$edad = $edad;
	}

	public String get$email() {
		return $email;
	}

	public void set$email(String $email) {
		this.$email = $email;
	}

	public String get$ruta() {
		return $ruta;
	}

	public void set$ruta(String $ruta) {
		this.$ruta = $ruta;
	}

}
