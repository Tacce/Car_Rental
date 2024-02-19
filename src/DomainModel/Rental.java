package DomainModel;

public class Rental {
    private User user;
    private Vehicle vehicle;
    private int ndays;
    private PaymentStrategy payment_method;

    public Rental(User user, Vehicle vehicle, int ndays, PaymentStrategy payment_method) {
        this.user = user;
        this.vehicle = vehicle;
        this.ndays = ndays;
        this.payment_method = payment_method;
    }

    public String getInfo(){
        return String.format("Utente: %s   Veicolo: %s (%s)   Metodo di pagamento: %s   %d giorni",
                user.getUsername(), vehicle.getModel(), vehicle.getPlate(), payment_method.getName(), ndays);
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getNdays() {
        return ndays;
    }

    public PaymentStrategy getPayment_method() {
        return payment_method;
    }
}
