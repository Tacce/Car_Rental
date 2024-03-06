package BusinessLogic;

import DomainModel.*;
import ORM.MopedDAO;
import ORM.RentalDAO;
import ORM.UserDAO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RentalControllerTest {

    @Test
    void returnVehicleTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "usertest", "-", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "usertest", "-");

        Moped moped = new Moped("-", "renttestplate",10,true, 0);
        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped("renttestplate", "-",10,0);
        mopedDAO.setAvailable("renttestplate", false);

        Cash cash = new Cash();
        Rental rental = new Rental(user, moped, 3, cash);
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRental("usertest","renttestplate",10, 0, 1);

        int initialRentCount = rentalDAO.getUserRental(user).size();

        RentalController rentalController = new RentalController(rental);
        rentalController.returnVehicle();

        assertEquals(cash.calculate_cost(10, 3),30);

        int finalRentCount = rentalDAO.getUserRental(user).size();
        assertEquals(finalRentCount, initialRentCount - 1);
        assertTrue(moped.isAvailable());
        assertTrue(mopedDAO.getMoped("renttestplate").isAvailable());

        userDAO.removeUser("usertest");
        mopedDAO.removeMoped("renttestplate");
    }

    @Test
    void cancelRentalTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "usertest", "-", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "usertest", "-");

        Moped moped = new Moped("-", "renttestplate",10,true, 0);
        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped("renttestplate", "-",10,0);
        mopedDAO.setAvailable("renttestplate", false);

        CreditCard creditCard = new CreditCard();
        Rental rental = new Rental(user, moped, 3, creditCard);
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRental("usertest","renttestplate",10, 0, 1);

        int initialRentCount = rentalDAO.getUserRental(user).size();

        RentalController rentalController = new RentalController(rental);
        rentalController.cancelRental();

        assertEquals(creditCard.cancelTax(10, 3),0);

        int finalRentCount = rentalDAO.getUserRental(user).size();
        assertEquals(finalRentCount, initialRentCount - 1);
        assertTrue(moped.isAvailable());
        assertTrue(mopedDAO.getMoped("renttestplate").isAvailable());

        userDAO.removeUser("usertest");
        mopedDAO.removeMoped("renttestplate");
    }

    @Test
    void modifyNDaysTest() throws SQLException, ClassNotFoundException {
        User user = new User("-", "-", "usertest", "-", "-", 0);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser("-", "-", 0, "-", "usertest", "-");

        Moped moped = new Moped("-", "renttestplate",10,true, 0);
        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped("renttestplate", "-",10,0);
        mopedDAO.setAvailable("renttestplate", false);

        CreditCard creditCard = new CreditCard();
        Rental rental = new Rental(user, moped, 3, creditCard);
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRental("usertest","renttestplate",10, 0, 1);

        String simulatedInput = "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        RentalController rentalController = new RentalController(rental);
        rentalController.modifyNDays();

        assertEquals(rental.getNdays(),5);
        assertEquals(rentalDAO.getUserRental(user).get(0).getNdays(),5);

        rentalDAO.removeRental("usertest","renttestplate");
        userDAO.removeUser("usertest");
        mopedDAO.removeMoped("renttestplate");
    }
}