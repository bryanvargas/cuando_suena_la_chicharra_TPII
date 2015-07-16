package vistas;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablaPanel extends JPanel{
	private static final long serialVersionUID = 6294456506146229717L;
	private JTable $tabla;
	private GenericTablaModel $genericTablaModel;
	private ClienteTablaListener $clienteTablaListener;
	
	public TablaPanel() {
		//setUp Tabla
		$genericTablaModel = new GenericTablaModel();
		this.$tabla = new JTable($genericTablaModel);
		this.$tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);	
		this.$tabla.setColumnSelectionAllowed(true);		
		
		$tabla.addMouseListener(new MouseAdapter() {			
			public void mousePressed(MouseEvent e) {		
				int row = $tabla.rowAtPoint(e.getPoint());
				int col =  $tabla.columnAtPoint(e.getPoint());
				$tabla.getSelectionModel().setSelectionInterval(row,row);
				if ($clienteTablaListener!=null) {
					if ($tabla.isColumnSelected(1)&& 
						$tabla.getColumnName(1).equals("Id Persona")) {							
						$clienteTablaListener.mostrarInformacionCliente((int)$tabla.getValueAt(row, col));
					}
				}
			}			
		});
		
		setLayout(new BorderLayout());
		add(new JScrollPane($tabla), BorderLayout.CENTER);
	}
	
	public Object[][] getDataTable(){		
		return $genericTablaModel.getTabla();		
	}	
	
	public void setDataTabla(Object[][] $tabla_data){	   
        $genericTablaModel.setTabla($tabla_data);
    }
	
	public void setNombreColumnasTabla(String[] $nombreColumnas){	
		$genericTablaModel.setNombreColumnas($nombreColumnas);
	}	
	
	public void activarSorter(){			
		$tabla.setFillsViewportHeight(true);
        $tabla.setAutoCreateRowSorter(true);
        $genericTablaModel.fireTableStructureChanged();	        
	}
	
	public void setClienteTableListener(ClienteTablaListener listener){
		this.$clienteTablaListener = listener;
	}
}
