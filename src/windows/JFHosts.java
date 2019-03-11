package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Host;
import common.Lista;
import common.Nodo;
import common.Red;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class JFHosts extends JFrame {

	private JPanel contentPane;
	private JTextField TFHostname;
	private JTextField TFFixedAddress;
	private JTextField TFHardwareEthernet;
	private JTextField TFOptionRouters;
	private JTextField TFDNS1;
	private JTextField TFDNS2;
	private JTextField TFComment;
	private JButton btnAdd;
	private JButton btnSave;
	private Host host;
	private Red r;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public JFHosts(Lista<Host>ListaHost,Red r,JFPrincipal frame) {
		super();
		this.r=r;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblHostname = new JLabel("Hostname");
		
		JLabel lblFixedaddress = new JLabel("Fixed-address");
		
		JLabel lblHardwareethernet = new JLabel("Hardware-Ethernet");
		
		JLabel lblOptionRouters = new JLabel("Option Routers");
		
		JLabel lblOptionDomainnameservers = new JLabel("Option domain-name-servers");
		
		JLabel lblComment = new JLabel("Comment");
		
		TFHostname = new JTextField();
		TFHostname.setColumns(10);
		
		TFFixedAddress = new JTextField();
		TFFixedAddress.setColumns(10);
		
		TFHardwareEthernet = new JTextField();
		TFHardwareEthernet.setColumns(10);
		
		TFOptionRouters = new JTextField();
		TFOptionRouters.setColumns(10);
		
		TFDNS1 = new JTextField();
		TFDNS1.setColumns(10);
		
		TFDNS2 = new JTextField();
		TFDNS2.setColumns(10);
		
		TFComment = new JTextField();
		TFComment.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAdd = new JButton("Add");
		panel_1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarNuevo(ListaHost);
				dispose();
				frame.cargarHostsTablas(r);
			}
		});
		
		btnSave = new JButton("Save");
		panel_1.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				dispose();
				frame.cargarHostsTablas(r);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHostname)
									.addGap(18)
									.addComponent(TFHostname, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblFixedaddress)
									.addGap(18)
									.addComponent(TFFixedAddress, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHardwareethernet)
									.addGap(18)
									.addComponent(TFHardwareEthernet, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOptionRouters)
									.addGap(18)
									.addComponent(TFOptionRouters, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblComment)
									.addGap(18)
									.addComponent(TFComment, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOptionDomainnameservers)
									.addGap(18)
									.addComponent(TFDNS1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(TFDNS2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
							.addGap(105))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHostname)
						.addComponent(TFHostname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFixedaddress)
						.addComponent(TFFixedAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHardwareethernet)
						.addComponent(TFHardwareEthernet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionRouters)
						.addComponent(TFOptionRouters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionDomainnameservers)
						.addComponent(TFDNS1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFDNS2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComment)
						.addComponent(TFComment, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	public void desactivarAdd() {
		btnAdd.setVisible(false);
	}
	
	public void desactivarSave() {
		btnSave.setVisible(false);
	}
	
	public void cargarHost(Host host) {
		this.host=host;
		TFComment.setText(host.getComentario());
		TFHostname.setText(host.getHostname());
		TFFixedAddress.setText(host.getFixedAddress().getHostAddress());
		TFHardwareEthernet.setText(host.getHardwareEthernet());
		TFOptionRouters.setText(host.getRouter().getHostAddress());
		TFDNS1.setText(host.getDns()[0].getHostAddress());
		TFDNS2.setText(host.getDns()[1].getHostAddress());
	}
	
	public void guardar() {
		
		try {
			host.setComentario(TFComment.getText());
			host.setHostname(TFHostname.getText());
			host.setFixedAddress(InetAddress.getByName(TFFixedAddress.getText()));
			host.setHardwareEthernet(TFHardwareEthernet.getText());
			host.setRouter(InetAddress.getByName(TFOptionRouters.getText()));
			InetAddress[] dns = {InetAddress.getByName(TFDNS1.getText()),InetAddress.getByName(TFDNS1.getText())};
			host.setDns(dns);
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	public void guardarNuevo(Lista<Host>ListaHost) {
		Host h = new Host();
		Nodo<Host> hosta=new Nodo<Host>(h);
		
		try {
			h.setComentario(TFComment.getText());
			h.setHostname(TFHostname.getText());
			h.setFixedAddress(InetAddress.getByName(TFFixedAddress.getText()));
			h.setHardwareEthernet(TFHardwareEthernet.getText());
			h.setRouter(InetAddress.getByName(TFOptionRouters.getText()));
			InetAddress[] dns = {InetAddress.getByName(TFDNS1.getText()),InetAddress.getByName(TFDNS2.getText())};
			h.setDns(dns);
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			if(r.isIpInSubnet(hosta.getInfo().getFixedAddress().getHostAddress())) {
				ListaHost.insertar(hosta.getInfo());
			}else {
				JOptionPane.showMessageDialog(this, "Esta direccion no pertenece a la red", "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		
	}
	
	public void desactivarTF() {
		TFComment.setEditable(false);
		TFHostname.setEditable(false);
		TFFixedAddress.setEditable(false);
		TFHardwareEthernet.setEditable(false);
		TFOptionRouters.setEditable(false);
		TFDNS1.setEditable(false);
		TFDNS2.setEditable(false);
	}
	public void setTFFixedAddress(String ip) {
		TFFixedAddress.setText(ip);
		TFFixedAddress.setEditable(false);
	}
	
	
}
