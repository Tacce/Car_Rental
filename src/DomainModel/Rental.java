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

    public void handleCancel(){
        String msg = String.format("Tassa per la cancellazione: %.2f €",
                payment_method.cancelTax(vehicle.daily_price, ndays));
        System.out.println(msg);    }

    public void handleReturn() {
        String msg = String.format("Costo totale per il noleggio: %.2f €",
                payment_method.calculate_cost(vehicle.daily_price, ndays));
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

    public PaymentStrategy getPayment_method() {
        return payment_method;
    }
}
