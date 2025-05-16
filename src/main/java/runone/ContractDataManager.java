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
                List<Vehicle> list = new ArrayList<>();
                list.add(vehicle);  // reconstruct list even though it's one item

                if (type.equals("SALE")) {
                    // sales contract format uses index 12-17
                    Contract sale = new SalesContract(date, name, email, vehicle, list, 0); // tax is recalculated inside
                    contracts.add(sale);
                } else if (type.equals("LEASE")) {
                    // lease contract format uses index 12-15
                    Contract lease = new LeaseContract(date, name, email, vehicle, list);
                    contracts.add(lease);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading contracts file: " + e.getMessage());
        }

        return contracts;
    }

}
