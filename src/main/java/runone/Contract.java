package runone;

public abstract class Contract {
    //Fields
    private String date;
    private String customerName;
    private String CustomerEmail;
    private int VehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    //Constructors

    public Contract(String date, String customerName, String customerEmail, int vehicleSold) {
        this.date = date;
        this.customerName = customerName;
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

    public int getVehicleSold() {
        return VehicleSold;
    }

    public void setVehicleSold(int vehicleSold) {
        VehicleSold = vehicleSold;
    }

    //Methods
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
