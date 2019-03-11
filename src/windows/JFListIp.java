package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.util.SubnetUtils;

import common.Host;
import common.Lista;
import common.Nodo;
import common.Red;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFListIp extends JFrame {

	private JPanel contentPane;
	private JTable table2;
	private Red r;
	Lista<InetAddress> lip;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JFListIp(Red r,JFPrincipal framePrincipal) {
		lip = new Lista<InetAddress>();
		this.r=r;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnUseIp = new JButton("Use ip");
		btnUseIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table2.getSelectedRow() != -1) {
					JFHosts frame = new JFHosts(r.getListaHosts(), r,framePrincipal);
					frame.setVisible(true);
					frame.setTFFixedAddress(lip.obtenerPosicion(table2.getSelectedRow()).getHostAddress());
					frame.desactivarSave();
					dispose();
					
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnUseIp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnUseIp))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table2 = new JTable();
		scrollPane.setViewportView(table2);
		panel.setLayout(gl_panel);
		cargarTablaIps();
	}
	
	private Lista<InetAddress> crearListaIps() {
		String[] li = r.listIps();
			try {
				for (int i=0 ; i <li.length; i++) {
					if (!r.existeHost(InetAddress.getByName(li[i]))){
						lip.insertar(InetAddress.getByName(li[i]));
					}
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lip;
	}	
		
		
	private void cargarTablaIps() {
		lip = crearListaIps();
		DefaultTableModel dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Ip"});
		Nodo<InetAddress> aux = lip.getCabeza();
		InetAddress ip;

		while (aux != null) {

			ip = aux.getInfo();

			dtm.addRow(new String[] { aux.getInfo().getHostAddress() });

			aux = aux.getSiguiente();
		}

		table2.setModel(dtm);
	}
	
	
	

}
