package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.*;

import javax.swing.border.Border;



import com.toedter.calendar.JDateChooser;

import entidades.Cancha;
import entidades.Cliente;




public class ReservaPanel extends JPanel implements ItemListener{	

	private static final long serialVersionUID = -5383102241909048572L;
	private JLabel $num_cliente_jl;	
	private JLabel $nombre_cliente_jl;
	private JLabel $apellido_cliente_jl;
	private JLabel $foto_jl;
	private JLabel $telefono_jl;
	private JLabel $nombre_no_registrado_jl;
	private ShingoTextField $nombre_no_registrado_jtf;
	private JLabel $tel_no_registrado_jl;
	private ShingoTextField $tel_no_registrado_jtf;
	private JLabel $senia_no_registrado_jl;
	private ShingoTextField $senia_no_registrado_jtf;
	private ShingoTextField $apellido_cliente_jtf;
	private ShingoTextField $nombre_cliente_jtf;
	private ShingoTextField $telefono_jtf;
	private JLabel $nombre_cancha;
	
	private JDateChooser $date_chooser;
	private JLabel $fecha_jl;
	
	private JLabel $horario_jl;
	private JComboBox<Integer> $inicio_jc;
	private JComboBox<Integer> $fin_jc;
	private JComboBox<Integer> $n_cliente_jcb;
	private DefaultComboBoxModel<Integer> $n_cliente_models;
	private JButton okBtn;
	
	
	private JComboBox<Integer> $canchas_combo;
	DefaultComboBoxModel<Integer> $canchas_model;
	private JCheckBox $cliente_registrado_Check;
	private JCheckBox $cliente_no_registrado_Check;
	private ButtonGroup genderGroup;
	private JScrollPane scroll;	
	
	
	private JLabel $monto_jl;
	private ShingoTextField $monto_jtf;
	
	private PanelListener $reserva_panel_listener;
	
	int $id_cancha = 0;
	
	
	public ReservaPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 257;	
		setPreferredSize(dim);
		setMinimumSize(dim);		
		
		//setUp Cliente registrado		
		$cliente_no_registrado_Check = new JCheckBox();	
		$cliente_no_registrado_Check.addItemListener(this);

		
		//setUp Cliente no registrado
		$cliente_registrado_Check = new JCheckBox();
		$cliente_registrado_Check.addItemListener(this);
//
		genderGroup = new ButtonGroup();
		
//		//Set up genter Radios
		genderGroup.add($cliente_registrado_Check);
		genderGroup.add($cliente_no_registrado_Check);
//		
		
		
		
		
		
		//setUp Numero Cliente
		$num_cliente_jl = new JLabel("Nro Cliente: ");		
		$num_cliente_jl.setDisplayedMnemonic(KeyEvent.VK_N);	
		$n_cliente_jcb = new JComboBox<Integer>();
		$n_cliente_models = new DefaultComboBoxModel<Integer>();		
		$n_cliente_jcb.setModel($n_cliente_models);
		$num_cliente_jl.setLabelFor($n_cliente_jcb);
		scroll = new JScrollPane($n_cliente_jcb);
//		scroll.setPreferredSize(new Dimension(110,65));
		scroll.setBorder(BorderFactory.createEtchedBorder());		
		scroll.setEnabled(false);
		$n_cliente_jcb.setEnabled(false);
		$n_cliente_jcb.addItemListener(this);
		$n_cliente_jcb.setName("");
		
		//setUp Nombre Cliente
		$nombre_cliente_jl = new JLabel("Nombre: ");		
		$nombre_cliente_jtf =  new ShingoTextField(15);
		$nombre_cliente_jl.setDisplayedMnemonic(KeyEvent.VK_C);
		$nombre_cliente_jl.setLabelFor($nombre_cliente_jtf);
		$nombre_cliente_jl.setEnabled(false);
		$nombre_cliente_jtf.setEditable(false);
		$nombre_cliente_jtf.setEditable(false);
		
		//setUp apellido Cliente		
		$apellido_cliente_jl = new JLabel("Apellido");
		$apellido_cliente_jtf = new ShingoTextField(15);
		$apellido_cliente_jl.setDisplayedMnemonic(KeyEvent.VK_A);
		$apellido_cliente_jl.setLabelFor($apellido_cliente_jtf);
		$apellido_cliente_jl.setEnabled(false);
		$apellido_cliente_jtf.setEnabled(false);
		$apellido_cliente_jtf.setEditable(false);
		
		//setUp telefono Cliente		
		$telefono_jl =  new JLabel("Telefono: ");
		$telefono_jtf =  new ShingoTextField(15);
		$telefono_jl.setDisplayedMnemonic(KeyEvent.VK_T);
		$telefono_jl.setLabelFor($telefono_jtf);
		$telefono_jl.setEnabled(false);
		$telefono_jtf.setEditable(false);
		$telefono_jtf.setEditable(false);
		
