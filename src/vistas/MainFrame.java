package vistas;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.EventObject;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import negocio.Controlador;

public class MainFrame extends JFrame {	
	
	private static final long serialVersionUID = -8026416994513756565L;
	private TablaPanel $tablaPanel;	
	private Toolbar $toolbar;
	private Controlador $controlador;
	private ClientePanel $form_nuevo_cliente_panel;
	private ReservaPanel $form_nueva_reserva;
	private CanchaPanel $cancha_panel;
	private MiTabbledPane $mi_tabbled_pane;
	private JSplitPane splitPane;
	private ClienteDialog $cliente_dialog;
	private CanchasDialog $canchas_dialog;
	private EstadisticasDialog $estadisticas_dialog;
	
	
	public MainFrame() {
		//set Cabecera
				setTitle("Cuando Suene la chicharra V.000000000000000000001 BETA");	
				setIconImage(ShingoUtils.createImageIcon("/images/icon.png").getImage());
				
		$controlador = new Controlador();
		$toolbar = new Toolbar();		
		$tablaPanel = new TablaPanel();
		$cliente_dialog =  new ClienteDialog(this);
		$canchas_dialog = new CanchasDialog(this);
		$estadisticas_dialog = new EstadisticasDialog(this);
		$mi_tabbled_pane =  new MiTabbledPane();
		$mi_tabbled_pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		$form_nuevo_cliente_panel =  new ClientePanel();		
		$form_nueva_reserva =  new ReservaPanel();
		$form_nueva_reserva.setDataLista($controlador.getNro_Cliente());
		$form_nueva_reserva.setDataCombo($controlador.getnombreCanchas());		
		$cancha_panel = new CanchaPanel();
		setJMenuBar(createMenuBar());		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,$mi_tabbled_pane,$tablaPanel);
		$mi_tabbled_pane.addTab("Nueva Reserva",$form_nueva_reserva);
		$mi_tabbled_pane.setMnemonicAt(0, KeyEvent.VK_1);
		$mi_tabbled_pane.addTab("Nuevo Cliente",$form_nuevo_cliente_panel);
		$mi_tabbled_pane.setMnemonicAt(1, KeyEvent.VK_2);
		$mi_tabbled_pane.addTab("Nueva Cancha",$cancha_panel);
		$mi_tabbled_pane.setMnemonicAt(2, KeyEvent.VK_3);
		splitPane.setOneTouchExpandable(true);	
	
		
		
		/** ***************** Eventos Toolbar ************* **/
		/** *********************************************** **/	
		$toolbar.setToolbarListener(new ToolbarListener() {					
			@Override
			public void mostrasReservasEventOcurred() {
				$tablaPanel.setNombreColumnasTabla($controlador.getNombresColTablaReservas());
				$tablaPanel.setDataTabla($controlador.getDatosTablaReservas());
				$tablaPanel.activarSorter();		
			}
			
			@Override
			public void mostrarClientesEventOccured() {
				$tablaPanel.setNombreColumnasTabla($controlador.getNombresColTablaClientes());
				$tablaPanel.setDataTabla($controlador.getDatosTablaClientes());
				$tablaPanel.activarSorter();
			}
			
			@Override
			public void mostrarCanchasEventOccured() {
				$tablaPanel.setNombreColumnasTabla($controlador.getnombresColTabCanchas());
				$tablaPanel.setDataTabla($controlador.getDatosTablaCanchas());
				$tablaPanel.activarSorter();
			}

			@Override
			public void mostrasEstadisticasEventOcurred() {
				$estadisticas_dialog.setDataMapa($controlador.readMenosCumplidores());				
				$estadisticas_dialog.setVisible(true);
			}

			@Override
			public void actualizarReservaEventOcurred() {
				$controlador.actualizarReservas($tablaPanel.getDataTable());
				$tablaPanel.setDataTabla($controlador.getDatosTablaReservas());
				$tablaPanel.activarSorter();	
			}

			@Override
			public void mostrarFomularioReservaEventOcurred() {
				splitPane.setDividerLocation((int)$form_nueva_reserva.getMinimumSize().getWidth());				
				$form_nueva_reserva.setVisible(true);		
				
			}

			@Override
			public void mostrarCanchasDispEventOcurred() {
				$canchas_dialog.setVisible(true);				
			}
		});
		
		
		/* ***************************************************************** */
		/** **************** Evento del Formmulario Cliente Registrado **** **/
		/** *************************************************************** **/
		$form_nuevo_cliente_panel.setFormListener(new FormListener() {
			@Override
			public void formEventOccurred(EventObject e) {
				if ($controlador.addCliente((ClienteEvent) e)) {
					$tablaPanel.setDataTabla($controlador.getDatosTablaClientes());	
					$form_nueva_reserva.agregarNroCliente(((ClienteEvent) e).get$num_cliente());				}else{
					ShingoUtils.shingoMensaje("El Nro de Cliente ya esta en uso",
							"WARNING!!",JOptionPane.WARNING_MESSAGE);					
				}								
			}			
		});
		
