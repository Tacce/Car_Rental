package BusinessLogic;
import DomainModel.User;
import ORM.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginController {

    public LoginController() {}

    public void register() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("REGISTRAZIONE");
        System.out.println("Inserisci il tuo nome: ");
        String name = scanner.nextLine();
        System.out.println("Inserisci il tuo cognome: ");
        String surname = scanner.nextLine();
        System.out.println("Inserisci la tua età: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci il codice della tua patente: ");
        String license = scanner.nextLine();
        System.out.println("Inserisci il tuo username: ");
        String username = scanner.nextLine();
        System.out.println("Inserisci la tua password: ");
        String password = scanner.nextLine();

        UserDAO userDAO = new UserDAO();
        userDAO.register(name, surname, age, license, username, password);
    }

    public User login() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        UserDAO userDAO = new UserDAO();
        return userDAO.login(username, password);
    }

    public boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Password Admin: ");
        String password = scanner.nextLine();
        return password.equals("ADMIN");
    }
}

