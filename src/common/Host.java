package common;

import java.net.InetAddress;

public class Host {

	private String hostname;
	private InetAddress fixedAddress;
	private String hardwareEthernet;
	private InetAddress router;
	private InetAddress[] dns;
	private String comentario;
	
	public Host() {
		this.hostname = null;
		this.fixedAddress = null;
		this.hardwareEthernet = "";
		this.router = null;
		this.dns = new InetAddress[2];
	}
	
	public Host(String hostname, InetAddress fixedAddress, String hardwareEthernet, InetAddress router,
			InetAddress[] dns) {
	
		this.hostname = hostname;
		this.fixedAddress = fixedAddress;
		this.hardwareEthernet = hardwareEthernet;
		this.router = router;
		this.dns = dns;
		this.comentario = "";
	}

	public Host(String hostname, InetAddress fixedAddress, String hardwareEthernet, InetAddress router,
			InetAddress[] dns, String comentario) {

		this.hostname = hostname;
		this.fixedAddress = fixedAddress;
		this.hardwareEthernet = hardwareEthernet;
		this.router = router;
		this.dns = dns;
		this.comentario = comentario;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public InetAddress getFixedAddress() {
		return fixedAddress;
	}

	public void setFixedAddress(InetAddress fixedAddress) {
		this.fixedAddress = fixedAddress;
	}

	public String getHardwareEthernet() {
		return hardwareEthernet;
	}

	public void setHardwareEthernet(String hardwareEthernet) {
		this.hardwareEthernet = hardwareEthernet;
	}

	public InetAddress getRouter() {
		return router;
	}

	public void setRouter(InetAddress router) {
		this.router = router;
	}

	public InetAddress[] getDns() {
		return dns;
	}

	public void setDns(InetAddress[] dns) {
		this.dns = dns;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Host) {
			return fixedAddress.getHostAddress().equals(((Host) o).getFixedAddress().getHostAddress());
		}
		return false;
	}
	
	public String stringGuardarHost() {
		String aux="";
		System.out.println(aux);
		aux += "# "+hostname.toLowerCase()+" ==>   ( )\n"
				+"host "+hostname+" {\n"
				+"fixed-address "+fixedAddress.getHostAddress()+";\n"
				+"hardware ethernet "+hardwareEthernet+";\n"
				+"option routers "+router.getHostAddress()+";\n"
				+"option domain-name-servers "+ ((dns[0]!=null)?dns[0].getHostAddress():"")+", "+((dns[1]!=null)?dns[1].getHostAddress():"")+";\n"
				+"# comentario "+ comentario+"\n"
				+"}\n";
		return aux;
	}
	
	
	
	
	
	
}
