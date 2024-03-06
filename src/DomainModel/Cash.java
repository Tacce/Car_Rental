package DomainModel;

public class Cash implements PaymentStrategy{
    @Override
    public String getName() {
        return "Contanti";
    }

    @Override
    public float calculateCancelTax(float daily_price, int ndays) {
        return calculateCost(daily_price, ndays)/10;
    }
}
