package vistas;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;


public class CanchaPanel extends JPanel {

	private static final long serialVersionUID = -309268801002218788L;
	private JLabel $nombre_jl;
	private JLabel $precio_jl;	
	private JLabel $descripcion_jl;
	private JLabel $tipo_jl;
	private ShingoTextField $nombre_Field;
	private ShingoTextField $precio_field;
	private JTextArea $descripcion_jta;
	private JComboBox<Integer> $emp_combo;
	private JList<CanchaCategory> $tipo_list;	
	private JButton $ok_btn;
	private FormListener formListener;
	
	public CanchaPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 257;	
		setPreferredSize(dim);
		setMinimumSize(dim);
		setUpPanel();
		layoutComponents();		
		
		
		
	$ok_btn.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
			if (valido()) {
				String $nombre = $nombre_Field.getText();
				double $precio = Double.parseDouble($precio_field.getText());
				int $cantidad_jugador = (int)$emp_combo.getSelectedItem();					
				String $descripcion = $descripcion_jta.getText();
				CanchaCategory $cancha_cat = (CanchaCategory)$tipo_list.getSelectedValue();			
				
				CanchaEvent ev = new CanchaEvent(this, $nombre, $precio,$cantidad_jugador,
							$descripcion, $cancha_cat.getId());			
				if (formListener != null) {
					formListener.formEventOccurred(ev);
						
				}			
			}
				
		}
	});	
		
	}
	
	public void layoutComponents(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////////Primera Fila Nombre//////////////////////////
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($nombre_jl, gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($nombre_Field, gc);	
		
		////////Segunda Fila Precio//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($precio_jl, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($precio_field, gc);
		
		
		////////Tercera Fila Tipo de cesped ///////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($tipo_jl, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 2;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($tipo_list, gc);
		
		//////// Cuatro Fila Descripcion//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($descripcion_jl, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 3;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($descripcion_jta, gc);

		
		
		
		
		////////Next Row cantidad maxima de juagadores ///////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment: "), gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($emp_combo, gc);
		
		

		
		////////Next Row//////////////////////////
		gc.gridy ++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;		
		gc.gridx = 1;		
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridwidth = 20;
		gc.insets = new Insets(0, -20, 0, 0);
		add($ok_btn, gc);
		
		
	}

	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
		
	} 
	
	public void setUpPanel(){
		
		Border innerBorder = BorderFactory.createTitledBorder("Agregar Cancha");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);		
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		$nombre_jl = new JLabel("Nombre Cancha: ");
		$nombre_Field = new ShingoTextField(15);
		$nombre_Field.setPlaceholder("Ingrese nombre");
		
		$precio_jl = new JLabel("Precio: ");
		$precio_field =  new ShingoTextField(15);
		$precio_field.setPlaceholder("ej: 180.00");
		
		
		DefaultComboBoxModel<Integer> empModel = new DefaultComboBoxModel<Integer>();
		$emp_combo = new JComboBox<Integer>();
		$emp_combo.setPreferredSize(new Dimension(130,25));
		for (int i = 12; i < 23; i++) {
			empModel.addElement(i);
		}	
		$emp_combo.setModel(empModel);
		$emp_combo.setSelectedItem(22);
		$emp_combo.setEditable(false);		
		
		$descripcion_jl = new JLabel("Descripcion: ");
		$descripcion_jta =  new JTextArea(10,16);
		$descripcion_jta.setBorder(BorderFactory.createEtchedBorder());
		
		$tipo_jl = new JLabel("Cesped: ");		
		$tipo_list = new JList<CanchaCategory>();		
		DefaultListModel<CanchaCategory> $cancha_model = new DefaultListModel<CanchaCategory>();		
		$cancha_model.addElement(new CanchaCategory(0,"Tierra"));
		$cancha_model.addElement(new CanchaCategory(1,"Sintetico"));
		$cancha_model.addElement(new CanchaCategory(2,"Pasto"));
		$tipo_list.setModel($cancha_model);
		
		$ok_btn = new JButton("Guardar Cancha");
		
//		Set Up mnemoc
		$ok_btn.setMnemonic(KeyEvent.VK_O);
		
		$nombre_jl.setDisplayedMnemonic(KeyEvent.VK_N);
		$nombre_jl.setLabelFor($nombre_Field);		
		$tipo_list.setPreferredSize(new Dimension(110,60));
		$tipo_list.setBorder(BorderFactory.createEtchedBorder());		
		$tipo_list.setSelectedIndex(2);
		
	}
	
	
	protected boolean valido() {
		Object msj ="";				
		boolean $bandera = true;
		
		
		if (!ShingoUtils.validarTexto($nombre_Field.getText())) {
			msj += "Formato de nombre no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		} if (!ShingoUtils.validarMonto($precio_field.getText())) {			
			msj += "Precio no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		}
		if (!$bandera) {
			ShingoUtils.shingoMensaje(msj,"WARNING!",JOptionPane.WARNING_MESSAGE);
		}
		
		return $bandera;
	}	
	
	public void resetPanel(){
		$nombre_Field.setText("");
		$precio_field.setText("");
		$descripcion_jta.setText("");
		
	}
	
}





class CanchaCategory{
	private String text;
	private int id;
	public CanchaCategory(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	@Override
	public String toString() {	
		return this.text;
	}

	public int getId() {
		return id;
	}
	
	
}

