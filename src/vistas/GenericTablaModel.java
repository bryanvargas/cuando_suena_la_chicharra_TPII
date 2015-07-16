package vistas;

import javax.swing.table.AbstractTableModel;

public class GenericTablaModel extends AbstractTableModel{
	private static final long serialVersionUID = 5090157278997135175L;
	private Object[][] $tabla= new String[0][0];
	private String[] $nombreColumnas = new String[0];
	
	public GenericTablaModel(){}

	@Override
	public Class<?> getColumnClass(int $columnIndex) {	
		return getValueAt(0,$columnIndex).getClass();
	}
	@Override
	public boolean isCellEditable(int $fila, int $columna) {	
		return getValueAt($fila,$columna).getClass()==Integer.class?false:true;
			
	}	
	
	@Override
	public void setValueAt(Object $valor, int $fila_index, int $colum_index) {		
		$tabla[$fila_index][$colum_index] = (Object) $valor;
	}
	
	@Override
	public String getColumnName(int column) {
		return $nombreColumnas[column];
	}
	@Override
	public int getColumnCount() {
		return this.$nombreColumnas.length;
	}

	@Override
	public int getRowCount() {		
		return $tabla.length;
	}
	@Override
	public Object getValueAt(int $fila, int $columna) {				
		return $tabla[$fila][$columna];
	}
	public String[] getNombreColumnas() {
		return $nombreColumnas;
	}
	public void setNombreColumnas(String[] $nombreColumnas) {
	
		this.$nombreColumnas = $nombreColumnas;
		
	}
	public Object[][] getTabla() {		
		return $tabla;
	}
	public void setTabla(Object[][] $tabla) {	
		
		this.$tabla = $tabla;	
	
	}
	

	
	
}
