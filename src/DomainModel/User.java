package DomainModel;

public class User {
    private String name, licence_code;
    private int age;

    public User(String name, String licence_code, int age) {
        this.licence_code = licence_code;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLicence_code() {
        return licence_code;
    }

    public int getAge() {
        return age;
    }
}

