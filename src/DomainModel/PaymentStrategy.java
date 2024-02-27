package DomainModel;

public interface PaymentStrategy {
    default float calculate_cost(float daily_price, int ndays) {
        return daily_price*ndays;
    }

    default float cancelTax(float daily_price, int ndays){return 0;}

    default String getName(){return null;}
}
