package DomainModel;

public class User {
    private String name, surname, username, password, licence_code;
    private int age;

    public User(String name, String surname, String username, String password, String licence_code, int age) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.licence_code = licence_code;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLicence_code() {
        return licence_code;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return String.format("Nome Utente: %s   Nome: %s   Cognome: %s   Età: %d   Codice Patente: %s   Password: %s",
                username, name, surname, age, licence_code, password);
    }
}

