package DomainModel;

import java.util.ArrayList;

public abstract class Vehicle {
    protected String model, plate;
    protected float daily_price;
    protected boolean available;

    public Vehicle(String model, String plate, float daily_price, boolean available) {
        this.model = model;
        this.plate = plate;
        this.daily_price = daily_price;
        this.available = available;
    }

    public static void printVehicleArray(ArrayList<Vehicle> vehicles){
        int i = 1;
        for(Vehicle vehicle:vehicles){
            System.out.printf("%d) " + vehicle.getInfo() +"\n", i);
            i++;
        }
    }
    public String getInfo(){return null;};

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public float getDaily_price() {
        return daily_price;
    }

    public boolean isAvailable() {
        return available;
    }

}
