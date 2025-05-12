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
    @Test
    public void testGetVehiclesByMakeModel() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2019, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2018, "Toyota", "Corolla", "Sedan", "White", 30000, 18000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByMakeModel("Toyota");

        // Assert
        assertEquals(2, results.size());
        assertEquals(1001, results.get(0).getVin());
        assertEquals(1003, results.get(1).getVin());
    }

    @Test
    public void testRemoveVehicleByVin() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));

        // Act
        boolean removed = dealership.removeVehicleByVin(1001);

        // Assert
        assertTrue(removed); // Vehicle should be  removed.
        assertEquals(0, dealership.getAllVehicles().size()); // "Inventory should be empty after removal."
    }

    @Test
    public void testRemoveVehicleByVin_NotFound() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));

        // Act
        boolean removed = dealership.removeVehicleByVin(9999);  // wrong VIN

        // Assert
        assertFalse(removed); //Vehicle removal should fail because VIN does not exist.
        assertEquals(1, dealership.getAllVehicles().size()); //No change to inventory cause no removal happened
    }
    @Test
    public void testGetVehiclesByPriceRange_FindsMatchingVehicles() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2019, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2018, "Ford", "Fusion", "Sedan", "Black", 30000, 18000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByPriceRange(18000, 25000);

        // Assert
        assertEquals(3, results.size(), "Expected 3 vehicles in price range.");
    }

    @Test
    public void testGetVehiclesByPriceRange_NoMatches() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByPriceRange(30000, 50000);  // No vehicles this expensive

        // Assert
        assertTrue(results.isEmpty(), "Expected no vehicles in this price range.");
    }

    @Test
    public void testGetVehiclesByPriceRange_BoundaryCheck() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByPriceRange(25000, 25000);  // Exact match at upper boundary

        // Assert
        assertEquals(1, results.size(), "Expected exactly one vehicle matching the boundary price.");
        assertEquals(1001, results.get(0).getVin());


    }

    @Test
    public void testGetVehiclesByYearRange() {
        // Arrange: Create dealership and add vehicles
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2018, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2020, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Fusion", "Sedan", "Black", 5000, 30000));

        // Act: Search for vehicles between 2019 and 2022 (should find 2020 and 2022 models)
        List<Vehicle> results = dealership.getVehiclesByYearRange(2019, 2022);

        // Assert: Expect 2 results
        assertEquals(2, results.size(), "Expected 2 vehicles in the year range.");
        assertEquals(1002, results.get(0).getVin(), "First vehicle should have VIN 1002.");
        assertEquals(1003, results.get(1).getVin(), "Second vehicle should have VIN 1003.");
    }

    @Test
    public void testGetVehiclesColor() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2019, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2018, "Toyota", "Corolla", "Sedan", "White", 30000, 18000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByColor("Red");

        // Assert
        assertEquals(1, results.size());
        assertEquals("Red", results.get(0).getColor());

    }

    @Test
    public void testGetVehiclesByMileageRange() {
        // Arrange: Create dealership and add vehicles
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2018, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2020, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Fusion", "Sedan", "Black", 5000, 30000));

        // Act: Search for vehicles between 2019 and 2022 (should find 2020 and 2022 models)
        List<Vehicle> results = dealership.getVehiclesByMileageRange(5000, 12000);

        // Assert: Expect 2 results
        assertEquals(2, results.size());
        assertEquals(12000, results.get(0).getOdometer());
        assertEquals(5000, results.get(1).getOdometer());
    }

    @Test
    public void testGetVehiclesByType() {
        // Arrange
        Dealership dealership = new Dealership("Test Dealership", "123 Main St", "555-1234");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Red", 15000, 25000));
        dealership.addVehicle(new Vehicle(1002, 2019, "Honda", "Civic", "Sedan", "Blue", 12000, 20000));
        dealership.addVehicle(new Vehicle(1003, 2018, "Toyota", "Corolla", "Sedan", "White", 30000, 18000));

        // Act
        List<Vehicle> results = dealership.getVehiclesByVehicleType("Sedan");

        // Assert
        assertEquals(3, results.size());
        assertEquals("Sedan", results.get(0).getVehicleType());
        assertEquals("Sedan", results.get(2).getVehicleType());


    }
}
