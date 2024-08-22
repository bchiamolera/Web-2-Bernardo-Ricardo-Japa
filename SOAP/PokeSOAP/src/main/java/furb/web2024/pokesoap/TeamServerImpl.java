package furb.web2024.pokesoap;

import java.util.Date;
import javax.jws.WebService;

@WebService(endpointInterface = "furb.web2024.pokesoap.TeamServer")
public class TeamServerImpl implements TeamServer {
    
    Team time;
    
    public String getTeam() {
        if (time != null) {
            return time.toString();
        }
        return "Nenhum time cadastrado";
    }

    public String updateTeam(Pokemon[] pkmns) {
        if (time != null) {
            time.UpdateTeam(pkmns);
            return "Time atualizado! \n" + time.toString();
        }
        return "Nenhum time cadastrado";
    }
    
    public String createTeam(String trainerName, Pokemon[] pkmns) {
        if (time == null) {
            time = new Team(trainerName, pkmns);
            return "Time criado! \n" + time.toString();
        }
        return "Treinador já cadastrado";
    }
    
    public String deleteTeam() {
        if (time != null) {
            time = null;
            return "Time deletado!";
        }
        return "Nenhum time cadastrado";
    }

}
