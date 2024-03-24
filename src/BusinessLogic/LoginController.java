package BusinessLogic;
import DomainModel.User;
import ORM.UserDAO;

import java.sql.SQLException;

public class LoginController {

    public LoginController() {}

    public void register(String name, String surname, int age, String license, String username, String password)
            throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname, age, license, username, password);
    }

    public User login(String username, String password) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        return userDAO.checkPassword(username, password);
    }

    public boolean adminLogin(String password) {
        return password.equals("ADMIN");
    }
}

