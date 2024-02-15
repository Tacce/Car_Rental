package DomainModel;

public class Moped extends Vehicle{
    private int displacement;

    public Moped(String model, String plate, float daily_price, boolean available, int displacement) {
        super(model, plate, daily_price, available);
        this.displacement = displacement;
    }

    public String getInfo(){
        String info = String.format("Targa: %s   Modello: %s   Cilindrata: %d   Prezzo Giornaliero: %.2f ",
                plate, model, displacement, daily_price);
        if (available){
            info += "  DISPONIBILE  ";
        }else
            info += "  NON DISPONIBILE   ";
        return info;
    }

    public int getDisplacement() {
        return displacement;
    }
}
