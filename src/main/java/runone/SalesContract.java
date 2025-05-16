package runone;

import javax.swing.plaf.PanelUI;
import java.util.List;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private static final double RECORDING_FEE = 100;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, List<Vehicle> vehicleList, double salesTaxAmount) {
        super(date, customerName, customerEmail, vehicleSold, vehicleList);
        this.salesTaxAmount = calculateSalesTax();
    }
    //Can get a vehicle or multiple vechile???
    public double calculateSalesTax(){

        return originalPrice()*0.05;

    }

    public double originalPrice(){
        return getVehicleSold().getPrice();
    }

    public double processingFee(){

        if (originalPrice() > 10000){
            return 295;
        }
        else {
            return 495;
        }
    }
    @Override
    public double getTotalPrice(){

        return originalPrice() + calculateSalesTax() + processingFee() + RECORDING_FEE;
    }

    @Override
    public double getMonthlyPayment(){
        if (originalPrice() >= 10000){
            return getTotalPrice() * (1+ 0.0425)/48;
        }
        else{
            return getTotalPrice() * (1+ 0.0525)/24;
        }
    }

    public String toFileString(){
        Vehicle vehicle = getVehicleSold();
        return "SALE|" + getDate() + "|" +
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
                String.format("%.2f", calculateSalesTax()) + "|" +
                "100.00|" +  // recording fee
                String.format("%.2f", processingFee()) + "|" +
                String.format("%.2f", getTotalPrice()) + "|" +
                "YES|" + // financed
                String.format("%.2f", getMonthlyPayment());
    }

}
