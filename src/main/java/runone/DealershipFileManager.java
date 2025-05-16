package runone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    private static final String FILE_PATH = "inventory.csv";

    public static Dealership getDealership (String filename){
        //This initalization helps a lot with clean code
        Dealership dealership = null; //Declare dealership now to return later
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            line = bufferedReader.readLine(); //Read first line
            if (line != null){
                String[] dealershipHeader = line.split("\\|");
                String name = dealershipHeader[0];
                String address = dealershipHeader[1];
                String phone = dealershipHeader[2];

                dealership = new Dealership(name, address, phone + "\n");
            }

            while ((line = bufferedReader.readLine()) != null){
                String[] splitEntries = line.split("\\|");
                int vin = Integer.parseInt(splitEntries[0]);
                int year = Integer.parseInt(splitEntries[1]);
                String make = splitEntries[2];
                String model = splitEntries[3];
                String vehicleType = splitEntries[4];
                String color = splitEntries[5];
                int odometer = Integer.parseInt(splitEntries[6]);
                double price = Double.parseDouble(splitEntries[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer,price);
                dealership.getInventory().add(vehicle);
                //dealership.addVehicle(vehicle);
            }
        }
        catch (IOException e){
            System.out.println("File Reading transaction error:  " + e.getMessage());
        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership, String filename) {
        File file = new File(filename);
        boolean fileExists = file.exists();

        try (FileWriter fileWriter = new FileWriter(file, true)) { // Always append

            // Step 1: Only write dealership header if the file is new
            if (!fileExists) {
                String name = dealership.getName();
                String address = dealership.getAddress();
                String phone = dealership.getPhone();
                fileWriter.write(name + "|" + address + "|" + phone + "\n");
            }

            // Step 2: Always write the vehicle data (appending)
            List<Vehicle> vehicleList = dealership.getAllVehicles();
            for (Vehicle vehicle : vehicleList) {
                fileWriter.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "\n");
            }
        } catch (IOException e) {
            System.out.println("File writing error: Dealership not saved. " + e.getMessage());
        }
    }

}
