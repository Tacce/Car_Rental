package DomainModel;

public class User {
    private String name, surname, username, password, licenceCode;
    private int age;

    public User(String name, String surname, String username, String password, String licenceCode, int age) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.licenceCode = licenceCode;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return String.format("Nome Utente: %s   Nome: %s   Cognome: %s   Età: %d   Codice Patente: %s   Password: %s",
                username, name, surname, age, licenceCode, password);
    }
}

