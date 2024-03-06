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

    public String getInfo(){
        String info = String.format("Targa: %s   Modello: %s   Posti: %d   Prezzo Giornaliero: %.2f   ",
                plate, model, nseats, dailyPrice);
        info += assistance.getInfo();
        if (available){
            info += "   DISPONIBILE";
        }else
            info += "   NON DISPONIBILE";
        return info;
    }

    public int getNseats() {
        return nseats;
    }

    public RoadsideAssistance getAssistance() {
        return assistance;
    }
}
