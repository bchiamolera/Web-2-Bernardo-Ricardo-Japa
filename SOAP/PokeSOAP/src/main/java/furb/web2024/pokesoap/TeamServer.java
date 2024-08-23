package furb.web2024.pokesoap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface TeamServer {    
        
    @WebMethod String getTeam();
    
    @WebMethod String createTeam(String trainerName, Pokemon[] pokemons);
    
    @WebMethod String updateTeam(Pokemon[] pokemons);
    
    @WebMethod String deleteTeam();

}
