/**
 * 
 */
package common;

import java.io.Serializable;

/**
 * Creado el 11 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
public class Mensaje implements Serializable{
	
	public static final int SOLICITAR_DOWNLOAD_CONF=0;
	public static final int SOLICITAR_UPLOAD_CONF=1;
	public static final int SOLICITAR_RESTART_DHCP=2;
	public static final int SOLICITAR_DHCP_STATUS=3;
	public static final int SOLICITAR_JOURNALCTL=4;
	public static final int SOLICITAR_DATOS_USUARIO=5;
	
	public static final int ENVIO_DHCP_CONF=6;
	public static final int ENVIO_DHCP_STATUS=7;
	public static final int ENVIO_SALIDA_RESTART_DHCP=8;
	public static final int ENVIO_JOURNALCTL=9;
	public static final int ENVIO_SALIDA_UPLOAD=10;
	public static final int ENVIO_DATOS_USUARIO=11;
	public static final int SOLICITAR_CIERRE=12;
	public static final int ACEPTAR_CIERRE=13;
	public static final int USUARIO_O_PASSWORD_NO_VALIDO=14;
	public static final int USUARIO_SIN_PRIVILEGIOS=15;
	
	
	private String resumen;
	private String login;
	private String passwd;
	private int tipoMensaje;
	private Object adjunto;
	
	public Mensaje(String resumen, String login, String passwd, int tipoMensaje) {
		super();
		this.resumen = resumen;
		this.login = login;
		this.passwd = passwd;
		this.tipoMensaje = tipoMensaje;
	}

	public Mensaje(String resumen, int tipoMensaje) {
		super();
		this.resumen = resumen;
		this.tipoMensaje = tipoMensaje;
	}

	/**
	 * @return the adjunto
	 */
	public Object getAdjunto() {
		return adjunto;
	}

	/**
	 * @param adjunto the adjunto to set
	 */
	public void setAdjunto(Object adjunto) {
		this.adjunto = adjunto;
	}

	/**
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @return the tipoMensaje
	 */
	public int getTipoMensaje() {
		return tipoMensaje;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mensaje [resumen=" + resumen + ", login=" + login + ", passwd=" + passwd + ", tipoMensaje="
				+ tipoMensaje + "]";
	}

	
	
}
