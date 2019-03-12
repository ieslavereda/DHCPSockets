/**
 * 
 */
package main;

/**
 * Creado el 11 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
import java.io.*;
import java.net.ServerSocket;

import sockets.DHCPManagerMultiServerThread;

public class DHCPManagerMultiServer {

	private static int conexionesActivas = 0;

	public static void main(String[] args) throws IOException {

		int portNumber = 2000;
		
		System.out.println("Socket iniciado en el puerto " + portNumber);

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (true) {
				new DHCPManagerMultiServerThread(serverSocket.accept()).start();
				System.out.println("Conexiones iniciada nº: " + (++conexionesActivas));
			}
		} catch (IOException e) {
			System.err.println("No se puede escuchar por el puerto " + portNumber);
			System.exit(-1);
		}
	}
}