		//setUp Foto Cliente	
		$foto_jl =  new JLabel();
		$foto_jl.setIcon(new ImagenUsuarioIcon(getClass().getResource("/images/sulueta.png")));
		$foto_jl.setPreferredSize(new Dimension(80, 80));	
		$foto_jl.setEnabled(false);
		
		//setUp Canchas Disponibes	
		$canchas_combo = new JComboBox<Integer>();
		$canchas_model = new DefaultComboBoxModel<Integer>();			
		$canchas_combo.setModel($canchas_model);
		$canchas_combo.setSelectedItem(0);
		$canchas_combo.setEditable(false);
		$canchas_combo.addItemListener(this);
//		$canchas_combo.setSelectedIndex(-1);
		
		
		//setUp nombre_no registtado
		$nombre_no_registrado_jl =  new JLabel("Nombre");
		$nombre_no_registrado_jtf = new ShingoTextField(13);
		$nombre_no_registrado_jtf.setPlaceholder("ej: Javier Marenco");
		$nombre_cliente_jl.setDisplayedMnemonic(KeyEvent.VK_G);
		$nombre_no_registrado_jl.setLabelFor($nombre_no_registrado_jtf);
		$nombre_no_registrado_jl.setEnabled(false);
		$nombre_no_registrado_jtf.setEnabled(false);
		
		
		//setUp telefono no registrado
		$tel_no_registrado_jl = new JLabel("Telefono: ");
		$tel_no_registrado_jtf = new ShingoTextField(13);
		$tel_no_registrado_jtf.setPlaceholder("ej: (15)-5555-5555");
		$tel_no_registrado_jl.setDisplayedMnemonic(KeyEvent.VK_H);
		$tel_no_registrado_jl.setLabelFor($tel_no_registrado_jtf);
		$tel_no_registrado_jl.setEnabled(false);
		$tel_no_registrado_jtf.setEnabled(false);
		
		
		//setUp senia no registtado
		$senia_no_registrado_jl = new JLabel("Seña: ");
		$senia_no_registrado_jtf = new ShingoTextField(15);
		$senia_no_registrado_jtf.setPlaceholder("00.00");
		$senia_no_registrado_jl.setDisplayedMnemonic(KeyEvent.VK_S);
		$nombre_no_registrado_jl.setLabelFor($senia_no_registrado_jtf);
		$senia_no_registrado_jl.setEnabled(false);
		$senia_no_registrado_jtf.setEnabled(false);
		
		//setUp fecha
		Calendar c = new GregorianCalendar();
		$date_chooser = new JDateChooser();
		$date_chooser.setCalendar(c);
		$fecha_jl = new JLabel("Fecha");
//		$fecha_jtf = new ShingoTextField(15);		
		$date_chooser.setEnabled(true);
		

		//setUp horarios
		$horario_jl = new JLabel("Horario: ");
		$inicio_jc = new JComboBox<Integer>();
		
		$fin_jc = new JComboBox<Integer>();
		for (int i = 8; i < 28; i++) {
			if (i>=24) {				
				$inicio_jc.addItem(i-24);
				$fin_jc.addItem(i-24);
			}else{
				$inicio_jc.addItem(i);
				$fin_jc.addItem(i);
			}
			
		}
		$inicio_jc.addItemListener(this);
		
		//setUp Monto
		$monto_jl = new JLabel("Monto: ");
		$monto_jtf = new ShingoTextField(15);			

		
		//setUp id´s Cliente - Cancha
		$nombre_cancha =  new JLabel();
		$nombre_cancha.setBorder(BorderFactory.createEtchedBorder());
		
		okBtn = new JButton("Reservar");
		
//		Set Up mnemoc
		okBtn.setMnemonic(KeyEvent.VK_O);
	
		okBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (valido()) {							
					boolean $is_cliente = $cliente_registrado_Check.isSelected();
					Integer $n_cliente = (Integer) $n_cliente_jcb.getSelectedItem();
					String $nombre_cliente = $nombre_cliente_jtf.getText();
					String $apellido_cliente = $apellido_cliente_jtf.getText();
					String $telefono_cliente = $telefono_jtf.getText();
					String $nombre_noregistrado = $nombre_no_registrado_jtf.getText();
					String $telefono_noregistrado = $tel_no_registrado_jtf.getText();
					int $id_cancha = (int)$canchas_combo.getSelectedItem();
					Date $fecha = $date_chooser.getDate();
					int $hora_inicial = (int)$inicio_jc.getSelectedItem();
					int $hora_fin = (int)$fin_jc.getSelectedItem();
					double $monto = Double.parseDouble($monto_jtf.getText());	
					String $nomb_cancha = $nombre_cancha.getText();							
					double $senia_no_cliente = Double.parseDouble($senia_no_registrado_jtf.getText());				
					ReservaEvent rv = new ReservaEvent(this,$is_cliente,$n_cliente,$nombre_cliente,
							$apellido_cliente,$telefono_cliente,$nombre_noregistrado,$telefono_noregistrado,
							$id_cancha,$nomb_cancha,$fecha,$hora_inicial,$hora_fin,$monto,$senia_no_cliente);	
					if ($reserva_panel_listener != null) {
						$reserva_panel_listener.guardarReservaEventOccured(rv);					
					}
				}
			}
		});
		
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Agregar Persona");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);		
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		layoutComponents();	
	}
	
	public void layoutComponents(){
		JPanel $registrado_panel = new JPanel();
		JPanel $noregistrado_panel = new JPanel();
		JPanel $reserva_panel =  new JPanel();
		
		int space = 2;
		Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		Border $t_border_a = BorderFactory.createTitledBorder("Cliente Registrado");
		Border $t_border_b = BorderFactory.createTitledBorder("Cliente No Registrado");
		@SuppressWarnings("unused")
		Border $t_border_c = BorderFactory.createTitledBorder("Reserva");
		
		$registrado_panel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,$t_border_a));		
		$noregistrado_panel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,$t_border_b));
		
		$registrado_panel.setLayout(new GridBagLayout());
		$noregistrado_panel.setLayout(new GridBagLayout());
		$reserva_panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//////////Primera Fila Cliente checkbox //////////////
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		$registrado_panel.add(new JLabel("Cte Registrado: "), gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		$registrado_panel.add($cliente_registrado_Check, gc);	
		
		////////Segunda Fila numero Clientes ////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$registrado_panel.add($num_cliente_jl, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.insets = new Insets(3, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		$registrado_panel.add(scroll, gc);
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$registrado_panel.add($nombre_cliente_jl, gc);
		
		gc.gridx = 1;
		gc.anchor =  GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		$registrado_panel.add($nombre_cliente_jtf, gc);
	
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		$registrado_panel.add($apellido_cliente_jl, gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		$registrado_panel.add($apellido_cliente_jtf, gc);
	
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$registrado_panel.add($telefono_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		$registrado_panel.add($telefono_jtf, gc);
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$registrado_panel.add(new JLabel("Foto: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		$registrado_panel.add($foto_jl, gc);
		
		
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		$noregistrado_panel.add(new JLabel("No Registrado: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		$noregistrado_panel.add($cliente_no_registrado_Check, gc);
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		$noregistrado_panel.add($nombre_no_registrado_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		$noregistrado_panel.add($nombre_no_registrado_jtf, gc);
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$noregistrado_panel.add($tel_no_registrado_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		$noregistrado_panel.add($tel_no_registrado_jtf, gc);
		
		

		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(10, 0, 0, 5);
		$reserva_panel.add(new JLabel("Cancha: "), gc);	
		
		
	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 5);
		
		$reserva_panel.add($canchas_combo, gc);

		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 1;		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 40, 0, 5);
		
		$reserva_panel.add($nombre_cancha, gc);
		

		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$reserva_panel.add($fecha_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		//		add($fecha_jtf, gc);
		$reserva_panel.add($date_chooser, gc);
		
		
		
		
		//		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$reserva_panel.add($horario_jl, gc);	
		
		
		
		gc.weightx = 0;
		gc.weighty = 0.2;			
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 5);
		$reserva_panel.add($inicio_jc, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;	
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 50, 0, 0);
		$reserva_panel.add(new JLabel("hasta"), gc);
		
		gc.weightx = 0;
		gc.weighty = 0.2;	
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 75, 0, 0);		
		$reserva_panel.add($fin_jc, gc);
		
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		$reserva_panel.add($senia_no_registrado_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		$reserva_panel.add($senia_no_registrado_jtf, gc);
		
		////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		$reserva_panel.add($monto_jl, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
//		add($fecha_jtf, gc);
		$reserva_panel.add($monto_jtf, gc);

		////////Next Row//////////////////////////
		gc.gridy ++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;		
		gc.gridx = 1;		
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridwidth = 20;
		gc.insets = new Insets(15, 0, 0, 0);
		$reserva_panel.add(okBtn, gc);
		
		
		add($registrado_panel, BorderLayout.NORTH);
		add($noregistrado_panel, BorderLayout.CENTER);
		add($reserva_panel,BorderLayout.SOUTH);		
	}

	public void setFormListener(PanelListener $reserva_panel_listener) {
		this.$reserva_panel_listener = $reserva_panel_listener;
		
	} 	
	public void setDataLista(List<Integer> db){		
		for (Integer integer : db)
			$n_cliente_models.addElement(integer);		
		$n_cliente_jcb.setSelectedIndex(-1);
	}
	public void agregarNroCliente(Integer $elem){
		$n_cliente_models.addElement($elem);
	}
	
	public void setDataCombo(List<Cancha> db){		
		for (Cancha c : db) 
			$canchas_model.addElement((Integer) c.getId_cancha());			
		$canchas_combo.setSelectedIndex(-1);
	
	
	}
	
	public void agregarCanchaNueva(int $elem){
		$canchas_model.addElement($elem);
	}
	
	public void setCliente(Cliente bd, Image image){		
		$nombre_cliente_jtf.setText(bd.getNombres());
		$apellido_cliente_jtf.setText(bd.getApellidos());
		$telefono_jtf.setText(bd.getTelefono());		
		$foto_jl.setIcon(new ImagenUsuarioIcon(image));
		$foto_jl.setPreferredSize(new Dimension(80, 80));
	}

	public void setDatosCancha(Cancha bd) {	
		$monto_jtf.setText(Double.toString(bd.getPrecio()));
		$nombre_cancha.setText(bd.getNombre());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		boolean $isTicked_cliente  = $cliente_no_registrado_Check.isSelected()?true:false;		
		$nombre_no_registrado_jl.setEnabled($isTicked_cliente);
		$nombre_no_registrado_jtf.setEnabled($isTicked_cliente);
		$tel_no_registrado_jl.setEnabled($isTicked_cliente);
		$tel_no_registrado_jtf.setEnabled($isTicked_cliente);
		$senia_no_registrado_jl.setEnabled($isTicked_cliente);
		$senia_no_registrado_jtf.setEnabled($isTicked_cliente);
		
		boolean $isTicked_nocliente  = $cliente_registrado_Check.isSelected()?true:false;
		$num_cliente_jl.setEnabled($isTicked_nocliente);
		$n_cliente_jcb.setEnabled($isTicked_nocliente);
		
		$nombre_cliente_jl.setEnabled($isTicked_nocliente);
		$nombre_cliente_jtf.setEnabled($isTicked_nocliente);
		$apellido_cliente_jl.setEnabled($isTicked_nocliente);
		$apellido_cliente_jtf.setEnabled($isTicked_nocliente);
		$telefono_jl.setEnabled($isTicked_nocliente);
		$telefono_jtf.setEnabled($isTicked_nocliente);
		$foto_jl.setEnabled($isTicked_nocliente);
		
		if (e.getSource() == $canchas_combo) {
			if ($reserva_panel_listener != null) {						
				int $id = (int) $canchas_combo.getSelectedItem();					
				ReservaEvent ev = new ReservaEvent(this, $id);
				$reserva_panel_listener.mostrarCanchasEventOccured(ev);
			}		
		}
		
		if (e.getSource()==$n_cliente_jcb) {
			if ($reserva_panel_listener != null) {				
				int $n_cliente = (int) $n_cliente_jcb.getSelectedItem();	
				ReservaEvent ev = new ReservaEvent(this, $n_cliente);
				$reserva_panel_listener.mostrarClientesEventOccured(ev);
			}	
		}
		if (e.getSource()==$inicio_jc) {
			int select = (int)$inicio_jc.getSelectedItem();
			$fin_jc.setSelectedItem(select+1);
		}
		
	}
	protected boolean valido() {
		Object msj ="";				
		boolean $bandera = true;
		if (!$cliente_registrado_Check.isSelected() 
				&& !$cliente_no_registrado_Check.isSelected()) {
			msj += "Por favor debe seleccionar tipo de cliente"
				+ System.getProperty("line.separator");
		}
		if ($cliente_no_registrado_Check.isSelected()) {
			if (!ShingoUtils.validarTexto($nombre_no_registrado_jtf.getText())) {
				msj += "Formato de nombre no Valido"
						+ System.getProperty("line.separator");
				$bandera = false;
			} if (!ShingoUtils.validarTelefono($tel_no_registrado_jtf.getText())) {			
				msj += "Telefono no Valido"
						+ System.getProperty("line.separator");
				$bandera = false;
			}if (!ShingoUtils.validarMonto($senia_no_registrado_jtf.getText())) {			
				msj += "Formato de seña no valido"
						+ System.getProperty("line.separator");
				$bandera = false;
			}
		}
		if ($canchas_combo.getSelectedItem()==null) {
			msj += "No ha seleccionado una cancha"
					+ System.getProperty("line.separator");
			$bandera = false;
		}
		if (!$bandera) {
			ShingoUtils.shingoMensaje(msj,"WARNING!",JOptionPane.WARNING_MESSAGE);
		}
		
		return $bandera;
	}

}

class Category{
	private String text;
	private int id;
	public Category(int id, String text){
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

