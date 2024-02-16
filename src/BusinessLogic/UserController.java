package BusinessLogic;

import DomainModel.*;
import ORM.CarDAO;
import ORM.MopedDAO;
import ORM.RentalDAO;
import ORM.VehicleDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    User myUser;

    public UserController(User myUser) {
        this.myUser = myUser;
    }

    public void viewAvailableVehicles() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAvailableCars();
        System.out.println("AUTOMOBILI\n");
        Vehicle.printVehicleArray(cars);
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAvailableMopeds();
        System.out.println("\nMOTORINI\n");
        Vehicle.printVehicleArray(mopeds);
    }

    public void RentCar() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAvailableCars();
        Vehicle.printVehicleArray(cars);
        int n = cars.size();
        Scanner scanner =new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona auto: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        RentVehicle(cars.get(x-1).getPlate());
    }

    public void RentMoped() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAvailableMopeds();
        Vehicle.printVehicleArray(mopeds);
        int n = mopeds.size();
        Scanner scanner =new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona Motorino: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        RentVehicle(mopeds.get(x-1).getPlate());
    }

    private void RentVehicle(String plate) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il numero di giorni: ");
        int ndays = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci il metodo di pagamento (0-Contanti 1-Carta di Credito): ");
        int pm = scanner.nextInt();
        scanner.nextLine();

        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.addRental(myUser.getUsername(), plate, ndays, pm);
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(plate, false);
    }

}
