package runone;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DealershipFileManagerTest {

    @Test
    public void testLoadAKnowDealershipFile (){
        Dealership dealership = DealershipFileManager.getDealership("inventory.csv");
        assertNotNull(dealership);
        assertEquals("D & B Used Cars", dealership.getName());
        assertEquals("111 Old Benbrook Rd", dealership.getAddress());
        assertEquals("817-555-5555", dealership.getPhone());
    }

    @Test
    public void testLoadDealershipVehicles (){
        Dealership dealership = DealershipFileManager.getDealership("inventory.csv");
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        //Assert vehcile list has the right number of vehicles
        assertEquals(3, vehicleList.size());
        //Assert that the correct vehicles are loaded
        Vehicle vehicle1 = vehicleList.get(0);
        assertEquals(10112, vehicle1.getVin());
        assertEquals("Ford", vehicle1.getMake());

        Vehicle vehicle2 = vehicleList.get(2);
        assertEquals(44901,vehicle2.getVin());
        assertEquals("Honda", vehicle2.getMake());

//        VIN: 10112 | 1993 Ford Explorer | Type: SUV | Color: Red | Odometer: 525,123 miles | Price: $995.00
//        VIN: 37846 | 2001 Ford Ranger | Type: truck | Color: Yellow | Odometer: 172,544 miles | Price: $1,995.00
//        VIN: 44901 | 2012 Honda Civic | Type: SUV | Color: Gray | Odometer: 103,221 miles | Price: $6,995.00

    }
    @Test
    public void testFileNotFound (){
        Dealership dealership = DealershipFileManager.getDealership("notinventory.csv");
        assertNull(dealership);
    }
}
