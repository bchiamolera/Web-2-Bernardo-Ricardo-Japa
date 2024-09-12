package furb.web2024.pokesoap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface TeamServer {    
     
    
    //Servidor é o mesmo que um metodo mas para o cliente
    @WebMethod String getTeam();
    
    @WebMethod String createTeam(String trainerName, int[] pokemons);
    
    @WebMethod String updateTeam(int[] pokemons);
    
    @WebMethod String deleteTeam();

}
