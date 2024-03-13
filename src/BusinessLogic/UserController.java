package BusinessLogic;

import DomainModel.*;
import ORM.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void RentCar() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAvailableCars();
        Vehicle.printVehicleArray(cars);
        int n = cars.size();
        if(n==0) {
            System.out.println("Nessun'auto disponibile");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona auto: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        RentVehicle(cars.get(x-1).getPlate(), 0, scanner);
    }

    public void RentMoped() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAvailableMopeds();
        Vehicle.printVehicleArray(mopeds);
        int n = mopeds.size();
        if(n==0) {
            System.out.println("Nessuna motorino disponibile");
            return;
        }
        Scanner scanner =new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona Motorino: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        RentVehicle(mopeds.get(x-1).getPlate(),1, scanner);
    }

    private void RentVehicle(String plate, int code, Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.println("\nInserisci il numero di giorni: ");
        int ndays = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci il metodo di pagamento (0-Contanti 1-Carta di Credito): ");
        int pm = scanner.nextInt();
        scanner.nextLine();

        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRental(myUser.getUsername(), plate, ndays, pm, code);
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.setAvailable(plate, false);
    }

    public Rental selectRental() throws SQLException, ClassNotFoundException {
        RentalDAO rentalDAO = new RentalDAO();
        ArrayList<Rental> rentals = rentalDAO.getUserRental(myUser);
        int i = 1;
        for(Rental rental:rentals){
            System.out.printf("%d) " + rental.getInfo() +"\n", i);
            i++;
        }
        int n = rentals.size();
        if(n==0){
            System.out.println("Nessuna prenotazione registrata.");
            return null;
        }
        Scanner scanner = new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona Prenotazione: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        return rentals.get(x-1);
    }

    public void resetPassword() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci la tua attuale password: ");
        String password = scanner.nextLine();
        if (!password.equals(myUser.getPassword()))
            return;
        System.out.println("Inserisci la nuova password: ");
        String newPassword = scanner.nextLine();
        userDAO.updatePassword(myUser.getUsername(), newPassword);
        myUser.setPassword(newPassword);
    }
}
