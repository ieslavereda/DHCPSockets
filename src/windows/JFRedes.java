package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Lista;
import common.Red;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFRedes extends JFrame {

	private JPanel contentPane;
	private JTextField TFDescripcion;
	private JTextField TFSubnet;
	private JTextField TFNetmask;
	private JTextField TFDNS1;
	private JTextField TFDNS2;
	private JTextField TFOptionRouters;
	private JTextField TFOptionNtp;
	private JTextField TFNetbios;
	private JTextField TFRange1;
	private JTextField TFRange2;
	private JTextField TFDefaultLeaseTime;
	private JTextField TFMaxLeaseTime;
	private JCheckBox chEnable;
	private Red net;
	private JButton btnAdd;
	private JButton btnSave;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public JFRedes(Lista<Red>ListaRedes,JFPrincipal frame) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		TFDescripcion = new JTextField();
		TFDescripcion.setColumns(10);
		
		JLabel lblSubnet = new JLabel("Subnet");
		
		JLabel lblNetmask = new JLabel("Netmask");
		
		JLabel lblOptionDomainnameservers = new JLabel("Option domain-name-servers");
		
		JLabel lblOptionRouters = new JLabel("Option routers");
		
		JLabel lblOptionNtpservers = new JLabel("Option ntp-servers");
		
		JLabel lblOptionNetbiosnameservers = new JLabel("Option netbios-name-servers");
		
		JLabel lblRange = new JLabel("Range");
		
		JLabel lblDefaultleasetime = new JLabel("Default-lease-time");
		
		JLabel lblMaxleasetime = new JLabel("Max-lease-time");
		
		TFSubnet = new JTextField();
		TFSubnet.setColumns(10);
		
		TFNetmask = new JTextField();
		TFNetmask.setColumns(10);
		
		TFDNS1 = new JTextField();
		TFDNS1.setColumns(10);
		
		TFDNS2 = new JTextField();
		TFDNS2.setColumns(10);
		
		TFOptionRouters = new JTextField();
		TFOptionRouters.setColumns(10);
		
		TFOptionNtp = new JTextField();
		TFOptionNtp.setColumns(10);
		
		TFNetbios = new JTextField();
		TFNetbios.setColumns(10);
		
		TFRange1 = new JTextField();
		TFRange1.setColumns(10);
		
		TFRange2 = new JTextField();
		TFRange2.setColumns(10);
		
		chEnable = new JCheckBox("Enable");
		
		TFDefaultLeaseTime = new JTextField();
		TFDefaultLeaseTime.setColumns(10);
		
		TFMaxLeaseTime = new JTextField();
		TFMaxLeaseTime.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				dispose();
				frame.actualizarCampos();
			}
		});
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarNuevo(ListaRedes);
				dispose();
				frame.actualizarCampos();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblMaxleasetime)
							.addGap(18)
							.addComponent(TFMaxLeaseTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDefaultleasetime)
							.addGap(18)
							.addComponent(TFDefaultLeaseTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRange)
							.addGap(18)
							.addComponent(TFRange1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(TFRange2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chEnable))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addGap(18)
							.addComponent(TFDescripcion, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNetmask)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(TFNetmask))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblSubnet)
								.addGap(18)
								.addComponent(TFSubnet, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblOptionNtpservers)
								.addGap(18)
								.addComponent(TFOptionNtp))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblOptionRouters)
								.addGap(18)
								.addComponent(TFOptionRouters, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOptionNetbiosnameservers)
									.addGap(18)
									.addComponent(TFNetbios))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOptionDomainnameservers)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(TFDNS1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(TFDNS2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(402)
							.addComponent(btnAdd)
							.addGap(18)
							.addComponent(btnSave)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(TFDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubnet)
						.addComponent(TFSubnet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNetmask)
						.addComponent(TFNetmask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionDomainnameservers)
						.addComponent(TFDNS1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFDNS2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionRouters)
						.addComponent(TFOptionRouters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionNtpservers)
						.addComponent(TFOptionNtp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionNetbiosnameservers)
						.addComponent(TFNetbios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(TFRange1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFRange2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chEnable)
						.addComponent(lblRange))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDefaultleasetime)
						.addComponent(TFDefaultLeaseTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxleasetime)
						.addComponent(TFMaxLeaseTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave)
						.addComponent(btnAdd))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
	
	public void cargarRed(Red net) {
		this.net=net;
		TFDescripcion.setText(net.getDescripcion());;
		TFSubnet.setText(net.getSubnet().getHostAddress());
		TFNetmask.setText(net.getNetmask());
		TFDNS1.setText(net.getDomainNameServers()[0].getHostAddress());
		TFDNS2.setText(net.getDomainNameServers()[1].getHostAddress());
		TFOptionRouters.setText(net.getRouter().getHostAddress());
		TFOptionNtp.setText(net.getNtpServer().getHostAddress());
		TFNetbios.setText(net.getNetbiosNameServers().getHostAddress());
		TFRange1.setText(net.getDomainNameServers()[0].getHostAddress());
		TFRange2.setText(net.getDomainNameServers()[1].getHostAddress());
		TFDefaultLeaseTime.setText(String.valueOf((net.getDefaultLeaseTime())));
		TFMaxLeaseTime.setText(String.valueOf(net.getMaxLeaseTime()));;
		chEnable.setSelected(net.getPool());
	}
	
	public void guardar() {
		
		try {
			net.setDescripcion(TFDescripcion.getText());
			net.setSubnet(InetAddress.getByName(TFSubnet.getText()));
			net.setNetmask(TFNetmask.getText());
			InetAddress[] dns = {InetAddress.getByName(TFDNS1.getText()),InetAddress.getByName(TFDNS1.getText())};
			net.setDomainNameServers(dns);
			net.setRouter(InetAddress.getByName(TFOptionRouters.getText()));
			net.setNtpServer(InetAddress.getByName(TFOptionNtp.getText()));
			net.setNetbiosNameServers(InetAddress.getByName(TFNetbios.getText()));
			InetAddress[] range = {InetAddress.getByName(TFRange1.getText()),InetAddress.getByName(TFRange1.getText())};
			net.setRange(range);
			net.setDefaultLeaseTime(Integer.parseInt(TFDefaultLeaseTime.getText()));
			net.setMaxLeaseTime(Integer.parseInt(TFMaxLeaseTime.getText()));
			net.setPool(chEnable.isSelected());
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	public void guardarNuevo(Lista<Red>ListaRedes) {
		Red neta=new Red();
		try {
			neta.setDescripcion(TFDescripcion.getText());
			neta.setSubnet(InetAddress.getByName(TFSubnet.getText()));
			neta.setNetmask(TFNetmask.getText());
			InetAddress[] dns = {InetAddress.getByName(TFDNS1.getText()),InetAddress.getByName(TFDNS1.getText())};
			neta.setDomainNameServers(dns);
			neta.setRouter(InetAddress.getByName(TFOptionRouters.getText()));
			neta.setNtpServer(InetAddress.getByName(TFOptionNtp.getText()));
			neta.setNetbiosNameServers(InetAddress.getByName(TFNetbios.getText()));
			InetAddress[] range = {InetAddress.getByName(TFRange1.getText()),InetAddress.getByName(TFRange1.getText())};
			neta.setRange(range);
			neta.setDefaultLeaseTime(Integer.parseInt(TFDefaultLeaseTime.getText()));
			neta.setMaxLeaseTime(Integer.parseInt(TFMaxLeaseTime.getText()));
			neta.setPool(chEnable.isSelected());
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListaRedes.insertar(neta);
	}
	
	public void desactivarAdd() {
		btnAdd.setVisible(false);
	}
	
	public void desactivarSave() {
		btnSave.setVisible(false);
	}
	public void desactivarTF() {
		TFDescripcion.setEditable(false);
		TFSubnet.setEditable(false);
		TFNetmask.setEditable(false);
		TFOptionRouters.setEditable(false);
		TFOptionRouters.setEditable(false);
		TFDNS1.setEditable(false);
		TFDNS2.setEditable(false);
		TFRange1.setEditable(false);
		TFRange2.setEditable(false);
		chEnable.setVisible(false);
		TFMaxLeaseTime.setEditable(false);
		TFDefaultLeaseTime.setEditable(false);
		TFOptionNtp.setEditable(false);
		TFNetbios.setEditable(false);
	}
}
