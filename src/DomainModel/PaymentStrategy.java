package DomainModel;

public interface PaymentStrategy {
    default float calculateCost(float daily_price, int ndays) {
        return daily_price*ndays;
    }

    default float calculateCancelTax(float daily_price, int ndays){return 0;}

    default String getName(){return null;}
}
