package DomainModel;

public class Cash implements PaymentStrategy{
    @Override
    public String getName() {
        return "Contanti";
    }
}
