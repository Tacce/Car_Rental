package DomainModel;

public class RoadsideAssistance {
    private final String name, phoneNumber;

    public RoadsideAssistance(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInfo() {
        return String.format("Assicurazione: %s (%s)", name, phoneNumber);
    }
}
