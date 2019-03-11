package common;

import java.net.InetAddress;

import org.apache.commons.net.util.SubnetUtils;

public class Red {
	private InetAddress subnet;
	private String netmask;
	private InetAddress[] domainNameServers;
	private InetAddress router;
	private InetAddress ntpServer;
	private InetAddress netbiosNameServers;
	private InetAddress[] range;
	private int defaultLeaseTime;
	private int maxLeaseTime;
	private boolean pool;
	private String descripcion;
	private Lista<Host> ListaHosts;
	

	public Red() {
		this.subnet = null;
		this.netmask = "";
		this.domainNameServers = new InetAddress[2];
		this.router = null;
		this.ntpServer = null;
		this.netbiosNameServers = null;
		this.range = new InetAddress[2];
		this.defaultLeaseTime = 0;
		this.maxLeaseTime = 0;
		this.pool = false;
		ListaHosts = new Lista<Host>();
	}
	
	public Red(InetAddress subnet, String netmask, InetAddress[] domainNameServers, InetAddress router,
			InetAddress ntpServer, InetAddress netbiosNameServers, InetAddress[] range, int defaultLeaseTime,
			int maxLeaseTime, boolean pool) {
		this.subnet = subnet;
		this.netmask = netmask;
		this.domainNameServers = domainNameServers;
		this.router = router;
		this.ntpServer = ntpServer;
		this.netbiosNameServers = netbiosNameServers;
		this.range = range;
		this.defaultLeaseTime = defaultLeaseTime;
		this.maxLeaseTime = maxLeaseTime;
		this.pool = pool;
		ListaHosts = new Lista<Host>();
		this.descripcion="";
	}
	
	public Red(InetAddress subnet, String netmask, InetAddress[] domainNameServers, InetAddress router,
			InetAddress ntpServer, InetAddress netbiosNameServers, InetAddress[] range, int defaultLeaseTime,
			int maxLeaseTime, boolean pool,String descripcion) {
		this.subnet = subnet;
		this.netmask = netmask;
		this.domainNameServers = domainNameServers;
		this.router = router;
		this.ntpServer = ntpServer;
		this.netbiosNameServers = netbiosNameServers;
		this.range = range;
		this.defaultLeaseTime = defaultLeaseTime;
		this.maxLeaseTime = maxLeaseTime;
		this.pool = pool;
		this.descripcion=descripcion;
		ListaHosts = new Lista<Host>();
	}

	public InetAddress getSubnet() {
		return subnet;
	}

	public void setSubnet(InetAddress subnet) {
		this.subnet = subnet;
	}

	public String getNetmask() {
		return netmask;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public InetAddress[] getDomainNameServers() {
		return domainNameServers;
	}

	public void setDomainNameServers(InetAddress[] domainNameServers) {
		this.domainNameServers = domainNameServers;
	}

	public InetAddress getRouter() {
		return router;
	}

	public void setRouter(InetAddress router) {
		this.router = router;
	}

	public InetAddress getNtpServer() {
		return ntpServer;
	}

	public void setNtpServer(InetAddress ntpServer) {
		this.ntpServer = ntpServer;
	}

	public InetAddress getNetbiosNameServers() {
		return netbiosNameServers;
	}

	public void setNetbiosNameServers(InetAddress netbiosNameServers) {
		this.netbiosNameServers = netbiosNameServers;
	}

	public InetAddress[] getRange() {
		return range;
	}

	public void setRange(InetAddress[] range) {
		this.range = range;
	}

	public int getDefaultLeaseTime() {
		return defaultLeaseTime;
	}

	public void setDefaultLeaseTime(int defaultLeaseTime) {
		this.defaultLeaseTime = defaultLeaseTime;
	}

	public int getMaxLeaseTime() {
		return maxLeaseTime;
	}

	public void setMaxLeaseTime(int maxLeaseTime) {
		this.maxLeaseTime = maxLeaseTime;
	}

	public boolean getPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public Lista<Host> getListaHosts() {
		return ListaHosts;
	}

	public void setListaHosts(Lista<Host> listaHosts) {
		ListaHosts = listaHosts;
	}

	// Metodo para ver un listado de las ips libres por consola
//	public void listIps() {
//		SubnetUtils utils = new SubnetUtils(subnet.getHostAddress(), netmask);
//        String[] ips = utils.getInfo().getAllAddresses();
//        for(int i=0;i<ips.length;i++) {
//        	System.out.println(ips[i]);
//        }
//	}
	
	public String[] listIps() {
		SubnetUtils utils = new SubnetUtils(subnet.getHostAddress(), netmask);
        String[] ips = utils.getInfo().getAllAddresses();
        return ips;
	}
	
    public boolean isIpInSubnet(String ip) {
        SubnetUtils utils = new SubnetUtils(subnet.getHostAddress(), netmask);      
        return utils.getInfo().isInRange(ip);
    }
    
    public boolean insertarHost(Host h) {
		  	
    	return this.ListaHosts.insertar(h);
    }
    
    @Override
    public String toString() {
    	if (descripcion=="") {
    		return subnet.getHostAddress();
    	} else {
    		return descripcion;
    	}
    }
    @Override
	public boolean equals(Object o) {
		if (o instanceof Host) {
			return subnet.getHostAddress().equals(((Red) o).getSubnet().getHostAddress());
		}
		return false;
	}
	
    public String stringGuardarRed() {
    	String aux="";
    	aux+="# "+descripcion+"\n"
    			+"subnet "+subnet.getHostAddress()+" netmask "+netmask+ " {\n"
    			+" option domain-name-servers "+domainNameServers[0].getHostAddress()+", "+domainNameServers[1].getHostAddress()+";\n"
    			+" option routers "+router.getHostAddress()+";\n"
    			+" option ntp-servers "+ntpServer.getHostAddress()+";\n"
    			+" option netbios-name-servers "+netbiosNameServers.getHostAddress()+";\n"
    			+((!pool)?"# range "+range[0].getHostAddress()+" "+range[1].getHostAddress()+";\n":
    				" range "+range[0].getHostAddress()+" "+range[1].getHostAddress()+";\n")
    			+" default-lease-time "+String.valueOf(defaultLeaseTime)+";\n"
    			+" max-lease-time "+String.valueOf(maxLeaseTime)+";\n"
    			+"}\n";
    	return aux;
    }
    public boolean existeHost(InetAddress ip) {
		boolean existe=false;
		Nodo<Host> aux = ListaHosts.getCabeza();
	while(aux!=null && !existe) {
		if (aux.getInfo().getFixedAddress().getHostName().compareTo(ip.getHostAddress())==0) {
			existe = true;
		} else;
		aux = aux.getSiguiente();
	}
	return existe;
}

}
