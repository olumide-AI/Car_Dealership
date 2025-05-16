package runone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalesContractTest {
    @Test
    public void getMonthPaymentTest(){
        //Arrange
        Vehicle vehicle = new Vehicle("0112ghf", 2023, "Honda", "Accord", "Sedan", "Blue", 300000, 24000);

        //Act
        Dealership dealership = new Dealership("olumide", "12 bunk rd", "303093933");
        dealership.addVehicle(vehicle);
        Contract contract = new SalesContract("2025-03-19", "Maaike", "ma@gmail.com", vehicle, dealership.getAllVehicles(), 0);

        //Assert
        assertEquals(555.89,contract.getMonthlyPayment(), 0.009, "Assertion for get monthly payment method");
    }

    @Test
    public void getTotalPrice(){
        //Arrange
        Vehicle vehicle = new Vehicle("0112ghf", 2023, "Honda", "Accord", "Sedan", "Blue", 300000, 1000);

        //Act
        Dealership dealership = new Dealership("olumide", "12 bunk rd", "303093933");
        dealership.addVehicle(vehicle);
        Contract contract = new SalesContract("2025-03-19", "Maaike", "ma@gmail.com", vehicle, dealership.getAllVehicles(), 0);

        //Assert
        assertEquals(1645, contract.getTotalPrice(), 0.009);
    }
}
