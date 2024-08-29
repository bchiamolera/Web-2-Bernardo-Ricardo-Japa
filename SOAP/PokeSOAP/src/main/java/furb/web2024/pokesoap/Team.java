package furb.web2024.pokesoap;

import java.util.HashMap;

public class Team {
    private CSVReader csvReader;
    private HashMap<Integer, Pokemon> pokemons;
    private String trainer;
    private Pokemon[] team;
    
    public Team(String trainerName, int[] pokemonNumbers) {
        this.csvReader = new CSVReader("src/util/pokemons.csv");
        this.trainer = trainerName;
        this.pokemons = csvReader.GetPokemons();
        
        setTeam(pokemonNumbers);
    }
    
    public void setTeam(int[] pokemonNumbers) {
        this.team = new Pokemon[pokemonNumbers.length];
        for (int i = 0; i < pokemonNumbers.length; i ++) {
            Pokemon pokemon = pokemons.get(pokemonNumbers[i]);
            team[i] = pokemon;
        }
    }
    
    @Override
    public String toString() {
        String str = "Treinador: " + trainer + "\n";
        
        for (int i = 0; i < team.length; i++) {
            str += "Slot " + (i + 1) + ": " + team[i].toString() + "\n";
        }

        return str + "\n";
    }
}
