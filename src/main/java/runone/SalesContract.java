package runone;

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
}
