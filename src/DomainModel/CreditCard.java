package DomainModel;

public class CreditCard implements PaymentStrategy{
    private float commission;

    public CreditCard(float commission) {
        this.commission = commission;
    }

    public float getCommission() {
        return commission;
    }
}
