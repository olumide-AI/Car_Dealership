package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Contract {
    //Fields
    private String date;
    private String customerName;
    private String CustomerEmail;
    private Vehicle VehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    List<Vehicle> vehicleList = new ArrayList<>();

    //Constructors

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold, List<Vehicle> vehicleList) {
        this.date = date;
        this.customerName = customerName;
        this.vehicleList = vehicleList;
        CustomerEmail = customerEmail;
        VehicleSold = vehicleSold;
    }

    //getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return VehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        VehicleSold = vehicleSold;
    }

    //Methods
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    //Create a to file string method
    public abstract String toFileString();
}
