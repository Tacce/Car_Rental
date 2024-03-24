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

    public void viewAllVehicles() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAllCars();
        System.out.println("\nAUTOMOBILI\n");
        if (cars.size()==0){
            System.out.println("Nessun'auto registrata");
        }else
            Vehicle.printVehicleArray(cars);
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAllMopeds();
        System.out.println("\nMOTORINI\n");
        if (mopeds.size()==0){
            System.out.println("Nessun motorino registrato");
        }else
            Vehicle.printVehicleArray(mopeds);
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

    public void viewAllUsers() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.selectAllUsers();
        if(users.size()==0){
            System.out.println("Nessun utente registrato");
            return;
        }
        int i = 1;
        System.out.println("\nUTENTI\n");
        for(User user:users){
            System.out.printf("%d) " + user.getInfo() +"\n", i);
            i++;
        }
    }

    public void viewAllRentals() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        ArrayList<Rental> rentals = rentalDAO.getAllRentals();
        if(rentals.size()==0){
            System.out.println("Nessuna prenotazione registrata");
            return;
        }
        int i = 1;
        for(Rental rental:rentals){
            System.out.printf("%d) " + rental.getInfo() +"\n", i);
            i++;
        }
    }
}
