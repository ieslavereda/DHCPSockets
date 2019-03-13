/**
 * 
 */
package sockets;

import java.io.*;
import java.net.*;

import common.Mensaje;

/**
 * Creado el 12 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class DHCPSocketClient {

	public static Mensaje connect(String hostName, int portNumber, Mensaje m) throws IOException {

		Mensaje fromServer = null, salida = null;
		try (Socket socket = new Socket(hostName, portNumber);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

			System.out.println("Estableciendo conexion con: " + socket.getRemoteSocketAddress());

			Mensaje fromUser;
			GestionMensajes gm = new GestionMensajes();

			out.writeObject(m);

			while ((fromServer = (Mensaje) in.readObject()) != null) {

				switch (fromServer.getTipoMensaje()) {
				case (Mensaje.ENVIO_DHCP_CONF):
				case (Mensaje.ENVIO_DHCP_STATUS):
				case (Mensaje.ENVIO_JOURNALCTL):
				case (Mensaje.ENVIO_SALIDA_UPLOAD):
					salida = fromServer;
					break;
				}
				System.out.println("Server: " + fromServer);
				if (fromServer.getTipoMensaje() == Mensaje.ACEPTAR_CIERRE)
					break;
				else {
					fromUser = gm.procesarMensaje(fromServer);
					out.writeObject(fromUser);
				}

			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}
}