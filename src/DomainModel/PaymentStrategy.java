package DomainModel;

public interface PaymentStrategy {
    default int calculate_cost(int total_cost) {
        return total_cost;
    }
    default String getName(){return null;}
}
