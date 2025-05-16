package runone;

public class Vehicle {
    //Fields
    private String vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    //Constructor to create objects, setting all fields
    public Vehicle(String vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }


    //Getters and setters for all fields

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
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

    //Write defensive test for set Price
    public void setPrice(double price) {
        if (price < 0){
            throw new IllegalArgumentException("Price can't be negative.");
        }
        this.price = price;
    }

    //toString method to print clean readable vehicle summary
    @Override
    public String toString() {
        return String.format(
                "VIN: %s | %d %s %s | Type: %s | Color: %s | Odometer: %,d miles | Price: $%,.2f",
                vin, year, make, model, vehicleType, color, odometer, price
        );
    }

}
