package entidades;


public class Cancha {
	private Object $id_cancha;
	private String $nombre;
	private double $precio;
	private int $max_jugadores;
	private String $descripcion;
	private TipoCesped $tipoCancha;
	
	
	
	public Cancha() {}

	/**
	 * 
	 * @param id_cancha de tipo Object
	 * @param nombre tipo String
	 * @param precio tipo double
	 * @param max_jugadores tipo int
	 * @param descripcion tipo String
	 * @param tipoCancha tipo Enum
	 */
	public Cancha(Object $id_cancha, String $nombre, double $precio,
			int $max_jugadores, String $descripcion, TipoCesped $tipoCancha) {
		super();
		this.$id_cancha = $id_cancha;
		this.$nombre = $nombre;
		this.$precio = $precio;
		this.$max_jugadores = $max_jugadores;
		this.$descripcion = $descripcion;
		this.$tipoCancha = $tipoCancha;

	}
	
	public Object getId_cancha() {
		return $id_cancha;
	}
	public void setId_cancha(int $id_cancha) {
		this.$id_cancha = $id_cancha;
	}
	public String getNombre() {
		return $nombre;
	}
	public void setNombre(String $nombre) {
		this.$nombre = $nombre;
	}
	public double getPrecio() {
		return $precio;
	}
	public void setPrecio(double $precio) {
		this.$precio = $precio;
	}
	public int getMax_jugadores() {
		return $max_jugadores;
	}
	public void setMax_jugadores(int $max_jugadores) {
		this.$max_jugadores = $max_jugadores;
	}
	public String getDescripcion() {
		return $descripcion;
	}
	public void setDescripcion(String $descripcion) {
		this.$descripcion = $descripcion;
	}
	public TipoCesped getTipoCancha() {
		return $tipoCancha;
	}
	public void setTipoCancha(TipoCesped $tipoCancha) {
		this.$tipoCancha = $tipoCancha;
	}

	@Override
	public String toString() {
		return "Cancha [$id_cancha=" + $id_cancha + ", $nombre=" + $nombre
				+ ", $precio=" + $precio + ", $max_jugadores=" + $max_jugadores
				+ ", $descripcion=" + $descripcion + ", $tipoCancha="
				+ $tipoCancha + "]";
	}

	
	
	
}
