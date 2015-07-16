package entidades;

import java.awt.Image;


public class ClienteRegistrado extends Cliente {
	private Integer $n_cliente;
	private int $edad;	
	private String $email;
	private String foto;
	private Image imagen;
	

	public ClienteRegistrado(Object $n_inventario, TipoCliente tipo, String nombres,
			String apellidos, String telefono,Integer $n_cliente,
			int $edad,String $email, String foto) {
		super($n_inventario, tipo, nombres, apellidos, telefono);
		this.$n_cliente = $n_cliente;	
		this.$edad = $edad;
		this.$email = $email;
		this.foto = foto;
		
	}
	
	public ClienteRegistrado(Object $n_inventario, TipoCliente tipo, String nombres,
			String apellidos, String telefono,Integer $n_cliente,
			int $edad,String $email) {
		super($n_inventario, tipo, nombres, apellidos, telefono);
		this.$n_cliente = $n_cliente;	
		this.$edad = $edad;
		this.$email = $email;		
	}
	
	
	public ClienteRegistrado(Object $n_inventario, TipoCliente tipo, String nombres,
			String apellidos, String telefono,Integer $n_cliente,
			int $edad,String $email, Image imagen) {
		super($n_inventario, tipo, nombres, apellidos, telefono);
		this.$n_cliente = $n_cliente;	
		this.$edad = $edad;
		this.$email = $email;
		this.imagen = imagen;
		
	}
	
//	{"Inventario","tipo","Nombres","Apellidos","Telefono","Nro Cliente","edad","Email"};









	public Integer get$n_cliente() {
		return $n_cliente;
	}
	public void set$n_cliente(Integer $n_cliente) {
		this.$n_cliente = $n_cliente;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return super.toString() + "ClienteRegistrado [$n_cliente=" + $n_cliente + ", $edad="
				+ $edad + ", $email=" + $email + ", foto=" + foto + ", imagen="
				+ imagen + "]";
	}

	

	

	
	
	
	
	
	
	
}
