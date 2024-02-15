package DomainModel;

public class RoadsideAssistance {
    private final String name,phone_number;

    public RoadsideAssistance(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getInfo() {
        return String.format("Assicurazione: %s (%s)", name, phone_number);
    }
}
