package BusinessLogic;

import DomainModel.*;
import ORM.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    User myUser;

    public UserController(User myUser) {this.myUser = myUser;}

    public ArrayList<Vehicle> viewAvailableCars() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        return carDAO.selectAvailableCars();
    }

    public ArrayList<Vehicle> viewAvailableMopeds() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        return mopedDAO.selectAvailableMopeds();
    }

    public void rentCar(String plate, int ndays, int pm) throws SQLException, ClassNotFoundException {
        rentVehicle(plate, ndays, pm, 0);
    }

    public void rentMoped(String plate, int ndays, int pm) throws SQLException, ClassNotFoundException {
        rentVehicle(plate, ndays, pm, 1);
    }

    private void rentVehicle(String plate, int ndays, int pm, int code) throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRental(myUser.getUsername(), plate, ndays, pm, code);
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(plate, false);
    }

    public ArrayList<Rental> viewAllRentals() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        return rentalDAO.getUserRental(myUser);
    }

    public void resetPassword(String password, String newPassword) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        if (!password.equals(myUser.getPassword())){
            System.out.println("La password inserita non coincide con quella corrente");
            return;
        }
        userDAO.updatePassword(myUser.getUsername(), newPassword);
        myUser.setPassword(newPassword);
    }

}
