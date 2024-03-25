package BusinessLogic;

import DomainModel.Rental;
import DomainModel.User;
import DomainModel.Vehicle;
import ORM.CarDAO;
import ORM.MopedDAO;
import ORM.RentalDAO;
import ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {

    public void addCar(String plate, String model, float dailyPrice, int nseats, int assintanceId)
            throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        carDAO.insertCar(plate, model, dailyPrice, nseats, assintanceId);
    }

    public void addMoped(String plate, String model, float dailyPrice, int displacement)
            throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped(plate, model, dailyPrice,displacement);
    }

    public ArrayList<Vehicle> viewAllCars() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        return carDAO.selectAllCars();
    }

    public void removeCar(String plate) throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        carDAO.removeCar(plate);
    }

    public ArrayList<Vehicle> viewAllMopeds() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        return mopedDAO.selectAllMopeds();
    }

    public void removeMoped(String plate) throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.removeMoped(plate);
    }

    public ArrayList<User> viewAllUsers() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        return userDAO.selectAllUsers();
    }

    public ArrayList<Rental> viewAllRentals() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        return rentalDAO.getAllRentals();
    }
}
