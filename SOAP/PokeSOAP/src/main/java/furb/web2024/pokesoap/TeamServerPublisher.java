package furb.web2024.pokesoap;


import javax.xml.ws.Endpoint;

public class TeamServerPublisher {
    public static void main(String[] args)
  {
    Endpoint.publish("http://127.0.0.1:9876/pokeSOAP", new TeamServerImpl());
  }
}
