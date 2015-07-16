package vistas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;



public class Toolbar extends JToolBar implements ActionListener{

	private static final long serialVersionUID = -4471978633890412807L;
	private JButton $mostrar_clientes;
	private JButton $mostrar_canchas;
	private JButton $mostrar_canchas_disp;
	private JButton $mostrar_reservas;
	private JButton $mostrar_estadisticas;
	private JButton $registrar_reserva;
	private JButton $agregar_reserva;	
	private ToolbarListener toolbarListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setUpBotones();	
	}	
	
	/** ****** Eventos Toolbar ********* */
	/** ******************************** */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked == $mostrar_clientes){
			if (toolbarListener != null) {
				toolbarListener.mostrarClientesEventOccured();
			}
		}else if(clicked == $mostrar_canchas){
			if (toolbarListener != null) {
				toolbarListener.mostrarCanchasEventOccured();
			}
		}else if(clicked == $mostrar_reservas){
			if (toolbarListener != null) {
				toolbarListener.mostrasReservasEventOcurred();
			}
		}else if(clicked == $mostrar_estadisticas){
			if (toolbarListener != null) {
				toolbarListener.mostrasEstadisticasEventOcurred();
			}
		}else if(clicked == $registrar_reserva){
			if (toolbarListener != null) {
				toolbarListener.actualizarReservaEventOcurred();
			}
		}else if(clicked == $agregar_reserva){
			if (toolbarListener != null) {
				toolbarListener.mostrarFomularioReservaEventOcurred();
			}
		}else if(clicked == $mostrar_canchas_disp){
			if (toolbarListener != null) {
				toolbarListener.mostrarCanchasDispEventOcurred();
			}
		}		
	}
	
	
	public void setToolbarListener(ToolbarListener listener){
		this.toolbarListener = listener;
	}
	
	
	private void setUpBotones(){	
		$mostrar_clientes = new JButton();
//		$mostrar_clientes.setText("Mostrar registro Clientes");	
		$mostrar_clientes.setIcon(ShingoUtils.createImageIcon("/images/cliente2.png"));
		$mostrar_clientes.setToolTipText("Canchas Disponibles");
		$mostrar_clientes.addActionListener(this);
		
		$mostrar_canchas = new JButton();
//		$mostrar_canchas.setText("Mostrar registro Canchas");
		$mostrar_canchas.setIcon(ShingoUtils.createImageIcon("/images/c.png"));
		$mostrar_canchas.setToolTipText("Mostrar Canchas");
		$mostrar_canchas.addActionListener(this);

		$mostrar_canchas_disp = new JButton();
//		$mostrar_canchas_disp.setText("Disponibles");
		$mostrar_canchas_disp.setIcon(ShingoUtils.createImageIcon("/images/d.png"));
		$mostrar_canchas_disp.setToolTipText("Canchas Disponibles");
		$mostrar_canchas_disp.addActionListener(this);
		
		$mostrar_reservas = new JButton();
//		$mostrar_reservas.setText("Mostrar registro Registro");
		$mostrar_reservas.setIcon(ShingoUtils.createImageIcon("/images/registro.png"));
		$mostrar_reservas.setToolTipText("Registro Reservas");
		$mostrar_reservas.addActionListener(this);
		
		$mostrar_estadisticas = new JButton();
//		$mostrar_estadisticas.setText("Estadsticas Clientes");
		$mostrar_estadisticas.setIcon(ShingoUtils.createImageIcon("/images/estadistica.png"));
		$mostrar_estadisticas.setToolTipText("Clientes No Cumplidores");
		$mostrar_estadisticas.addActionListener(this);
		
		$registrar_reserva =  new JButton();
//		$registrar_reserva.setText("Registrar Reserva");
		$registrar_reserva.setIcon(ShingoUtils.createImageIcon("/images/guardar.png"));
		$registrar_reserva.setToolTipText("Registrar Reserva");
		$registrar_reserva.addActionListener(this);
		
		
		add($registrar_reserva);		
		addSeparator();
		add($mostrar_clientes);
		add($mostrar_canchas);
		add($mostrar_canchas_disp);
		add($mostrar_reservas);		
	
		addSeparator();
		add($mostrar_estadisticas);
	}
}
