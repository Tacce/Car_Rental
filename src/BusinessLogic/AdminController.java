package BusinessLogic;

import DomainModel.Car;
import DomainModel.Moped;
import DomainModel.User;
import DomainModel.Vehicle;
import ORM.CarDAO;
import ORM.MopedDAO;
import ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminController {

    public void addCar() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci la targa: ");
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
        System.out.println("Inserisci la targa: ");
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
        System.out.println("AUTOMOBILI\n");
        Vehicle.printVehicleArray(cars);
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Vehicle> mopeds = mopedDAO.selectAllMopeds();
        System.out.println("\nMOTORINI\n");
        Vehicle.printVehicleArray(mopeds);
    }

    public void removeCar() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Vehicle> cars = carDAO.selectAllCars();
        Vehicle.printVehicleArray(cars);
        int n = cars.size();
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
        Vehicle.printVehicleArray(mopeds);
        int n = mopeds.size();
        Scanner scanner = new Scanner(System.in);
        int x;
        do{
            System.out.println("Seleziona auto: ");
            x = scanner.nextInt();
            scanner.nextLine();
        }while (x<0 || x>n);

        mopedDAO.removeMoped(mopeds.get(x-1).getPlate());
    }

    public void viewAllUsers() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.selectAllUsers();
        int i = 1;
        System.out.println("\nUTENTI\n");
        for(User user:users){
            System.out.printf("%d) " + user.getInfo() +"\n", i);
            i++;
        }
    }
}
