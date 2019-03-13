package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.util.SubnetUtils;
import common.Host;
import common.Lista;
import common.Mensaje;
import common.Nodo;
import common.Red;
import sockets.DHCPSocketClient;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * creado el 6 mar. 2019
 * 
 * @author raul
 *
 *         En el frame principal es donde se van a visualizar todos los cambios
 *         del fichero dhcp
 */
public class JFPrincipal extends JFrame {

	private JPanel contentPane;
	private String configuracionGlobal;
	private String informacion;
	private JTextPane TPGlobal;
	private JTextPane TPInfo;
	private JTable table;
	private JTable table_1;
	private Lista<Red> ListaRedes;
	private JComboBox<Red> comboBox;
	private JMenu mnHelp;
	private JLabel lblHost;
	private JFPrincipal framePrincipal;
	private JTextPane textPaneServidor;

	/**
	 * Lanzamos la aplicación
	 */
	/**
	 * Se crea el frame principal desde el que podremos realizar casi todas las
	 * acciones
	 */
	public JFPrincipal() {
		this.configuracionGlobal = "";
		this.informacion = "";
		framePrincipal = this;
		ListaRedes = new Lista<Red>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 674);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null,
						"Si carga los datos se sobreescribiran los que habian �Confirmar carga?", "Info",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				if (opcion == JOptionPane.YES_OPTION) {
					File f = seleccionarArchivo();
					if (f != null)
						cargarDatosDesdeFichero(f);
					actualizarCampos();
					TPGlobal.setText(configuracionGlobal);
					TPInfo.setText(informacion);
//					System.out.println(configuracionGlobal);
				}
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarArchivoGuardar();
				System.out.println(crearStringGuardar());
			}
		});
		mnFile.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);

		mnHelp = new JMenu("Help");
		mnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		menuBar.add(mnHelp);

		JMenuItem mntmIfno = new JMenuItem("Info");
		mntmIfno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info frame = new Info();
				frame.setVisible(true);
			}
		});
		mnHelp.add(mntmIfno);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelServidor = new JPanel();
		tabbedPane.addTab("Servidor", null, panelServidor, null);

		JPanel panel_6 = new JPanel();

		JButton btnDownload = new JButton("Download dhcp.conf");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obtenerDHCP();
			}
		});
		GroupLayout gl_panelServidor = new GroupLayout(panelServidor);
		gl_panelServidor
				.setHorizontalGroup(gl_panelServidor.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panelServidor.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelServidor
										.createParallelGroup(Alignment.TRAILING).addComponent(panel_6,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
										.addComponent(btnDownload))
								.addContainerGap()));
		gl_panelServidor
				.setVerticalGroup(gl_panelServidor.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panelServidor.createSequentialGroup().addContainerGap()
								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
								.addComponent(btnDownload).addGap(20)));
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_4 = new JScrollPane();
		panel_6.add(scrollPane_4, BorderLayout.CENTER);

		textPaneServidor = new JTextPane();
		scrollPane_4.setViewportView(textPaneServidor);
		panelServidor.setLayout(gl_panelServidor);

		JPanel panelGlobal = new JPanel();
		tabbedPane.addTab("Global", null, panelGlobal, null);

		JPanel panel = new JPanel();

		JLabel lblNewLabel = new JLabel("Configuracion Global");
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblGlobal = new JLabel("Has realizado cambios, guarda antes de continuar o se perderán");
		lblGlobal.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		TPGlobal = new JTextPane();
		TPGlobal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!TPGlobal.getText().equals(configuracionGlobal)) {
					lblGlobal.setVisible(true);
				} else {
					lblGlobal.setVisible(false);
				}

			}
		});

		scrollPane.setViewportView(TPGlobal);

		JPanel panel_1 = new JPanel();

		JLabel lblInformacion = new JLabel("Informacion");

		JPanel panel_5 = new JPanel();

		GroupLayout gl_panelGlobal = new GroupLayout(panelGlobal);
		gl_panelGlobal.setHorizontalGroup(gl_panelGlobal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGlobal.createSequentialGroup().addContainerGap().addGroup(gl_panelGlobal
						.createParallelGroup(
								Alignment.TRAILING)
						.addGroup(gl_panelGlobal.createSequentialGroup()
								.addGroup(gl_panelGlobal.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
										.addComponent(lblInformacion))
								.addContainerGap())
						.addGroup(gl_panelGlobal.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE).addGap(12))
						.addGroup(
								gl_panelGlobal.createSequentialGroup()
										.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 228,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(Alignment.LEADING,
								gl_panelGlobal.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
										.addComponent(lblGlobal).addGap(122)))));
		gl_panelGlobal.setVerticalGroup(gl_panelGlobal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGlobal.createSequentialGroup().addGap(9)
						.addGroup(gl_panelGlobal.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(lblGlobal))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblInformacion).addGap(9)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnGuardar = new JButton("Guardar");
		panel_5.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configuracionGlobal = TPGlobal.getText();
				informacion = TPInfo.getText();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		panel_5.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TPGlobal.setText(configuracionGlobal);
				TPInfo.setText(informacion);
			}
		});
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		TPInfo = new JTextPane();
		TPInfo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!TPGlobal.getText().equals(configuracionGlobal) || !TPInfo.getText().equals(informacion)) {
					lblGlobal.setVisible(true);
				} else {
					lblGlobal.setVisible(false);
				}

			}
		});

		scrollPane_1.setViewportView(TPInfo);
		panelGlobal.setLayout(gl_panelGlobal);

		JPanel panelRedes = new JPanel();
		tabbedPane.addTab("Redes", null, panelRedes, null);

		JPanel panel_4 = new JPanel();

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFRedes frame = new JFRedes(ListaRedes, framePrincipal);
				frame.setVisible(true);
				frame.desactivarSave();
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1) {
					JFRedes frame = new JFRedes(ListaRedes, framePrincipal);
					frame.setVisible(true);
					frame.cargarRed(encontrarRed(table_1.getSelectedRow()));
					frame.desactivarAdd();
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1) {
					ListaRedes.eliminarPosicion(table_1.getSelectedRow());
					actualizarCampos();
				}
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarCampos();

			}
		});

		JButton btnView_1 = new JButton("View");
		btnView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1) {
					JFRedes frame = new JFRedes(ListaRedes, framePrincipal);
					frame.setVisible(true);
					frame.cargarRed(encontrarRed(table_1.getSelectedRow()));
					frame.desactivarAdd();
					frame.desactivarSave();
					frame.desactivarTF();
				}

			}
		});
		GroupLayout gl_panelRedes = new GroupLayout(panelRedes);
		gl_panelRedes.setHorizontalGroup(gl_panelRedes.createParallelGroup(Alignment.TRAILING).addGroup(gl_panelRedes
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelRedes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelRedes.createSequentialGroup().addComponent(btnView_1)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRefresh).addGap(18)
								.addComponent(btnDelete).addGap(18).addComponent(btnEdit).addGap(18)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panelRedes.setVerticalGroup(gl_panelRedes.createParallelGroup(Alignment.LEADING).addGroup(gl_panelRedes
				.createSequentialGroup().addContainerGap()
				.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_panelRedes.createParallelGroup(Alignment.BASELINE).addComponent(btnAdd)
						.addComponent(btnEdit).addComponent(btnDelete).addComponent(btnRefresh).addComponent(btnView_1))
				.addContainerGap(22, Short.MAX_VALUE)));
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_4.add(scrollPane_3, BorderLayout.CENTER);

		table_1 = new JTable();
		scrollPane_3.setViewportView(table_1);
		panelRedes.setLayout(gl_panelRedes);

		JPanel panelHosts = new JPanel();
		tabbedPane.addTab("Hosts", null, panelHosts, null);
		panelHosts.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panelHosts.add(panel_2, BorderLayout.CENTER);

		comboBox = new JComboBox<Red>();
		comboBox.setEditable(true);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarHostsTablas((Red) comboBox.getSelectedItem());
				Red r2 = (Red) comboBox.getSelectedItem();
				lblHost.setText(String.valueOf(r2.getListaHosts().getCantidad()));
			}
		});

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();
				actualizarCampos();
				lblHost.setText(String.valueOf(r2.getListaHosts().getCantidad()));
			}
		});

		JPanel panel_3 = new JPanel();

		JButton btnEditar = new JButton("Edit");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();

				JFHosts frame = new JFHosts(r2.getListaHosts(), r2, framePrincipal);
				frame.setVisible(true);
				frame.cargarHost(encontrarHost(table.getSelectedRow(), r2));
				frame.desactivarAdd();

			}
		});

		JButton btnBorrar = new JButton("Remove");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();
				if (table.getSelectedRow() != -1) {
					r2.getListaHosts().eliminarPosicion(table.getSelectedRow());
				}
			}
		});

		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();
				JFHosts frame = new JFHosts(r2.getListaHosts(), r2, framePrincipal);
				frame.setVisible(true);
				frame.desactivarSave();
			}
		});

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();
				JFHosts frame = new JFHosts(r2.getListaHosts(), r2, framePrincipal);
				frame.setVisible(true);
				frame.cargarHost(encontrarHost(table.getSelectedRow(), r2));
				frame.desactivarAdd();
				frame.desactivarSave();
				frame.desactivarTF();
			}
		});

		JButton btnIpsFree = new JButton("Ip's Free");
		btnIpsFree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red r2 = (Red) comboBox.getSelectedItem();
				JFListIp frame = new JFListIp(r2, framePrincipal);
				frame.setVisible(true);

			}
		});

		JLabel lblNumeroDeHosts = new JLabel("Numero de Hosts en la Red:");

		lblHost = new JLabel("0");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(
						gl_panel_2
								.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2.createSequentialGroup()
										.addContainerGap().addGroup(gl_panel_2
												.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel_2
														.createSequentialGroup()
														.addComponent(
																comboBox, GroupLayout.PREFERRED_SIZE, 216,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(btnActualizar))
												.addGroup(gl_panel_2.createSequentialGroup()
														.addComponent(lblNumeroDeHosts)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(lblHost)
														.addPreferredGap(ComponentPlacement.RELATED, 186,
																Short.MAX_VALUE)
														.addComponent(btnIpsFree)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnView)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnAdd_1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnEditar)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnBorrar))
												.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE))
										.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(29)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnActualizar))
						.addGap(18).addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(btnBorrar)
								.addComponent(btnEditar).addComponent(btnAdd_1).addComponent(btnView)
								.addComponent(btnIpsFree).addComponent(lblNumeroDeHosts).addComponent(lblHost))
						.addContainerGap(29, Short.MAX_VALUE)));
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_3.add(scrollPane_2, BorderLayout.CENTER);

		table = new JTable();
		scrollPane_2.setViewportView(table);
		panel_2.setLayout(gl_panel_2);

	}

	// Este metodo se va a encargar de sacarnos una ventana del explorador
	// y delvolvernos el archivo que seleccionemos en dicha ventana
	// @return File Fichero que se devolverá al seleccionarlo en el explorador
	private File seleccionarArchivo() {
		File file = null;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new FileNameExtensionFilter("Configuration .conf", "conf"));

		int opcion = jfc.showOpenDialog(this);

		if (opcion == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
		}
		return file;
	}

	// En este método le pasamos un archivo y en este se creará un flujo
	// de lectura en el cual se irá repartiendo el contenido a las variables
	// correspondientes.
	// @param file Fichero del cual se cargará la información

	private void cargarDatosDesdeFichero(File file) {
		BufferedReader is = null;
		String linea = "";
		Host h;
		Nodo<Red> aux;
		boolean insertado = false;
		try {

			is = new BufferedReader(new FileReader(file));

			while ((linea = is.readLine()) != null) {

				if (linea.contains("# Configuracion global")) {
					cargarConfiguracionGlobal(is);
				}
				if (linea.contains("# Info")) {
					cargarInformacion(is);
				}
				if (linea.contains("# Red")) {
					cargarRedes(is, linea);
				}
				if (linea.contains("host")) {

					aux = ListaRedes.getCabeza();
					h = cargarHosts(is, linea);

					while (aux != null && !aux.getInfo().isIpInSubnet(h.getFixedAddress().getHostAddress())) {

						aux = aux.getSiguiente();

					}

					if (aux == null) {
						JOptionPane.showMessageDialog(this, "Red no encontrada", "Error", JOptionPane.ERROR_MESSAGE,
								null);
					} else {
						insertado = aux.getInfo().insertarHost(h);
						if (!insertado) {
							JOptionPane.showMessageDialog(this,
									"La direccion del host " + h.getHostname()
											+ " ya esta siendo utilizada por otro host",
									"Error", JOptionPane.ERROR_MESSAGE, null);
						}
					}
				}

			}
			cargarComboBox();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

	}

	/**
	 * Este metodo mostrará una ventana a través de la cual podremos guardar en un
	 * fichero todos los cambios realizados en la aplicación
	 */
	public void seleccionarArchivoGuardar() {
		int opcion;
		JFileChooser jff = new JFileChooser();
		jff.setFileFilter(new FileNameExtensionFilter("Configuration .conf", "conf"));
		opcion = jff.showSaveDialog(null);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			guardar(jff.getSelectedFile());
			JOptionPane.showMessageDialog(null, "Se ha guardado correctamente", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Pasandole un archivo, este metodo se va a encargar de guardar mediante un
	 * flujo de escritura todos los datos que se hayan gestionado en la aplicación
	 * 
	 * @param file fichero en el que se guardará la información
	 */
	public void guardar(File file) {
		PrintWriter oos = null;

		try {
			oos = new PrintWriter(new FileWriter(file.getAbsolutePath() + ".conf"));
			oos.write(crearStringGuardar());

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			oos.close();
		}
	}

	// Metodo que mediante el flujo carga la configuración global de un archivo en
	// una variable
	// @param is Flujo de datos

	private void cargarConfiguracionGlobal(BufferedReader is) {
		configuracionGlobal = "";
		String linea;
		try {
			while ((linea = is.readLine()) != null && !linea.contains("# Fin configuracion global")) {
				configuracionGlobal += linea + "\n";
			}

		} catch (Exception e) {
			try {
				is.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}

		}

	}

	// Metodo que mediante el flujo carga la información de un archivo dhcp.conf en
	// una variable
	// @param is Flujo de datos
	private void cargarInformacion(BufferedReader is) {
		informacion = "";
		String linea;
		try {
			while ((linea = is.readLine()) != null && !linea.contains("# Fin info")) {
				informacion += linea + "\n";
			}

		} catch (Exception e) {
			try {
				is.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}

		}
	}

	// Metodo que mediante el flujo carga las redes de un archivo en un objeto red
	// y las va introduciendo dentro de una lista si cumplen con los requisitos.
	// @param is flujo de datos
	// @param linea2 linea que se pasa para no perderla en la lectura del flujo
	private void cargarRedes(BufferedReader is, String linea2) {
		String linea;
		String[] campos;
		String[] campos2;
		Red net;
		InetAddress subnet = null;
		String netmask = null;
		InetAddress[] domainNameServers = new InetAddress[2];
		InetAddress router = null;
		InetAddress ntpServer = null;
		InetAddress netbiosNameServers = null;
		InetAddress[] range = new InetAddress[2];
		String descripcion = "";
		int defaultLeaseTime = 0;
		int maxLeaseTime = 0;
		boolean pool = false;

		try {
			campos2 = linea2.trim().split(" ");
			descripcion += campos2[1] + " " + campos2[2];
//			System.out.println(descripcion);
			while ((linea = is.readLine()) != null && !linea.contains("}")) {
				campos = linea.trim().replaceAll(",", "").replaceAll(";", "").split(" ");
				if (linea.contains("subnet")) {
					subnet = InetAddress.getByName(campos[1]);
					netmask = campos[3];
				}
				if (linea.contains("domain-name-servers")) {
					domainNameServers[0] = InetAddress.getByName(campos[2]);
					domainNameServers[1] = InetAddress.getByName(campos[3]);
				}
				if (linea.contains("routers")) {
					router = InetAddress.getByName(campos[2]);
				}
				if (linea.contains("ntp-servers")) {
					ntpServer = InetAddress.getByName(campos[2]);
				}
				if (linea.contains("netbios-name-servers")) {
					netbiosNameServers = InetAddress.getByName(campos[2]);
				}
				if (linea.contains("range")) {

					if (linea.contains("#")) {
						range[0] = InetAddress.getByName(campos[2]);
						range[1] = InetAddress.getByName(campos[3]);
						pool = false;

					} else {
						range[0] = InetAddress.getByName(campos[1]);
						range[1] = InetAddress.getByName(campos[2]);
						pool = true;
					}

				}

				if (linea.contains("default-lease-time")) {
					defaultLeaseTime = Integer.parseInt(campos[1]);
				}
				if (linea.contains("max-lease-time")) {
					maxLeaseTime = Integer.parseInt(campos[1]);
				}

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				is.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}

		}
		net = new Red(subnet, netmask, domainNameServers, router, ntpServer, netbiosNameServers, range,
				defaultLeaseTime, maxLeaseTime, pool, descripcion);
		if (!ListaRedes.insertar(net)) {
			JOptionPane.showMessageDialog(this, "Esta direccion ya esta siendo utilizada", "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
	}

	/**
	 * Carga una lista de redes en una tabla
	 */
	public void cargarRedesEnTabla() {
		Nodo<Red> nodo = ListaRedes.getCabeza();
		DefaultTableModel dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Subnet", "Netmask", "Descripcion", "Pool" });
		Red net;

		while (nodo != null) {

			net = nodo.getInfo();

			dtm.addRow(new String[] { net.getSubnet().getHostName(), net.getNetmask(), net.getDescripcion(),
					(net.getPool()) ? "Activado" : "Desactivado" });

			nodo = nodo.getSiguiente();
		}

		table_1.setModel(dtm);

	}

	/**
	 * Método que carga los cambios que se hayan realizado en las tablas de redes y
	 * host y en el comboBox de host para que no haya selecciones vacías
	 */
	public void actualizarCampos() {

		cargarRedesEnTabla();
		cargarComboBox();
		if (comboBox.getSelectedIndex() != -1) {
			cargarHostsTablas((Red) comboBox.getSelectedItem());
		}
	}

	// Carga el comboBox de una lista de redes

	private void cargarComboBox() {
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
		dcbm.removeAllElements();

		Nodo<Red> nodoRedes = ListaRedes.getCabeza();

		while (nodoRedes != null) {
			// dcbm.addElement(nodoEjemplar.getInfo().getId());
			dcbm.addElement(nodoRedes.getInfo());
			nodoRedes = nodoRedes.getSiguiente();
		}
		comboBox.setModel(dcbm);
	}

	// Metodo que mediante el flujo carga los hosts de un archivo
	// @param is flujo de lectura
	// @param linea2 linea que le pasamos junto con el flujo para que no se pierda
	// la información
	// @return Host Host que devuelve con todos sus campos cargados
	private Host cargarHosts(BufferedReader is, String linea2) {
		String linea;
		String[] campos;
		String[] campos2;
		Host host;
		String hostname = null;
		InetAddress fixedAddress = null;
		String hardwareEthernet = null;
		InetAddress router = null;
		InetAddress[] dns = new InetAddress[2];
		String comentario = "";

		try {
			campos2 = linea2.trim().split(" ");
			hostname = campos2[1];

			while ((linea = is.readLine()) != null && !linea.contains("}")) {
				campos = linea.trim().replaceAll(",", "").replaceAll(";", "").split(" ");
				if (linea.contains("fixed-address")) {
					fixedAddress = InetAddress.getByName(campos[1]);
				}
				if (linea.contains("hardware")) {
					hardwareEthernet = campos[2];
				}
				if (linea.contains("routers")) {
					router = InetAddress.getByName(campos[2]);
				}
				if (linea.contains("domain-name-servers")) {
					dns[0] = InetAddress.getByName(campos[2]);
					dns[1] = InetAddress.getByName(campos[3]);
				}
				if (linea.contains("# comentario") && campos.length >= 2) {
					for (int i = 2; i < campos.length; i++) {
						comentario += campos[i] + " ";
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				is.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}

		}
		host = new Host(hostname, fixedAddress, hardwareEthernet, router, dns, comentario);

		return host;

	}

	/**
	 * Metodo que se encarga de cargar la lista de hosts en una tabla, se le pasa
	 * una red para que pueda obtener la lista de hosts
	 * 
	 * @param r Red para obtener la lista de hosts
	 */
	public void cargarHostsTablas(Red r) {
		Lista<Host> LH = r.getListaHosts();
		// r.listIps();
		Nodo<Host> nodo = LH.getCabeza();
		DefaultTableModel dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Host", "Fixed-Address", "Comentario", "Hardware-Ethernet" });
		Host host;

		while (nodo != null) {

			host = nodo.getInfo();

			dtm.addRow(new String[] { host.getHostname(), host.getFixedAddress().getHostAddress(), host.getComentario(),
					host.getHardwareEthernet() });

			nodo = nodo.getSiguiente();
		}

		table.setModel(dtm);
	}

	/**
	 * Metodo que te devuelve una red mediante el paso de la posición que tenga la
	 * misma en una tabla
	 * 
	 * @param posicion posición que ocupa en la tabla
	 * @return
	 */
	public Red encontrarRed(int posicion) {
		Nodo<Red> aux = ListaRedes.getCabeza();
		Red r;
		for (int i = 0; i < posicion; i++) {
			aux = aux.getSiguiente();
		}
		r = aux.getInfo();
		return r;
	}

	/**
	 * Metodo que te devuelve un host mediante el paso de la posicion en tabla y la
	 * red en la que esté ubicado el host
	 * 
	 * @param posicion posición que ocupa en la tabla
	 * @param r        red en la que está i
	 * @return
	 */
	public Host encontrarHost(int posicion, Red r) {
		Nodo<Host> aux = r.getListaHosts().getCabeza();
		Host h;
		for (int i = 0; i < posicion; i++) {
			aux = aux.getSiguiente();
		}
		h = aux.getInfo();
		return h;
	}

	/**
	 * Metodo que se encarga de juntar todas las variables y objetos en un mismo
	 * string para poder guardarlo en un fichero
	 * 
	 * @return String ya relleno con los datos que se almacenaran en el fichero
	 */
	public String crearStringGuardar() {
		String aux = "";
		aux += "# Configuracion global\n" + configuracionGlobal + "\n" + "# Fin configuracion global\n" + "# Info\n"
				+ informacion + "\n" + "# Fin info\n" + cargarStringRedes() + "\n" + "\n";
		return aux;
	}

	/**
	 * Método que irá rellenando el string de redes que se guardarán y hará un
	 * llamamiento a otro método para que se rellene también el string de hosts por
	 * cada red
	 * 
	 * @return
	 */
	public String cargarStringRedes() {
		String aux = "", aux2 = "";
		Nodo<Red> nodo = ListaRedes.getCabeza();
		Red net;
		while (nodo != null) {
			net = nodo.getInfo();
			nodo = nodo.getSiguiente();
			aux += net.stringGuardarRed();
			aux2 += cargarStringHosts(net);
		}

		return aux + "\n" + aux2;

	}

	private void obtenerDHCP() {

		try {
			Mensaje respuesta = DHCPSocketClient.connect("10.0.3.1", 2000,
					new Mensaje("Solicitamos fichero dhcp", Mensaje.SOLICITAR_DOWNLOAD_CONF));
			textPaneServidor.setText(respuesta.getResumen());

			File f = new File("tmp.conf");
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			pw.print(respuesta.getResumen());
			cargarDatosDesdeFichero(f);
			f.delete();
			actualizarCampos();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método que irá cargando el string de hosts para ser guardado
	 * 
	 * @param r Red en la que esta situada la lista de hosts
	 * @return Devuelve el String con la lista de hosts de una red
	 */
	public String cargarStringHosts(Red r) {
		String aux = "";
		Lista<Host> LH = r.getListaHosts();
		Nodo<Host> nodo = LH.getCabeza();
		Host host;
		aux += "\n#************** Hosts de la " + r.getDescripcion() + " *******************\n";
		while (nodo != null) {
			host = nodo.getInfo();
			nodo = nodo.getSiguiente();
			aux += host.stringGuardarHost();
		}
		return aux;
	}
}
