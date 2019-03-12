/**
 * 
 */
package sockets;

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
		}

		return mensajeRespuesta;
	}
}
