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
import java.util.Scanner;

public class AdminController {

    public void addCar() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInserisci la targa: ");
        String plate = scanner.nextLine();
        System.out.println("Inserisci il modello: ");
        String model = scanner.nextLine();
        System.out.println("Inserisci il prezzo giornaliero: ");
        float daily_price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci il numero di posti: ");
        int nseats = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci assicurazione (0 per default): ");
        int assintance_id = scanner.nextInt();
        scanner.nextLine();

        CarDAO carDAO = new CarDAO();
        carDAO.insertCar(plate, model, daily_price, nseats, assintance_id);
    }

    public void addMoped() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInserisci la targa: ");
        String plate = scanner.nextLine();
        System.out.println("Inserisci il modello: ");
        String model = scanner.nextLine();
        System.out.println("Inserisci il prezzo giornaliero: ");
        float daily_price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci cilindrata: ");
        int displacement = scanner.nextInt();
        scanner.nextLine();

        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped(plate, model, daily_price,displacement);
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


    public void removeCar() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAllCars();
        int n = cars.size();
        if(n==0){
            System.out.println("Nessun'auto registrata");
            return;
        }
        Vehicle.printVehicleArray(cars);
        Scanner scanner = new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona auto: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        carDAO.removeCar(cars.get(x-1).getPlate());
    }

    public void removeMoped() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAllMopeds();
        int n = mopeds.size();
        if(n==0){
            System.out.println("Nessun motorino registrato");
            return;
        }
        Vehicle.printVehicleArray(mopeds);
        Scanner scanner = new Scanner(System.in);
        int x;
        do{
            System.out.println("\nSeleziona auto: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        mopedDAO.removeMoped(mopeds.get(x-1).getPlate());
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
