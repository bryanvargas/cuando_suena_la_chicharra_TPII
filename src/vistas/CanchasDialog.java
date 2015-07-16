package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;


public class CanchasDialog extends JDialog{

	private static final long serialVersionUID = -7939548978818827954L;
	private JButton okButton;
	private JButton cancelButton;
	private JDateChooser $date_chooser;
	
	private CanchaDialogListener $cancha_dialog_listener;
	
	public	CanchasDialog(JFrame parent){
		super(parent, "Canchas Disponibles por Fecha", false);
		
		//setUp fecha
		Calendar c = new GregorianCalendar();
		$date_chooser = new JDateChooser();
		$date_chooser.setCalendar(c);

	
		$date_chooser.setEnabled(true);

		okButton= new JButton("OK");
		cancelButton = new JButton("CANCEL");	
		
		layoutControls();
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date $fecha = $date_chooser.getDate();			
				System.out.println($fecha);				
				if($cancha_dialog_listener !=null){
					$cancha_dialog_listener.preferencesSet($fecha);
				}
				setVisible(false);
				
			}
		});
		
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
			
		});
		
		
		setSize(230,180);
		setLocationRelativeTo(parent);
	}
	
	private void layoutControls(){
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Preferencias Fecha");
		
		
//		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		controlsPanel.setBorder(titleBorder);
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
		
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;	
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0,0, 0);
		
		//////First Row///////////		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Fecha: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add($date_chooser, gc);
		
		
		
		///////////Buttons Panel////////////
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton, gc);
		buttonsPanel.add(cancelButton,gc);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
//		Add sub panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel,BorderLayout.CENTER);
		add(buttonsPanel,BorderLayout.SOUTH);
		
	}


	public void setCanchaDialogListener(CanchaDialogListener $cancha_dialog_listener) {
		this.$cancha_dialog_listener = $cancha_dialog_listener;
		
	}
}
