package furb.web2024.pokesoap;

public class Team {
    private String trainer;
    private Pokemon[] pokemons;
    
    public Team(String trainerName, Pokemon[] pokemons) {
        this.trainer = trainerName;
        this.pokemons = pokemons;
    }
    
    public void UpdateTeam(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }
    
    @Override
    public String toString() {
        String str = "Treinador: " + trainer + "\n";
        
        for (int i = 0; i < pokemons.length; i++) {
            str += "Slot " + (i + 1) + ": " + pokemons[i].toString() + "\n";
        }

        return str + "\n";
    }
}
