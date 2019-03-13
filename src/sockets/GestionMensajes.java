/**
 * 
 */
package sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
			//System.out.println(archivo);
			mensajeRespuesta = new Mensaje(archivo, Mensaje.ENVIO_DHCP_CONF);
			break;
		case (Mensaje.ENVIO_DHCP_CONF):
			System.out.println(mensajeEntrada.getResumen());
			mensajeRespuesta = new Mensaje("Recibido datos Dhcp, solicitando cierre", Mensaje.SOLICITAR_CIERRE);
			break;
		case (Mensaje.SOLICITAR_UPLOAD_CONF):
			String salida = grabarDHCP(mensajeEntrada.getResumen());
			mensajeRespuesta = new Mensaje(salida, Mensaje.ENVIO_SALIDA_UPLOAD);
			break;
		case (Mensaje.ENVIO_SALIDA_UPLOAD):
			mensajeRespuesta = new Mensaje("Recibido datos envio upload, solicitando cierre", Mensaje.SOLICITAR_CIERRE);
			break;
		}

		return mensajeRespuesta;
	}

	private String grabarDHCP(String datos) {
		String salida = "Todo OK.";

		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter("/home/jalonso/dhcpd.conf.backup"));
			out.write(datos);
		} catch (Exception e) {
			e.printStackTrace();
			salida = e.toString();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return salida;
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
