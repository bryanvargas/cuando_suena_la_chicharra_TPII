package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import entidades.ClienteRegistrado;



public class EstadisticasDialog  extends JDialog{

	private static final long serialVersionUID = 6082945099559462694L;
	private JList<Integer> jlista;
	private DefaultListModel<Integer> messageListModel;
	private List<Map<Integer, ClienteRegistrado>> $map_clientes;

	private JTextField $nombre;
	private JTextField $apellido;
	private JTextField $telfono;
	private JTextField $edad;
	private JTextField $email;
	private JLabel $foto;
	private JTextField $tipo;
	private JLabel $cantidad_reservas;
	private ScrollPane $scroll;
	private JButton $button;
	private Graficos $graficos;
	public EstadisticasDialog(JFrame parent) {
		super(parent, "Preferences", false);
		messageListModel = new DefaultListModel<Integer>();	
		jlista = new JList<Integer>(messageListModel);
		$scroll = new ScrollPane();
		$graficos =  new Graficos();		
//		$lista_clientes = new ArrayList<ClienteRegistrado>();
		
		
		//setUp labels y Jtextfield
//		getContentPane().setBackground(Color.GREEN);
	
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
		
		$button = new JButton("Grafico");
		
		jlista.addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				for (Map<Integer, ClienteRegistrado> keys : $map_clientes)
				     for (Entry<Integer, ClienteRegistrado> entry : keys.entrySet())
						if (jlista.getSelectedValue()==entry.getValue().get$n_cliente()) {
							$nombre.setText(entry.getValue().getNombres());
							$apellido.setText(entry.getValue().getApellidos());
							$telfono.setText(entry.getValue().getTelefono());
							$edad.setText(Integer.toString(entry.getValue().get$edad()));
							$email.setText(entry.getValue().get$email());
							$tipo.setText(entry.getValue().getTipo().toString());
							$cantidad_reservas.setText(entry.getKey().toString());
							$foto.setIcon(new ImagenUsuarioIcon(entry.getValue().getImagen()));
							$foto.setPreferredSize(new Dimension(140, 140));
						}
					}				
		});
		
		$button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				$graficos.setVisible(true);
				$graficos.setCharGrafico($map_clientes);
			}
		});
		
		$scroll.add(jlista);
		$scroll.setPreferredSize(new Dimension(80,200));
		layoutControls();
		setSize(490,270);
		setResizable(false); 
		setLocationRelativeTo(parent);
	}
	
	
	public void setDataMapa(List<Map<Integer, ClienteRegistrado>> db){
//		Iterator<ClienteRegistrado> it = db.listIterator();
//		messageListModel.clear();
//		$lista_clientes.clear();
//		while (it.hasNext()) {			
//			$lista_clientes.add(it.next());							
//		}	
		$map_clientes = db;
		cargarListaNroClientes();
	}

	public void cargarListaNroClientes(){	
		messageListModel.clear();
		for (Map<Integer, ClienteRegistrado> keys : $map_clientes)
		     for (Entry<Integer, ClienteRegistrado> entry : keys.entrySet())
		    	 messageListModel.addElement(entry.getValue().get$n_cliente());
	}
	
	private void layoutControls(){
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel listaPanel =  new JPanel();
		
		int space = 1;
		Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Formulario Cliente");
		Border listaBorder = BorderFactory.createTitledBorder("Lista Cliente");
		
		
//		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		controlsPanel.setBorder(titleBorder);
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,null));
		listaPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,listaBorder));
		
//		buttonsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
		controlsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setLayout(new GridBagLayout());
		listaPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
	
		
		//////First Row///////////		
		
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		controlsPanel.add(new JLabel("Nombre: "), gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
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
		JTextArea $t = new JTextArea("Reserva \nNo Cumplida: ");
		$t.setEditable(false);
		controlsPanel.add($t, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($cantidad_reservas, gc);
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		

		
		
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 5);
		buttonsPanel.add($foto, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		buttonsPanel.add($button, gc);	
		//***********************//
		gc.gridy = 0;	
	
		gc.gridx = 1;	

		gc.gridheight = 10;
		
		gc.fill = GridBagConstraints.PAGE_START;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 5);
		listaPanel.add($scroll, gc);
//		
		
//		gc.insets = new Insets(0, 0, 0, 0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		listaPanel.add(new JScrollPane(jlista), gc);	
		
		
		///////////Buttons Panel////////////
//		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		buttonsPanel.add(okButton, gc);
//		buttonsPanel.add(cancelButton,gc);
//		
//		Dimension btnSize = cancelButton.getPreferredSize();
//		okButton.setPreferredSize(btnSize);
		
//		Add sub panels to dialog
		setLayout(new BorderLayout());
		add(listaPanel, BorderLayout.WEST);
		add(controlsPanel,BorderLayout.CENTER);
		add(buttonsPanel,BorderLayout.EAST);
		
	}
}
