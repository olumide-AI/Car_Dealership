package runone;

public class Vehicle {
    //Fields
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    //Constructor to create objects, setting all fields
    public Vehicle(int vin, String make, int year, String model, String vehicleType, int odometer, String color, double price) {
        this.vin = vin;
        this.make = make;
        this.year = year;
        this.model = model;
        this.vehicleType = vehicleType;
        this.odometer = odometer;
        this.color = color;
        this.price = price;
    }

    //Getters and setters for all fields

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //toString method to print clean readable vehicle summary
    public String toString() {
        return getMake() + " " + getModel() + " " + getYear() + " is a " + getColor() + " " + getVehicleType() +" having a total miles of: " + getOdometer() + " and cost: " + getPrice() + ". The vin number is: " + getVin();
    }
}
