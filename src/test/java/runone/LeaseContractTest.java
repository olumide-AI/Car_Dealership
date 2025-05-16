package runone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeaseContractTest {
    @Test
    public void getMonthlyPaymentTest(){
        //Arrange
        Vehicle vehicle = new Vehicle("0112ghf", 2023, "Honda", "Accord", "Sedan", "Blue", 300000, 24000);
        Vehicle vehicle1 = new Vehicle("0112ABD", 2023, "Honda", "Civic", "Sedan", "Blue", 3000, 42000);
        Dealership dealership = new Dealership("olumide", "12 bunk rd", "303093933");
        dealership.addVehicle(vehicle);
        dealership.addVehicle(vehicle1);
        Contract contract = new SalesContract("2025-03-19", "Maaike", "ma@gmail.com", vehicle, dealership.getAllVehicles(), 0);

        //Act and Assert
        assertEquals(2, dealership.getAllVehicles().size());
        assertEquals(555.89, contract.getMonthlyPayment(), 0.009);
    }
}
