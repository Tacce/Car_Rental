package DomainModel;

public class Moped extends Vehicle{
    private String displacement;

    public Moped(String model, String plate, int daily_price, boolean available, String displacement) {
        super(model, plate, daily_price, available);
        this.displacement = displacement;
    }

    public String getDisplacement() {
        return displacement;
    }
}
