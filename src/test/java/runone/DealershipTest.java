package runone;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DealershipTest {
    //Test Constructor
    @Test
    public void testDealershipConstructor(){
        //Arrange
        Dealership dealership = new Dealership("Olu Cars",  "303 2nd st, san francisco, CA", "234-456-7890");
        //Act
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        //Assert
        assertEquals("Olu Cars", dealership.getName());
        assertEquals("303 2nd st, san francisco, CA",dealership.getAddress());
        assertEquals("234-456-7890", dealership.getPhone());
        assertEquals(0,vehicleList.size());
        assertTrue(vehicleList.isEmpty());

    }
    @Test
    public void addVehicleTest (){
        //Arrange
        Dealership dealership = new Dealership("Olu Cars",  "303 2nd st, san francisco, CA", "234-456-7890");
        Vehicle vehicle1 = new Vehicle(123,1997,"Toyota", "Camry", "Sedan", "red", 155600,20000);
        Vehicle vehicle2 = new Vehicle(123, 2020, "Honda", "Civic", "Sedan", "Blue", 15000, 20000.00);
        //Act
        dealership.addVehicle(vehicle1);
        dealership.addVehicle(vehicle2);
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        //Assert
        assertEquals(2,vehicleList.size());
        assertEquals(vehicle1,vehicleList.getFirst());
        assertEquals(vehicle2,vehicleList.get(1));
    }
    @Test
    public void testgetvehicle (){
        //Arrange
        Dealership dealership = new Dealership("Olu Cars",  "303 2nd st, san francisco, CA", "234-456-7890");
        Vehicle vehicle1 = new Vehicle(123,1997,"Toyota", "Camry", "Sedan", "red", 155600,20000);
        Vehicle vehicle2 = new Vehicle(123, 2020, "Honda", "Civic", "Sedan", "Blue", 15000, 20000.00);
        //Act
        dealership.addVehicle(vehicle1);
        dealership.addVehicle(vehicle2);

        List<Vehicle> vehicleList = dealership.getAllVehicles();
        //Assert
        assertEquals(2,vehicleList.size()); //we should have 3 vehicles
        vehicleList.clear();

        List<Vehicle> latestInventory = dealership.getAllVehicles();
        assertEquals(2, latestInventory.size()); //Clearing the returned list should not affect the dealership inventory.
    }


}
