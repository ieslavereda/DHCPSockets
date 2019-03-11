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
import java.io.*;

public class DHCPManagerMultiServerThread extends Thread{

	private Socket socket = null;

	public DHCPManagerMultiServerThread(Socket socket) {
	        super("KKMultiServerThread");
	        this.socket = socket;
	    }

	public void run() {
		
		

		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			String inputLine, outputLine;
//			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}