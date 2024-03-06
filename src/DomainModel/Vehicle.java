package DomainModel;

import java.util.ArrayList;

public abstract class Vehicle {
    protected String model, plate;
    protected float dailyPrice;
    protected boolean available;

    public Vehicle(String model, String plate, float dailyPrice, boolean available) {
        this.model = model;
        this.plate = plate;
        this.dailyPrice = dailyPrice;
        this.available = available;
    }

    public static void printVehicleArray(ArrayList<Vehicle> vehicles){
        int i = 1;
        for(Vehicle vehicle:vehicles){
            System.out.printf("%d) " + vehicle.getInfo() +"\n", i);
            i++;
        }
    }
    public String getInfo(){return null;}

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public float getDailyPrice() {
        return dailyPrice;
    }

    public boolean isAvailable() {
        return available;
    }

}
