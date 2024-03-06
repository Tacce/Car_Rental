package DomainModel;

public class Rental {
    private User user;
    private Vehicle vehicle;
    private int ndays;
    private PaymentStrategy paymentMethod;

    public Rental(User user, Vehicle vehicle, int ndays, PaymentStrategy paymentMethod) {
        this.user = user;
        this.vehicle = vehicle;
        this.ndays = ndays;
        this.paymentMethod = paymentMethod;
    }

    public String getInfo(){
        return String.format("Utente: %s   Veicolo: %s (%s)   Metodo di pagamento: %s   %d giorni",
                user.getUsername(), vehicle.getModel(), vehicle.getPlate(), paymentMethod.getName(), ndays);
    }

    public void handleCancel(){
        String msg = String.format("Tassa per la cancellazione: %.2f €",
                paymentMethod.cancelTax(vehicle.dailyPrice, ndays));
        System.out.println(msg);    }

    public void handleReturn() {
        String msg = String.format("Costo totale per il noleggio: %.2f €",
                paymentMethod.calculate_cost(vehicle.dailyPrice, ndays));
        System.out.println(msg);
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

    public void setNdays(int ndays) {
        this.ndays = ndays;
    }

    public PaymentStrategy getPaymentMethod() {
        return paymentMethod;
    }
}
