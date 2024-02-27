package DomainModel;

public class Cash implements PaymentStrategy{
    @Override
    public String getName() {
        return "Contanti";
    }

    @Override
    public float cancelTax(float daily_price, int ndays) {
        return calculate_cost(daily_price, ndays)/10;
    }
}
