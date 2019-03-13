/**
 * 
 */
package sockets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import common.Mensaje;

/**
 * Creado el 12 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class GestionMensajes {

	public Mensaje procesarMensaje(Mensaje mensajeEntrada) {
		Mensaje mensajeRespuesta = null;

		switch (mensajeEntrada.getTipoMensaje()) {
		case (Mensaje.SOLICITAR_CIERRE):
			mensajeRespuesta = new Mensaje("Recibida solicitud de cierre. Aceptamos cierre", Mensaje.ACEPTAR_CIERRE);
			System.out.println(mensajeRespuesta.getResumen());
			break;
		case (Mensaje.ACEPTAR_CIERRE):
			mensajeRespuesta = new Mensaje("Recibida aceptacion de cierre. Aceptamos cierre", Mensaje.ACEPTAR_CIERRE);
			System.out.println(mensajeRespuesta.getResumen());
			break;
		case (Mensaje.SOLICITAR_DOWNLOAD_CONF):
			String archivo = obtenerDatosDHCP();
			mensajeRespuesta = new Mensaje(archivo, Mensaje.ENVIO_DHCP_CONF);
			break;
		case (Mensaje.ENVIO_DHCP_CONF):
			mensajeRespuesta = new Mensaje("Recibido datos Dhcp, solicitando cierre", Mensaje.SOLICITAR_CIERRE);
			break;
		}

		return mensajeRespuesta;
	}

	private String obtenerDatosDHCP() {
		String salida = "", fila = "";

		BufferedReader in = null;

		try {

			in = new BufferedReader(new FileReader("/home/jalonso/dhcpd.conf"));
			while ((fila = in.readLine()) != null) {
				salida += fila + "\n";
			}

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return salida;
	}

}