		/** **************** Evento del Formmulario Cancha **************** **/
		/** *************************************************************** **/
		$cancha_panel.setFormListener(new FormListener() {			
			@Override
			public void formEventOccurred(EventObject e) {
				if ($controlador.addCancha((CanchaEvent) e)) {	
					$tablaPanel.setDataTabla($controlador.getDatosTablaCanchas());					
					$form_nueva_reserva.agregarCanchaNueva($controlador.getIdCancha());
					$cancha_panel.resetPanel();					
				}else{
					ShingoUtils.shingoMensaje("El Nombre de la Cancha ya esta en uso",
							"WARNING!!",JOptionPane.WARNING_MESSAGE);					
				}						
			}
		});
		
		
		
		/** ************* Eventos del formulario Eventos *************** **/
		/** ************************************************************ **/
		$form_nueva_reserva.setFormListener(new PanelListener() {	
			
			@Override
			public void mostrarClientesEventOccured(ReservaEvent ev) {
				$form_nueva_reserva.setCliente($controlador.readCliente(ev.get$id(), true)
						,$controlador.imagen(ev.get$id(), true));			
			}

			@Override
			public void mostrarCanchasEventOccured(ReservaEvent ev) {
				$form_nueva_reserva.setDatosCancha($controlador.readCancha(ev.get$id()));
			}

			@Override
			public void guardarReservaEventOccured(ReservaEvent ev) {
				if (!$controlador.getCanchaDisponible(ev)) {				
					Object msj = "RESERVA: "
							+ System.getProperty("line.separator")
							+ "Monto: $" + ev.get$monto()
							+ System.getProperty("line.separator")
							+ "Fecha: " + ev.get$fecha().toString() 
							+ System.getProperty("line.separator")
							+ "Tiempo: " + (ev.get$hora_fin()-ev.get$hora_inicio())+" hs" 
							+ System.getProperty("line.separator")
							+ "Realmente decea guardar la Reserva?";					
					int seleccion = shingoOption(msj,"WARNING!",JOptionPane.YES_NO_CANCEL_OPTION
									,JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == seleccion) {
						$controlador.saveReserva(ev);						
						$tablaPanel.setDataTabla($controlador.getDatosTablaReservas());	
					}
				}else{
					Object msj = "La cancha: "
							+ System.getProperty("line.separator")
							+ "ID: " + ev.get$id_cancha() 
							+ System.getProperty("line.separator")
							+ "Nombre: " + ev.get$nombre_cancha() 
							+ System.getProperty("line.separator")
							+ "ya ha sido reservada";				
					shingoMensaje(msj,"WARNING!",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		
		$tablaPanel.setClienteTableListener(new ClienteTablaListener() {			
			@Override
			public void rowDelete(int row) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mostrarInformacionCliente(int $id_cliente) {				
				$cliente_dialog.setCliente($controlador.readCliente($id_cliente, false), 
						$controlador.imagen($id_cliente, false), $controlador.cantidadReservas($id_cliente));
				$cliente_dialog.setVisible(true);		
			}
			
			@Override
			public void mostrarInformacionCancha(int $id_cancha) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//mostrar canchas disponibles
		$canchas_dialog.setCanchaDialogListener(new CanchaDialogListener() {			
			@Override
			public void preferencesSet(Date $fecha) {				
				$tablaPanel.setNombreColumnasTabla($controlador.getnombresColTabCanchas());
				$tablaPanel.setDataTabla($controlador.getDatosTablaCanchasDisp($fecha));
				$tablaPanel.activarSorter();			
			}
		});
		
		/** ***************** SetUp MainFrame ************* **/
		/** *********************************************** **/		
		setLayout(new BorderLayout());
//		add($tablaPanel,BorderLayout.CENTER);
		add(splitPane, BorderLayout.CENTER);
		add($toolbar,BorderLayout.PAGE_START);		
		
		setMinimumSize(new Dimension(500, 400));
		setSize(600,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");

		// JMenuItem showFormItem = new JMenuItem("Person Form");
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		
		

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPane.setDividerLocation((int)$mi_tabbled_pane.getMinimumSize().getWidth());
				}
				$mi_tabbled_pane.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		
		


		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// String $text = JOptionPane.showInputDialog(MainFrame.this,
				// "Enter your user name"
				// ,
				// "Enter User Name",JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);
				// System.out.println($text);

				int $action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the appication?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if ($action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}

			}
		});
		return menuBar;

	}

	
	private void  shingoMensaje(Object $msj,String $titulo,int $plainMessage) {		
		JOptionPane.showMessageDialog(null, $msj,$titulo,$plainMessage);		
	}

	public int shingoOption(Object $msj,String $titulo, int $optionType,int $plainMessage){
		int $seleccion = JOptionPane.showConfirmDialog(this, $msj, $titulo, $optionType,
				$plainMessage);		
		return $seleccion;		
	}


}
