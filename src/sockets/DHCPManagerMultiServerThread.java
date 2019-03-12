/**
 * 
 */
package sockets;

/**
 * Creado el 11 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import common.Mensaje;

import java.io.*;

public class DHCPManagerMultiServerThread extends Thread {

	private Socket socket = null;

	public DHCPManagerMultiServerThread(Socket socket) {
		super("KKMultiServerThread");
		this.socket = socket;
	}

	public void run() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-uuuu H:m:s");
		String instante = LocalDateTime.now().format(dtf);
		System.out.println(instante + " -> Conexion realizada desde la IP " + socket.getRemoteSocketAddress());

		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
			Mensaje mensajeRecibido, mensajeAEnviar;
			GestionMensajes gm = new GestionMensajes();

			while ((mensajeRecibido = (Mensaje) in.readObject()) != null) {
				mensajeAEnviar = gm.procesarMensaje(mensajeRecibido);
				out.writeObject(mensajeAEnviar);
				if (mensajeAEnviar.getTipoMensaje() == Mensaje.ACEPTAR_CIERRE)
					break;
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}