package runone;

public class DebugMain {
    public static void main(String[] args) {

        //Debug vehicle logic
        Vehicle vehicle1 = new Vehicle(1,1997,"toyota","camry","sedan","red",100000,2000.00);
        System.out.println(vehicle1);

        //Add second  vehicle
        Vehicle vehicle2 = new Vehicle(123, 2020, "Honda", "Civic", "Sedan", "Blue", 15000, 20000.00);

        //Debug Dealership class logic
        Dealership dealership1 = new Dealership("Olu Cars", "1230 10th, ST, Oakland, CA", "223-345-2343");
        dealership1.addVehicle(vehicle1);
        dealership1.addVehicle(vehicle2);
        System.out.println(dealership1.getAllVehicles());
    }
}
