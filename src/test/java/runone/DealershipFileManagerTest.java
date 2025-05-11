package runone;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DealershipFileManagerTest {

    @Test
    public void testLoadAKnowDealershipFile() {
        Dealership dealership = DealershipFileManager.getDealership("inventory.csv");
        assertNotNull(dealership);
        assertEquals("D & B Used Cars", dealership.getName().trim());
        assertEquals("111 Old Benbrook Rd", dealership.getAddress().trim());
        assertEquals("817-555-5555", dealership.getPhone().trim());
    }

    @Test
    public void testLoadDealershipVehicles() {
        Dealership dealership = DealershipFileManager.getDealership("inventory.csv");
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        //Assert vehcile list has the right number of vehicles
        assertEquals(2, vehicleList.size());
        //Assert that the correct vehicles are loaded
        Vehicle vehicle1 = vehicleList.get(0);
        assertEquals(10112, vehicle1.getVin());
        assertEquals("Ford", vehicle1.getMake());

        Vehicle vehicle2 = vehicleList.get(1);
        assertEquals(37846, vehicle2.getVin());
        assertEquals("Ford", vehicle2.getMake());

//        VIN: 10112 | 1993 Ford Explorer | Type: SUV | Color: Red | Odometer: 525,123 miles | Price: $995.00
//        VIN: 37846 | 2001 Ford Ranger | Type: truck | Color: Yellow | Odometer: 172,544 miles | Price: $1,995.00
//        VIN: 44901 | 2012 Honda Civic | Type: SUV | Color: Gray | Odometer: 103,221 miles | Price: $6,995.00

    }

    @Test
    public void testFileNotFound() {
        Dealership dealership = DealershipFileManager.getDealership("notinventory.csv");
        assertNull(dealership);
    }

    @Test
    public void testSaveDealershipCreatesCorrectFile() throws IOException {
        // Arrange: Create a dealership and add vehicles
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        dealership.addVehicle(new Vehicle(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995.0));
        dealership.addVehicle(new Vehicle(37846, 2001, "Ford", "Ranger", "truck", "Yellow", 172544, 1995.0));

        String filename = "inventory.csv";

        // Act: Save the dealership to a file
        DealershipFileManager.saveDealership(dealership, filename);

        // Assert: Read the file and verify content
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String header = reader.readLine();
            assertEquals("D & B Used Cars|111 Old Benbrook Rd|817-555-5555", header);

            String vehicleLine1 = reader.readLine();
            assertEquals("10112|1993|Ford|Explorer|SUV|Red|525123|995.0", vehicleLine1);

            String vehicleLine2 = reader.readLine();
            assertEquals("37846|2001|Ford|Ranger|truck|Yellow|172544|1995.0", vehicleLine2);

            //assertNull(reader.readLine(), "File should not have extra lines.");
        }
    }
}
