package runone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {
    private static final String FILE_PATH = "contracts.csv";
    //Writing a file using save contract
    public static void saveContract(Contract contract){
        try(FileWriter fileWriter = new FileWriter(FILE_PATH, true)){
            fileWriter.write(contract.toFileString()+"\n");
        }
        catch (IOException e){
            System.out.println("File writing error " + e.getMessage());
        }
    }

    public static List<Contract> loadAllContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0];

                String date = parts[1];
                String name = parts[2];
                String email = parts[3];

                String vin = parts[4];
                int year = Integer.parseInt(parts[5]);
                String make = parts[6];
                String model = parts[7];
                String vehicleType = parts[8];
                String color = parts[9];
                int odometer = Integer.parseInt(parts[10]);
                double price = Double.parseDouble(parts[11]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                List<Vehicle> vehicleList = new ArrayList<>();
                vehicleList.add(vehicle);  // create single-item list for constructor

                if (type.equals("SALE")) {
                    // Can ignore fields 12-17 because we recalculate them
                    Contract sale = new SalesContract(date, name, email, vehicle, vehicleList, 0);
                    contracts.add(sale);
                } else if (type.equals("LEASE")) {
                    // Can ignore fields 12-15 because we recalculate them
                    Contract lease = new LeaseContract(date, name, email, vehicle, vehicleList);
                    contracts.add(lease);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading contracts file: " + e.getMessage());
        }

        return contracts;
    }


    private void displayAllContracts() {
        List<Contract> contracts = ContractDataManager.loadAllContracts();

        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }

        for (Contract contract : contracts) {
            System.out.println("\n===============================");
            System.out.println("Contract Type: " + (contract instanceof SalesContract ? "SALE" : "LEASE"));
            System.out.println("Date: " + contract.getDate());
            System.out.println("Customer: " + contract.getCustomerName());
            System.out.println("Email: " + contract.getCustomerEmail());
            System.out.println("Vehicle Details:");
            System.out.println(contract.getVehicleSold().toString());

            System.out.println("\n Financial Summary:");
            System.out.printf("Total Price: $%.2f\n", contract.getTotalPrice());
            System.out.printf("Monthly Payment: $%.2f\n", contract.getMonthlyPayment());

            if (contract instanceof SalesContract sale) {
                System.out.println("Sales Tax: $" + String.format("%.2f", sale.calculateSalesTax()));
                System.out.println("Recording Fee: $100.00");
                System.out.println("Processing Fee: $" + String.format("%.2f", sale.processingFee()));
                System.out.println("Financed: YES");
            }

            if (contract instanceof LeaseContract lease) {
                System.out.println("Expected End Value: $" + String.format("%.2f", lease.getExpectedEndingValue()));
                System.out.println("Lease Fee: $" + String.format("%.2f", lease.getLeaseFee()));
                System.out.println("Term: 36 months @ 4.0% interest");
            }

            System.out.println("===============================");
        }
    }


}
