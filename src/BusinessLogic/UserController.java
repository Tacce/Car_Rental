package BusinessLogic;

import DomainModel.*;
import ORM.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    User myUser;

    public UserController(User myUser) {this.myUser = myUser;}

    public void viewAvailableVehicles() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAvailableCars();
        System.out.println("\nAUTOMOBILI\n");
        if (cars.size()==0){
            System.out.println("Nessun'auto disponibile");
        }else
            Vehicle.printVehicleArray(cars);
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAvailableMopeds();
        System.out.println("\nMOTORINI\n");
        if (mopeds.size()==0){
            System.out.println("Nessun motorino disponibile");
        }else
            Vehicle.printVehicleArray(mopeds);
    }

    public ArrayList<Vehicle> viewAvailableCars() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        return carDAO.selectAvailableCars();
    }

    public ArrayList<Vehicle> viewAvailableMopeds() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        return mopedDAO.selectAvailableMopeds();
    }

    public void rentVehicle(String plate, int ndays, int pm, int code) throws SQLException, ClassNotFoundException {
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
