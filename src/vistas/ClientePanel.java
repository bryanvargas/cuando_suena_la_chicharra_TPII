package vistas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class ClientePanel extends JPanel {
	private static final long serialVersionUID = -233982905780729611L;
	private JLabel $num_cliente_jb;
	private JLabel $nombres_jb;
	private JLabel $apellidos_jb;
	private JLabel $telefono_jb;
	private JLabel $email_jl;
	private JLabel $foto_label;
	private ShingoTextField $num_cliente_Field;
	private ShingoTextField $nombre_Field;
	private ShingoTextField $apellidos_field;
	private ShingoTextField $telefono_field;
	private ShingoTextField $email_field;
	private JButton okBtn;
	private FormListener formListener;
	private JComboBox<Integer> empCombo;
	private static final int LIMIT = 8;
	
	protected String ruta;	
	
	public ClientePanel() {
		Dimension dim = getPreferredSize();
		dim.width = 257;	
		setPreferredSize(dim);
		setMinimumSize(dim);
		SetUpPanel();	
		
		
		/** Evento de Click Sobre Imagen **/
		$foto_label.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent evt) {
	                lbFotoMouseClicked(evt);
	            }
		});
		
		
		okBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (valido()) {				
					int $num_cliente = Integer.parseInt($num_cliente_Field.getText());
					String $nombres = $nombre_Field.getText();	
					String $apellidos = $apellidos_field.getText();
					String $telefono = $telefono_field.getText();	
					int $edad = (Integer)empCombo.getSelectedItem();	
					String $email = $email_field.getText();					
					ClienteEvent ev = new ClienteEvent(this, $num_cliente, $nombres, $apellidos,
										$telefono,$edad, $email,ruta );				
					if (formListener != null) {
							formListener.formEventOccurred(ev);						
						}			
					}
			}
		});
		
		
		$num_cliente_Field.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {			
			if ($num_cliente_Field.getText().length()>LIMIT) {				
				e.consume();
				getToolkit().beep();
				}
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Agregar Persona");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);		
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		layoutComponents();	
	}
	
	

	public void SetUpPanel(){
		//setUp Nro cliente
		$num_cliente_jb = new JLabel("Nro Cliente: ");	
		$num_cliente_Field = new ShingoTextField(15);
		$num_cliente_Field.setPlaceholder("ej: 000001");
		
		$nombres_jb = new JLabel("Nombre: ");
		$nombre_Field = new ShingoTextField(15);
		$nombre_Field.setPlaceholder("ej: Armando");
		
		$apellidos_jb = new JLabel("Apellido: ");
		$apellidos_field =  new ShingoTextField(15);
		$apellidos_field.setPlaceholder("ej: Faso");
		
		$telefono_jb = new JLabel("Telefono: ");
		$telefono_field =  new ShingoTextField(15);
		$telefono_field.setPlaceholder("ej: (15)-5555-5555");
		
		$email_jl = new JLabel("Email: ");
		$email_field =  new ShingoTextField(15);
		$email_field.setPlaceholder("ej: alguien@shingo.com");
		
		/** **** SetUp Imagen Label *** **/
		/** **************************** **/
		$foto_label =  new JLabel();
		$foto_label.setIcon(new ImagenUsuarioIcon(getClass().getResource("/images/defaultlarge.gif")));
		$foto_label.setPreferredSize(new Dimension(130, 130));	
		

		empCombo = new JComboBox<Integer>();	
		
		okBtn = new JButton("Guardar Cliente");
//		okBtn.setPreferredSize(new Dimension(100,50));
		
//		Set Up mnemoc
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		$num_cliente_jb.setDisplayedMnemonic(KeyEvent.VK_N);
		$num_cliente_jb.setLabelFor($num_cliente_Field);		
		
	
		setVisible(false);

		
		//Set Up combo box.
		DefaultComboBoxModel<Integer> empModel = new DefaultComboBoxModel<Integer>();
		for (int i = 0; i < 100; i++) {
			empModel.addElement(i);
		}	
		empCombo.setModel(empModel);
		empCombo.setSelectedItem(30);
		empCombo.setEditable(true);
		
		
	}
	
	
	public void layoutComponents(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////////Primera Fila//////////////////////////
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.2;		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($num_cliente_jb, gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;			
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($num_cliente_Field, gc);
		
		
		////////Segunda Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($nombres_jb, gc);	
		
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($nombre_Field, gc);		
		
		////////Tercera Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($apellidos_jb, gc);		
		
		gc.gridx = 1;
		gc.gridy = 2;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($apellidos_field, gc);
		
		//////// Cuatro Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($telefono_jb, gc);		
		
		gc.gridx = 1;
		gc.gridy = 3;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($telefono_field, gc);		
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Años: "), gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);
		
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.3;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($email_jl, gc);			
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($email_field, gc);	
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Foto: "), gc);		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($foto_label, gc);


		
		////////Next Row//////////////////////////
		gc.gridy ++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;		
		gc.gridx = 1;		
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridwidth = 20;
		gc.insets = new Insets(0, -20, 0, 0);
		add(okBtn, gc);
		
	}

	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;	
	} 
	
	protected boolean valido() {
		Object msj ="";				
		System.out.println(ruta + " esto es la ruta");
		
		boolean $bandera = true;
		if (!ShingoUtils.validarNumeros($num_cliente_Field.getText())) {
			msj += "Nro de cliente no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		} if (!ShingoUtils.validarTexto($nombre_Field.getText())) {			
			msj += "Formato de nombre no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		}if (!ShingoUtils.validarTexto($apellidos_field.getText())) {
			msj += "Formato de apellido no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		} if (!ShingoUtils.validarTelefono($telefono_field.getText())) {
			msj += "Telefono no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		}
		if (!ShingoUtils.validateEmail($email_field.getText())) {
			msj += "Email no Valido"
					+ System.getProperty("line.separator");
			$bandera = false;
		}if (ruta==null) {
			msj += "Ingrese una Fucking Foto"
					+ System.getProperty("line.separator");
			$bandera = false;
		}if (!$bandera) {
			ShingoUtils.shingoMensaje(msj,"WARNING!",JOptionPane.WARNING_MESSAGE);
		}
		
		return $bandera;
	}
	
	public void lbFotoMouseClicked(MouseEvent evt) {		
	    JFileChooser elegirImagen = new JFileChooser();
	    elegirImagen.setFileSelectionMode(JFileChooser.FILES_ONLY);       
	    int estado = elegirImagen.showOpenDialog(null);
	    if(estado == JFileChooser.APPROVE_OPTION){
	        try {
	        	ruta = elegirImagen.getSelectedFile().getAbsolutePath();	            
	            Image icono = ImageIO.read(elegirImagen.getSelectedFile()).getScaledInstance($foto_label.getWidth(), $foto_label.getHeight(), Image.SCALE_DEFAULT);
	            $foto_label.setIcon(new ImageIcon(icono));//Actualiza icono del panel cliente
	            $foto_label.updateUI(); 	            
	        } 
	        catch (IOException ex){
	        	ShingoUtils.shingoMensaje("Seleccione una Imagen", 
	        			"Error Imagen", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}
	
}

class ClienteCategory{
	private String text;
	private int id;
	public ClienteCategory(int id, String text){
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

