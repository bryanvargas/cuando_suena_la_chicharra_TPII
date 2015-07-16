package entidades;


public class Cliente {
	private Object $n_inventario;
	private TipoCliente $tipo;
	private String $nombres;
	private String $apellidos;
	private String $telefono;
	
	/**
	 * 
	 * @param $n_inventario
	 * @param tipo
	 * @param nombres
	 * @param apellidos
	 * @param telefono
	 */
	public Cliente(Object $n_inventario, TipoCliente $tipo, String $nombres, String $apellidos,
			String $telefono) {	
		this.$n_inventario = $n_inventario;
		this.$tipo = $tipo;
		this.$nombres = $nombres;
		this.$apellidos = $apellidos;
		this.$telefono = $telefono;

	}
	

	public void setN_cliente(int $n_inventario) {
		this.$n_inventario = $n_inventario;
	}
	public String getNombres() {
		return $nombres;
	}
	public void setNombres(String $nombres) {
		this.$nombres = $nombres;
	}
	public String getApellidos() {
		return $apellidos;
	}
	public void setApellidos(String $apellidos) {
		this.$apellidos = $apellidos;
	}
	public String getTelefono() {
		return $telefono;
	}
	public void setTelefono(String $telefono) {
		this.$telefono = $telefono;
	}
	public Object get$n_inventario() {
		return $n_inventario;
	}
	public void set$n_inventario(int $n_inventario) {
		this.$n_inventario = $n_inventario;
	}
	public TipoCliente getTipo() {
		return $tipo;
	}
	public void setTipo(TipoCliente $tipo) {
		this.$tipo = $tipo;
	}

	@Override
	public String toString() {
		return "Cliente [$n_inventario=" + $n_inventario + ", $tipo=" + $tipo
				+ ", $nombres=" + $nombres + ", $apellidos=" + $apellidos
				+ ", $telefono=" + $telefono + "]";
	}

	
	
	

	
	
	
}
