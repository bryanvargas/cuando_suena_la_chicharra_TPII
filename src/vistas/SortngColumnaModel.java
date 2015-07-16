package vistas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class SortngColumnaModel extends DefaultTableColumnModel {

	private static final long serialVersionUID = 8845880440611371467L;
	
	
	public void addColumna(TableColumn tc){
		super.addColumn(tc);
		int newIndex  = sortedIndexOf(tc);
		if(newIndex!=tc.getModelIndex()){
			moveColumn(tc.getModelIndex(), newIndex);
		}
	}


	protected int sortedIndexOf(TableColumn tc) {
		int stop = getColumnCount();
		String name = tc.getHeaderValue().toString();
		
		for (int i = 0; i < stop; i++) {
			if(name.compareTo(getColumn(i).getHeaderValue().toString())<0){
				return i;
			}
		}
		return stop;
	}
	

}
