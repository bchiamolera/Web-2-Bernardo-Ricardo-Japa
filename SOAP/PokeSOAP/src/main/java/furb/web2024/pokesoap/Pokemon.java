/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package furb.web2024.pokesoap;

/**
 *
 * @author Bernardo Chiamolera
 */
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
        return name;
    }
}
