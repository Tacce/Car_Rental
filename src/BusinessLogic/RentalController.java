package BusinessLogic;

import DomainModel.Rental;
import ORM.RentalDAO;
import ORM.VehicleDAO;
import java.sql.SQLException;

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
        myRental.cancel();
    }

    public void returnVehicle() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.removeRental(myRental.getUser().getUsername(), myRental.getVehicle().getPlate());
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(myRental.getVehicle().getPlate(), true);
        myRental.returnVehicle();
    }

    public void modifyNDays(int newNdays) throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.updateNDays(newNdays, myRental.getUser().getUsername(), myRental.getVehicle().getPlate());
        myRental.setNdays(newNdays);
    }
}
