package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import entidades.Cliente;
import entidades.ClienteRegistrado;


public class ClienteDialog extends JDialog {

	private static final long serialVersionUID = -4490178416764006930L;

	private JTextField $n_cliente;
	private JTextField $nombre;
	private JTextField $apellido;
	private JTextField $telfono;
	private JTextField $edad;
	private JTextField $email;
	private JLabel $foto;
	private JTextField $tipo;
	private JLabel $cantidad_reservas;
	
	public	ClienteDialog(JFrame parent){
		super(parent, "Preferences", false);

	
//		getContentPane().setBackground(Color.GREEN);
		$n_cliente  = new JTextField(12);
		$n_cliente.setEditable(false);
		$nombre = new JTextField(12);
		$nombre.setEditable(false);
		$apellido =  new JTextField(12);
		$apellido.setEditable(false);
		$telfono =  new JTextField(12);
		$telfono.setEditable(false);	
		$email = new JTextField(12);
		$email.setEditable(false);
		$edad = new JTextField(12);
		
		$edad.setEditable(false);
		$tipo = new JTextField(12);
		$tipo.setEditable(false);
		$cantidad_reservas =  new JLabel();
		
		
		//setUp Foto Cliente	
		$foto =  new JLabel();
		$foto.setIcon(new ImagenUsuarioIcon(getClass().getResource("/images/sulueta.png")));
		$foto.setPreferredSize(new Dimension(140, 140));	
	
		
//		seteat la forma es que se escribe el pasword  de "*" a "&"
//		passField.setEchoChar('&');
		
		layoutControls();
		
		
	
		
		
		
		setSize(400,260);
		
		setResizable(false); 
		setLocationRelativeTo(parent);
	}
	
	private void layoutControls(){
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 2;
		Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		
		
//		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		controlsPanel.setBorder(titleBorder);
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,null));
		
//		buttonsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
		controlsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
	
		
//		Insets rightPadding = new Insets(0, 0, 0, 15);
//		Insets noPadding = new Insets(0, 0,0, 0);
		
		//////First Row///////////		
		
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		controlsPanel.add(new JLabel("Nro Cliente: "), gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		controlsPanel.add($n_cliente, gc);	

	
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Nombre: "), gc);
		
		gc.gridx = 1;
		gc.anchor =  GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($nombre, gc);
	
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		controlsPanel.add(new JLabel("Apellidos: "), gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		controlsPanel.add($apellido, gc);
	
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Telefono: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($telfono, gc);
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Edad: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($edad, gc);
		
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Email: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($email, gc);
		
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Tipo: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($tipo, gc);
		
		
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Reservas: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($cantidad_reservas, gc);
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
//		gc.weightx = 1;
//		gc.weighty = 0.2;
//		
//		gc.gridx = 0;		
//		gc.anchor = GridBagConstraints.FIRST_LINE_END;
//		gc.insets = new Insets(3, 0, 0, 5);
//		buttonsPanel.add(new JLabel("Foto: "), gc);	
		
		
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 5);
		buttonsPanel.add($foto, gc);
		
		
		
		///////////Buttons Panel////////////
//		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		buttonsPanel.add(okButton, gc);
//		buttonsPanel.add(cancelButton,gc);
//		
//		Dimension btnSize = cancelButton.getPreferredSize();
//		okButton.setPreferredSize(btnSize);
		
//		Add sub panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel,BorderLayout.WEST);
		add(buttonsPanel,BorderLayout.CENTER);
		
	}
	
	
	public void setCliente(Cliente bd, Image image, int cantidad){
		$nombre.setText(bd.getNombres());
		$apellido.setText(bd.getApellidos());
		$telfono.setText(bd.getTelefono());		
		if (image==null) {
			$foto.setIcon(new ImagenUsuarioIcon(getClass().getResource("/images/sulueta.png")));
			$foto.setPreferredSize(new Dimension(150, 140));	
		}else{		
			$foto.setIcon(new ImagenUsuarioIcon(image));
			$foto.setPreferredSize(new Dimension(150, 150));
		}
		if (bd.getTipo()!=null) {
			ClienteRegistrado cr = (ClienteRegistrado) bd;
			$n_cliente.setText(cr.get$n_cliente().toString());
			$edad.setText(Integer.toString(cr.get$edad()));
			$email.setText(cr.get$email());
			$tipo.setText(cr.getTipo().toString());
		}else{
			$n_cliente.setText("");
			$edad.setText("");
			$email.setText("");
			$tipo.setText("no registrado");
		}
		$cantidad_reservas.setText(Integer.toString(cantidad));
		
		
	}


}
