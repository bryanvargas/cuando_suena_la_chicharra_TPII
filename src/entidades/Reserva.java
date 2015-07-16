package entidades;



public class Reserva {
	private Object $id_cancha;
	private Object $id_inventario;
	private int $n_cliente;
	private String $fecha;
	private int $hora_inicio;
	private int $hora_fin;
	private double $monto_pagar;
	private boolean $estado_reservacion;
	private int $duracion;
	private double $senia;
	public Reserva() {		
	}
	
	public Reserva(Object $id_cancha, Object $id_inventario, String $fecha,
			int $hora_inicio, int $hora_fin, double $monto_pagar,
			boolean $estado_reservacion, int $duracion, double $senia) {
		super();
		this.$id_cancha = $id_cancha;
		this.$id_inventario = $id_inventario;
//		this.$n_cliente = $n_cliente;
		this.$fecha = $fecha;
		this.$hora_inicio = $hora_inicio;
		this.$hora_fin = $hora_fin;
		this.$monto_pagar = $monto_pagar;
		this.$estado_reservacion = $estado_reservacion;
		this.$duracion = $duracion;
		this.$senia =  $senia;
	}
	

	public Object get$id_cancha() {
		return $id_cancha;
	}
	public void set$id_cancha(Object $id_cancha) {
		this.$id_cancha = $id_cancha;
	}
	public Object get$id_inventario() {
		return $id_inventario;
	}
	public void set$id_inventario(Object $id_inventario) {
		this.$id_inventario = $id_inventario;
	}
	public String get$fecha() {
		return $fecha;
	}
	public void set$fecha(String $fecha) {
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
	public double get$monto_pagar() {
		return $monto_pagar;
	}
	public void set$monto_pagar(double $monto_pagar) {
		this.$monto_pagar = $monto_pagar;
	}
	public boolean isEstado_reservacion() {
		return $estado_reservacion;
	}
	public void setEstado_reservacion(boolean $estado_reservacion) {
		this.$estado_reservacion = $estado_reservacion;
	}
	public int get$duracion() {
		return $duracion;
	}
	public void set$duracion(int $duracion) {
		this.$duracion = $duracion;
	}
	public int get$n_cliente() {
		return $n_cliente;
	}
	public void set$n_cliente(int $n_cliente) {
		this.$n_cliente = $n_cliente;
	}
	public boolean is$estado_reservacion() {
		return $estado_reservacion;
	}
	public void set$estado_reservacion(boolean $estado_reservacion) {
		this.$estado_reservacion = $estado_reservacion;
	}
	public double get$senia() {
		return $senia;
	}
	public void set$senia(double $senia) {
		this.$senia = $senia;
	}
	@Override
	public String toString() {
		return "Reserva [$id_cancha=" + $id_cancha + ", $id_inventario="
				+ $id_inventario + ", $n_cliente=" + $n_cliente + ", $fecha="
				+ $fecha + ", $hora_inicio=" + $hora_inicio + ", $hora_fin="
				+ $hora_fin + ", $monto_pagar=" + $monto_pagar
				+ ", $estado_reservacion=" + $estado_reservacion
				+ ", $duracion=" + $duracion + ", $senia=" + $senia + "]";
	}
	
	

	
	
}
