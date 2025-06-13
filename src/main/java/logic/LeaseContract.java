package logic;

import model.Contract;
import model.Vehicle;

import java.util.List;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE = 0.07;
    private static final double EXPECTED_ENDING_VALUE = 0.50;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, List<Vehicle> vehicleList) {
        super(date, customerName, customerEmail, vehicleSold, vehicleList);
    }
    public double getLeaseFee(){
        return getVehicleSold().getPrice() * LEASE_FEE;
    }

    public double getExpectedEndingValue(){
        return getVehicleSold().getPrice() * EXPECTED_ENDING_VALUE;
    }

    @Override
    public double getTotalPrice() {

        return getVehicleSold().getPrice() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment(){
        //total price financed at 4% for 36 month
        //totalprice * (1+0.04)/36
        return getTotalPrice() * (1+0.04)/36;
    }
    public String toFileString(){
        Vehicle vehicle = getVehicleSold();
        return "LEASE|" + getDate() + "|" +
                getCustomerName() + "|" +
                getCustomerEmail() + "|" +
                vehicle.getVin() + "|" +
                vehicle.getYear() + "|" +
                vehicle.getMake() + "|" +
                vehicle.getModel() + "|" +
                vehicle.getVehicleType() + "|" +
                vehicle.getColor() + "|" +
                vehicle.getOdometer() + "|" +
                String.format("%.2f", vehicle.getPrice()) + "|" +
                String.format("%.2f", getExpectedEndingValue()) + "|" +
                String.format("%.2f",getLeaseFee()) + "|" +
                String.format("%.2f", getTotalPrice()) + "|" +
                "YES|" + // financed
                String.format("%.2f", getMonthlyPayment());
    }
}
