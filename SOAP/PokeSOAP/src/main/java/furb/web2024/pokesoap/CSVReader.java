package furb.web2024.pokesoap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CSVReader {
    File fileCSV;
    
    public CSVReader(String path) {
        fileCSV = new File(path);
    }
    
    public HashMap<Integer, Pokemon> GetPokemons() {
        HashMap<Integer,Pokemon> pokemons = new HashMap();
        
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileCSV));
            
            br.readLine();
            
            line = br.readLine();
            while ((line) != null) {
                List<String> csvLine = Arrays.asList(line.split(","));
                
                int number = Integer.parseInt(csvLine.get(0));
                String name = csvLine.get(1);
                String type1 = csvLine.get(2);
                String type2 = null;
                if (csvLine.size() == 4) {
                    type2 = csvLine.get(3);
                }
                
                Pokemon pokemon = new Pokemon(number, name, type1, type2);
                pokemons.put(number, pokemon);
                
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error when reading lines:\n" + e);
            return null;
        }
        
        return pokemons;
    }
}
