package yeshede.server;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class YeshedeServer {
	public static void main(String[] args) throws Exception {
		Component component = new Component();
		component.getClients().add(Protocol.CLAP);
		component.getServers().add(Protocol.HTTP, 8182);
		component.getDefaultHost().attach("/" + "yeshede", new YeshedeServerApplication());
		component.start();
	}
}
