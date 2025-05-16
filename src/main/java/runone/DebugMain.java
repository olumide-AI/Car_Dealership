package runone;

public class DebugMain {
    public static void main(String[] args) {

        //Debug vehicle logic
        Vehicle vehicle1 = new Vehicle("1",1997,"toyota","camry","sedan","red",100000,2000.00);
        System.out.println(vehicle1);

        //Add second  vehicle
        Vehicle vehicle2 = new Vehicle("123", 2020, "Honda", "Civic", "Sedan", "Blue", 15000, 20000.00);

        //Debug Dealership class logic
        Dealership dealership1 = new Dealership("Olu Cars", "1230 10th, ST, Oakland, CA", "223-345-2343");
        dealership1.addVehicle(vehicle1);
        dealership1.addVehicle(vehicle2);
        System.out.println(dealership1.getAllVehicles());

        //Debug get dealership method
        DealershipFileManager inventory = new DealershipFileManager();
        Dealership dealership = inventory.getDealership("inventory.csv");

        // Check Dealership Info
        System.out.println("Dealership Name: " + dealership.getName());
        System.out.println("Address: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());

        // Check Vehicles
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            System.out.println(vehicle);
        }

    }
}
