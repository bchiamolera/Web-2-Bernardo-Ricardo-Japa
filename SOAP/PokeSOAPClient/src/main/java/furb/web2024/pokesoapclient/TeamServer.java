package furb.web2024.pokesoapclient;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TeamServer {
    public String getTeam();
    
    public String createTeam(String trainerName, int[] pokemons);
    
    public String updateTeam(int[] pokemons);
    
    public String deleteTeam();
}
