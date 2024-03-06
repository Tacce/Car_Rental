package BusinessLogic;

import DomainModel.User;
import ORM.CarDAO;
import ORM.MopedDAO;
import ORM.RentalDAO;
import ORM.UserDAO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void rentCarTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "renttest", "-", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "renttest", "-");

        CarDAO carDAO = new CarDAO();
        carDAO.insertCar("renttestplate", "-",0,0,0);
        int initialAvailable = carDAO.selectAvailableCars().size();

        UserController userController = new UserController(user);
        String simulatedInput = String.format("%s\n0\n0\n", initialAvailable);
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        userController.RentCar();
        System.setIn(originalSystemIn);

        int finalAvailable = carDAO.selectAvailableCars().size();
        assertEquals(finalAvailable, initialAvailable-1);

        RentalDAO rentalDAO = new RentalDAO();
        assertEquals(rentalDAO.getUserRental(user).size(),1);

        rentalDAO.removeRental("renttest","renttestplate");
        userDAO.removeUser("renttest");
        carDAO.removeCar("renttestplate");
    }

    @Test
    void rentMopedTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "renttest", "-", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "renttest", "-");

        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped("renttestplate", "-",0,0);
        int initialAvailable = mopedDAO.selectAvailableMopeds().size();

        UserController userController = new UserController(user);
        String simulatedInput = String.format("%s\n0\n0\n", initialAvailable);
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        userController.RentMoped();
        System.setIn(originalSystemIn);

        int finalAvailable = mopedDAO.selectAvailableMopeds().size();
        assertEquals(finalAvailable, initialAvailable-1);

        RentalDAO rentalDAO = new RentalDAO();
        assertEquals(rentalDAO.getUserRental(user).size(),1);

        rentalDAO.removeRental("renttest","renttestplate");
        userDAO.removeUser("renttest");
        mopedDAO.removeMoped("renttestplate");
    }

    @Test
    void resetPasswordTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "resettest", "pass1", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "resettest", "pass1");

        UserController userController = new UserController(user);
        String simulatedInput = "pass1\npass2\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        userController.resetPassword();
        System.setIn(originalSystemIn);

        assertEquals(user.getPassword(), "pass2");
        User user1 = userDAO.getUser("resettest");
        assertEquals(user1.getPassword(), "pass2");

        userDAO.removeUser("resettest");
    }

}