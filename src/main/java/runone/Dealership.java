package runone;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    //Fields
    private String name;
    private String address;
    private  String phone;

    private List<Vehicle> inventory;

    //Constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //Getters


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    //Methods
    public void addVehicle (Vehicle vehicle){
        inventory.add(vehicle);
    }

    public List<Vehicle> getAllVehicles(){
         return new ArrayList<>(this.inventory);//Safe: changes affect the copy, not the original
    }

    public List<Vehicle> getVehiclesByMakeModel(String query) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(query) || vehicle.getModel().equalsIgnoreCase(query)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public boolean removeVehicleByVin(int vin) {
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getVin() == vin){
                inventory.remove(i);
                return true;  // Successfully removed
            }
        }
        return false;  // Vehicle not found
    }

    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(vehicle);
            }
        }
        return results;
    }






}

