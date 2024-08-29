package furb.web2024.pokesoapclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ClientConsole {
    
    private TeamServer serverMethods;
    private Scanner sc;
    private CSVReader csvReader;
    private HashMap<Integer, Pokemon> pokemons;
    
    public ClientConsole(TeamServer serverMethods) {
        this.serverMethods = serverMethods;
        sc = new Scanner(System.in);
        csvReader = new CSVReader("src/util/pokemons.csv");
    }
    
    public void menu() {
        pokemons = csvReader.GetPokemons();
        
        System.out.println("BEM-VINDO AO POKESOAP!");
        System.out.println("1. Ver time");
        System.out.println("2. Criar time");
        System.out.println("3. Alterar time");
        System.out.println("4. Deletar time");
        System.out.println("0. Sair");
        menuResponse();
    }
    
    public void menuResponse() {
        int op = readIntValue(0, 4);
        
        switch(op) {
            case 1:
                ShowTeam();
                break;
            case 2:
                CreateTeam();
                break;
            case 3:
                ChangeTeam();
                break;
            case 4:
                DeleteTeam();
                break;
            default:
                sc.close();
                break;
        }
    }
    
    public void ShowTeam() {
        System.out.println("\n" + serverMethods.getTeam() + "\n");
        
        menu();
    }
    
    public void CreateTeam() {
        if (!serverMethods.getTeam().equals("Nenhum time cadastrado")) {
            System.out.println("Time já cadastrado\n");
            menu();
            return;
        }
        
        String opString;
        int opInt;
        
        String trainerName;
        Pokemon pokemon;
        List<Pokemon> team = new ArrayList();
        
        System.out.println("\nQual o seu nome de treinador?");
        do {
            opString = sc.next();
            if (opString == null) {
                System.out.println("Digite um nome válido");
            }
        } while(opString == null);
        trainerName = opString.trim();
        
        pokemon = choosePokemon();
        team.add(pokemon);
        System.out.print(pokemon.name + " adicionado ao time!");
        while (team.size() < 6) {
            System.out.println(" Deseja adicionar mais um?");
            System.out.println("1. Sim\t\t2. Nao");
            opInt = readIntValue(1, 2);
            if (opInt == 2) {
                break;
            }
            pokemon = choosePokemon();
            team.add(pokemon);
            System.out.print(pokemon.name + " adicionado ao time!");
        }
        System.out.println("\n");
        
        int[] finalTeam = new int[team.size()];
        for (int i = 0; i < team.size(); i++) {
            finalTeam[i] = team.get(i).number;
        }

        System.out.println(serverMethods.createTeam(trainerName, finalTeam));
        
        menu();
    }
    
    public void ChangeTeam() {        
        if (serverMethods.getTeam().equals("Nenhum time cadastrado")) {
            System.out.println("\nNenhum time cadastrado\n");
            menu();
            return;
        }
        
        int opInt;
        
        Pokemon pokemon;
        List<Pokemon> team = new ArrayList();
        
        pokemon = choosePokemon();
        team.add(pokemon);
        System.out.print(pokemon.name + " adicionado ao time!");
        while (team.size() < 6) {
            System.out.println(" Deseja adicionar mais um?");
            System.out.println("1. Sim\t\t2. Nao");
            opInt = readIntValue(1, 2);
            if (opInt == 2) {
                break;
            }
            pokemon = choosePokemon();
            team.add(pokemon);
            System.out.print(pokemon.name + " adicionado ao time!");
        }
        System.out.println("\n");
        
        int[] finalTeam = new int[team.size()];
        for (int i = 0; i < team.size(); i++) {
            finalTeam[i] = team.get(i).number;
        }
        
        String res = serverMethods.updateTeam(finalTeam);
        System.out.println(res);
        menu();
    }
    
    public void DeleteTeam() {
        System.out.println("\n" + serverMethods.deleteTeam() + "\n");

        menu();
    }
    
    public Pokemon choosePokemon() {
        System.out.println("\nQual pokemon deseja adcionar? Digite o numero correspondente (1 - 151)");
        int number = readIntValue(1, 151);
        
        return pokemons.get(number);
    }
    
    public int readIntValue(int min, int max) {
        int opInt;
        
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Opcao invalida!");
                sc.next();
            }
            opInt = sc.nextInt();
            if (opInt < min || opInt > max) {
                System.out.println("Opcao invalida!");
            }
        } while(opInt < min || opInt > max);
        
        return opInt;
    }
}
