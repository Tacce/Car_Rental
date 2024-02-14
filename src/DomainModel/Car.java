package DomainModel;

public class Car extends Vehicle {
    private int nseats;
    private RoadsideAssistance assistance;

    public Car(String model, String plate, float daily_price, boolean available, int nseats,
               RoadsideAssistance assistance) {
        super(model, plate, daily_price, available);
        this.nseats = nseats;
        this.assistance = assistance;
    }

    public int getNseats() {
        return nseats;
    }

    public RoadsideAssistance getAssistance() {
        return assistance;
    }
}
