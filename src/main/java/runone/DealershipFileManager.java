package runone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

                dealership = new Dealership(name, address, phone);
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
                dealership.addVehicle(vehicle);
            }
        }
        catch (IOException e){
            System.out.println("File Reading transaction error:  " + e.getMessage());
        }
        return dealership;
    }

//    public Dealership saveDealership(Dealership dealership, String filename){
//        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)){
//
//        }
//    }
}
