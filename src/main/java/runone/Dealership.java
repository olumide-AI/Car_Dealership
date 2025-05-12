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
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                inventory.remove(vehicle);
                return true;  // Successfully removed
            }
        }
        return false;  // Vehicle not found
    }


}

