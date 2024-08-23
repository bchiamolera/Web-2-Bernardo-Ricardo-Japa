package furb.web2024.pokesoapclient;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TeamServer {
    public String getTeam();
    
    public String createTeam(String trainerName, Pokemon[] pokemons);
    
    public String updateTeam(Pokemon[] pokemons);
    
    public String deleteTeam();
}
