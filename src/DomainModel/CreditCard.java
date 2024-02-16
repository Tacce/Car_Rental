package DomainModel;

public class CreditCard implements PaymentStrategy{
    private float commission = 0.1F;

    public float getCommission() {
        return commission;
    }
}
