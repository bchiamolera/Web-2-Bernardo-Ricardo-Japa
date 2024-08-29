package furb.web2024.pokesoapclient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class PokeSOAPClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://127.0.0.1:9876/pokeSOAP?wsdl");
        
        QName qname = new QName("http://pokesoap.web2024.furb/","TeamServerImplService");
        Service ws = Service.create(url, qname);
        
        QName qport = new QName("http://pokesoap.web2024.furb/", "TeamServerImplPort");
        TeamServer ts = ws.getPort(qport, TeamServer.class);
        
        ClientConsole console = new ClientConsole(ts);
        console.menu();
    }
}
