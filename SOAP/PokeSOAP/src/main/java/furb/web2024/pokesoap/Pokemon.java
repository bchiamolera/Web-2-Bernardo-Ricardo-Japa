package furb.web2024.pokesoap;

public class Pokemon {
    
    int number;
    String name;
    String type1;
    String type2;
    
    public Pokemon(int number, String name, String type1, String type2) {
        this.number = number;
        this.name = name;
        this.type1 = type1;
        
        if (type2 != null) {
            this.type2 = type2;
        } else {
            this.type2 = null;
        }
    }
    
    @Override
    public String toString() {
        String ret = name + ". Tipo 1: " + type1;
        if (type2 != null) {
            ret += ". Type2: " + type2 + ".";
        }
        return ret;
    }
}
