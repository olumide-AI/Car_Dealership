package controller;

import logic.LeaseContract;
import logic.SalesContract;
import model.Contract;
import model.Dealership;
import model.Vehicle;
import runone.ContractDataManager;
import runone.DealershipFileManager;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //Fields
    //Holds the current dealership the user is working with.
    private Dealership dealership;
    private String filename;
    private static Scanner scanner = new Scanner(System.in);

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
        filename = scanner.nextLine();
        this.dealership = DealershipFileManager.getDealership(filename);
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
        System.out.println("5. Search by Price Range");
        System.out.println("6. Search by Year Range");
        System.out.println("7. Search by Color");
        System.out.println("8. Search by Mileage Range");
        System.out.println("9. Search by Vehicle Type");
        System.out.println("10. Sell or Finance to lease a vehicle");
        System.out.println("11. View all Contracts");
        System.out.println("0. Save and Exit");
    }
    private boolean processUserSelection(int choice){
        switch (choice) {
            case 1:
                listAllVehicles();
                break;
            case 2:
                processGetByMakeModelRequest ();
                break;
            case 3:
                addVehicle();
                break;
            case 4:
                removeVehicle();
                break;
            case 5:
                processGetByPriceRequest();
                break;
            case 6:
                processGetByYearRequest();
                break;
            case 7:
                processGetByColorRequest ();
                break;
            case 8:
                processGetByMileageRequest();
                break;
            case 9:
                processGetByVehicleTypeRequest ();
                break;
            case 10:
                sellOrLeaseVehicle();
                break;
            case 11:
                displayAllContracts();
                break;
            case 0:
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

    private void processGetByMakeModelRequest (){
        System.out.println("Enter make or model to search");
        String query = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMakeModel(query);
        for (Vehicle vehicle: results){
            System.out.println(vehicle);
        }
    }

    private void addVehicle(){
        System.out.print("Enter VIN: ");
        String vin = (scanner.nextLine());

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
        this.dealership.addVehicle(newVehicle);

        DealershipFileManager.saveDealership(dealership, filename);

        System.out.println("Vehicle added successfully.");
    }

    private void removeVehicle() {
        System.out.print("Enter VIN of vehicle to remove: ");
        String vin = scanner.nextLine();

        boolean removed = dealership.removeVehicleByVin(vin);
        if (removed) {
            DealershipFileManager.saveDealership(dealership, filename);

            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void processGetByPriceRequest() {
        try{
            System.out.println("What is your min car price");
            double minPrice =  Double.parseDouble(scanner.nextLine());
            System.out.println("What is your max car price ");
            double maxPrice = Double.parseDouble(scanner.nextLine());
            if (minPrice > maxPrice){
                System.out.println("Minimum price can't be greater than maximum value");
                System.exit(0);
            }
            List<Vehicle> results = dealership.getVehiclesByPriceRange(minPrice, maxPrice);
            if(results.isEmpty()){
                System.out.println("No vehicles found within that price range");
            }
            else {
                System.out.println("Vehciles found below");
                for(Vehicle vehicle: results){
                    System.out.println(vehicle);
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Invalid input. Please enter a number ");
        }

    }

    private void processGetByYearRequest() {
        try {
            System.out.print("Enter minimum year: ");
            int minYear = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter maximum year: ");
            int maxYear = Integer.parseInt(scanner.nextLine());

            if (minYear > maxYear) {
                System.out.println("Minimum year cannot be greater than maximum year.");
                return;
            }

            List<Vehicle> results = dealership.getVehiclesByYearRange(minYear, maxYear);

            if (results.isEmpty()) {
                System.out.println("No vehicles found within the specified year range.");
            } else {
                System.out.println("Vehicles found:");
                for (Vehicle v : results) {
                    System.out.println(v);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numeric years.");
        }
    }

    private void processGetByColorRequest (){
        System.out.println("Please enter color to search");
        String color = scanner.nextLine();
        List<Vehicle> results = dealership.getVehiclesByColor(color);
        if(results.isEmpty()){
            System.out.println("No vehicles has been found with this " + color + " request");
        }
        else{
            System.out.println("Vehicles found with that color includes:");
            for(Vehicle vehicle: results){
                System.out.println(vehicle);
            }
        }
    }

    private void processGetByMileageRequest() {
        try {
            System.out.print("Enter minimum mileage: ");
            int minMileage = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter maximum mileage: ");
            int maxMileage = Integer.parseInt(scanner.nextLine());

            if (minMileage > maxMileage) {
                System.out.println("Minimum mileage cannot be greater than maximum mileage.");
                return;
            }

            List<Vehicle> results = dealership.getVehiclesByMileageRange(minMileage, maxMileage);

            if (results.isEmpty()) {
                System.out.println("No vehicles found within the specified mileage range.");
            } else {
                System.out.println("Vehicles found:");
                for (Vehicle v : results) {
                    System.out.println(v);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for mileage.");
        }
    }

    private void processGetByVehicleTypeRequest (){
        System.out.println("Please enter vehicle type to search");
        String vehicleType = scanner.nextLine();
        List<Vehicle> results = dealership.getVehiclesByVehicleType(vehicleType);
        if(results.isEmpty()){
            System.out.println("No vehicles has been found with this " + vehicleType + " request");
        }
        else{
            System.out.println("Vehicles found with that vehicleType includes:");
            for(Vehicle vehicle: results){
                System.out.println(vehicle);
            }
        }
    }

    private void saveAndExit() {
        DealershipFileManager.saveDealership(dealership, filename);
        System.out.println("Dealership saved. Exiting program.");
        System.out.println("Dealership saved to " + filename + ". Exiting application");
    }

    private void sellOrLeaseVehicle(){

        System.out.println("What is the vin of the vehicle you want to sell or lease ");
        String userVin = scanner.nextLine();
        Vehicle userVehicleChoice = null;
        for (Vehicle vehicle: dealership.getInventory()) {
            if (vehicle.getVin().equalsIgnoreCase(userVin)) {
                userVehicleChoice = vehicle;
                break;
            }
        }
            if(userVehicleChoice == null){
                System.out.println("Vehicle with " + userVin + "not found. ");
                return;
            }

        // Get customer info
        System.out.print("Please enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Please enter your email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter today's date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Ask for contract type
        System.out.print("Is this a SALE or LEASE? ");
        String contractType = scanner.nextLine().trim().toUpperCase();

        //Decide which contract to create
        Contract contract = null;
        if (contractType.equalsIgnoreCase("SALE")){
             contract = new SalesContract(date, customerName,customerEmail, userVehicleChoice,dealership.getAllVehicles(),0);
        } else if (contractType.equalsIgnoreCase("LEASE")) {
             contract = new LeaseContract(date, customerName, customerEmail, userVehicleChoice, dealership.getAllVehicles());
        }
        else{
            System.out.println("Invalid contract type");
            return;
        }
        // Save contract and remove vehicle
        ContractDataManager.saveContract(contract);
        dealership.removeVehicleByVin(userVehicleChoice.getVin());

        System.out.println("Contract processed and vehicle removed from inventory.");
    }
    private void displayAllContracts() {
        List<Contract> contracts = ContractDataManager.loadAllContracts();

        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }

        for (Contract contract : contracts) {
            System.out.println("---- Contract ----");
            System.out.println("Date: " + contract.getDate());
            System.out.println("Customer: " + contract.getCustomerName());
            System.out.println("Email: " + contract.getCustomerEmail());
            System.out.println("Vehicle: " + contract.getVehicleSold());
            System.out.println("Total Price: $" + String.format("%.2f", contract.getTotalPrice()));
            System.out.println("Monthly Payment: $" + String.format("%.2f", contract.getMonthlyPayment()));
            System.out.println();
        }
    }

}
