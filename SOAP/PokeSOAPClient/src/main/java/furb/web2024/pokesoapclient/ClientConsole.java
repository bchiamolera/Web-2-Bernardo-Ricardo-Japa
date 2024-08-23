package furb.web2024.pokesoapclient;

import java.util.Scanner;

public class ClientConsole {
    
    private TeamServer serverMethods;
    private CSVReader csvReader;
    private Scanner sc;
    
    
    public ClientConsole(TeamServer serverMethods) {
        this.serverMethods = serverMethods;
        csvReader = new CSVReader("/util/pokemons.csv");
        sc = new Scanner(System.in);
    }
    
    public void menu() {
        System.out.println("BEM-VINDO AO POKESOAP!");
        System.out.println("1. Ver time");
        System.out.println("2. Criar time");
        System.out.println("3. Alterar time");
        System.out.println("4. Deletar time");
        System.out.println("0. Sair");
        menuResponse();
    }
    
    public void menuResponse() {
        int op;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Opcao invalida!\n");
                sc.next();
            }
            op = sc.nextInt();
            if (op < 0 || op > 4) {
                System.out.println("Opcao invalida!\n");
            }
        } while(op < 0 || op > 4);
        
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
        System.out.println("Funcao nao implementada.\n");
        menu();
    }
    
    public void CreateTeam() {
        System.out.println("Funcao nao implementada.\n");
        menu();
    }
    
    public void ChangeTeam() {
        System.out.println("Funcao nao implementada.\n");
        menu();
    }
    
    public void DeleteTeam() {
        System.out.println("Funcao nao implementada.\n");
        menu();
    }
}
