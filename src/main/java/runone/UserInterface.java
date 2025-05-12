package runone;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //Fields
    //Holds the current dealership the user is working with.
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    //Methods
    public void display(){
        init();//Call the init method to load dealership
        boolean running = true;
        while (running){
            displayMenu();//call the display menu method to show options to the user

            System.out.println("Enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());

            // Process the user selection. If they choose to exit, stop the loop.
            running = processUserSelection(choice);
        }

    }
    //Load the dealership
    //Ask user for file name
    private void init(){
        System.out.println("Enter inventory filename");
        String filename = scanner.nextLine();
        dealership = DealershipFileManager.getDealership(filename);
        if(dealership == null){
            System.out.println("Failed to load dealership data");
            System.exit(0);
        }

    }
    private void displayMenu(){
        System.out.println("\n--- Dealership Management ---");
        System.out.println("1. List All Vehicles");
        System.out.println("2. Search Vehicles by Make/Model");
        System.out.println("3. Add a Vehicle");
        System.out.println("4. Remove a Vehicle");
        System.out.println("5. Save and Exit");
    }
    private boolean processUserSelection(int choice){
        switch (choice) {
            case 1:
                listAllVehicles();
                break;
            case 2:
                searchVehicles();
                break;
            case 3:
                addVehicle();
                break;
            case 4:
                removeVehicle();
                break;
            case 5:
                saveAndExit();
                return false;  // Exit the loop and stop the program
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true;  // Keep looping
    }

    //Helper Methods
    private void listAllVehicles(){
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        for (Vehicle vehicle: vehicleList){
            System.out.println(vehicle); //Calls toString() on each Vehicle
        }
    }

    private void searchVehicles (){
        System.out.println("Enter make or model to search");
        String query = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMakeModel(query);
        for (Vehicle vehicle: results){
            System.out.println(vehicle);
        }
    }

    private void addVehicle(){
        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter Odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);

        System.out.println("Vehicle added successfully.");
    }

    private void removeVehicle() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        boolean removed = dealership.removeVehicleByVin(vin);
        if (removed) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void saveAndExit() {
        DealershipFileManager.saveDealership(dealership, "inventory.csv");
        System.out.println("Dealership saved. Exiting program.");
    }
}
