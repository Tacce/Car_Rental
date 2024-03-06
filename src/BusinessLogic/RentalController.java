package BusinessLogic;

import DomainModel.Rental;
import ORM.RentalDAO;
import ORM.VehicleDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class RentalController {
    Rental myRental;

    public RentalController(Rental myRental) {
        this.myRental = myRental;
    }

    public void cancelRental() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.removeRental(myRental.getUser().getUsername(), myRental.getVehicle().getPlate());
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(myRental.getVehicle().getPlate(), true);
        myRental.Cancel();
    }

    public void returnVehicle() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.removeRental(myRental.getUser().getUsername(), myRental.getVehicle().getPlate());
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(myRental.getVehicle().getPlate(), true);
        myRental.Return();
    }

    public void modifyNDays() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nuovo numero di giorni: ");
        int newNdays = scanner.nextInt();
        scanner.nextLine();
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.updateNDays(newNdays, myRental.getUser().getUsername(), myRental.getVehicle().getPlate());
        myRental.setNdays(newNdays);
    }
}
