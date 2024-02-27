package DomainModel;

public class CreditCard implements PaymentStrategy{
    private final float commission = 0.1F;

    public float getCommission() {
        return commission;
    }

    @Override
    public float calculate_cost(float daily_price, int ndays) {
        return daily_price * ndays * (1 + commission);
    }

    @Override
    public String getName() {
        return "Carta di Credito";
    }
}
