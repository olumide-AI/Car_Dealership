package runone;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    private static Scanner scanner = new Scanner(System.in);
    public void display(){
        boolean running = true;
        while (running){
            System.out.println("\n --- ADMIN PANEL ---");
            System.out.println("1. List all contracts");
            System.out.println("2. List last 10 contracts");
            System.out.println("0. Exit Admin");

            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice){
                case 1:
                    listAllContracts();
                    break;
                case 2:
                    listLastTenContracts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please pick an option between 1,2,or 0");
            }
        }
    }

    private void listAllContracts(){
        List<Contract> contracts = ContractDataManager.loadAllContracts();
        for (Contract contract: contracts){
            System.out.println(contract.toFileString());
        }
    }

    private void listLastTenContracts(){
        List<Contract> contracts = ContractDataManager.loadAllContracts();

        if (contracts.isEmpty()){
            System.out.println("No contracts found.");
            return;
        }

        int totalContractsAvailable = contracts.size();
        int startIndex = totalContractsAvailable - 10;

        if(startIndex <0){
            startIndex = 0;
        }
        System.out.println("Showing last " + (totalContractsAvailable - startIndex) + " contract(s):");

        for(int i = startIndex; i < totalContractsAvailable; i++){
            Contract contract = contracts.get(i);
            System.out.println("----------");
            System.out.println(contract.toFileString());
        }


    }
}
