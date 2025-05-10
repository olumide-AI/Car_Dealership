package runone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    //Test constructor and getters and setters
    @Test
    public void testVehiclesConstructorAndGetters(){
        //Act and Arrange
        Vehicle vehicle = new Vehicle(123,1997,"Toyota", "Camry", "Sedan", "Red", 155600,2000);
        //Assert
        assertEquals(123,vehicle.getVin());
        assertEquals(1997, vehicle.getYear());
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Camry", vehicle.getModel());
        assertEquals("Sedan", vehicle.getVehicleType());
        assertEquals("Red", vehicle.getColor());
        assertEquals(155600,vehicle.getOdometer());
        assertEquals(2000, vehicle.getPrice());
    }

    @Test
    public void testVehicleSetter (){
        //Act
        Vehicle vehicle = new Vehicle(123,1997,"Toyota", "Camry", "Sedan", "red", 155600,20000);

        //Arange by setting new values
        vehicle.setPrice(7000);
        vehicle.setVehicleType("Coupe");
        vehicle.setColor("Blue");
        vehicle.setMake("Honda");
        vehicle.setOdometer(77000);
        vehicle.setVin(234);
        vehicle.setYear(2009);
        vehicle.setModel("Civic");

        //Assert
        assertEquals(7000, vehicle.getPrice());
        assertEquals("Coupe", vehicle.getVehicleType());
        assertEquals("Blue", vehicle.getColor());
        assertEquals("Honda", vehicle.getMake());
        assertEquals("Civic", vehicle.getModel());
        assertEquals(77000, vehicle.getOdometer());
        assertEquals(234, vehicle.getVin());
        assertEquals(2009, vehicle.getYear());
    }

    //Test th toString method
    @Test
    public void testToStringMethod(){
        //Act
        Vehicle vehicle = new Vehicle(123,1997,"Toyota", "Camry", "Sedan", "red", 155600,20000);
        //Arrange
        String result = vehicle.toString();
        //Assert
        assertTrue(result.contains("Toyota"));
        assertTrue(result.contains("Camry"));
        assertTrue(result.contains("Sedan"));
        assertTrue(result.contains("123"));
        assertTrue(result.contains("1997"));
    }

}
