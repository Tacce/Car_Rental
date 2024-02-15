package BusinessLogic;

import DomainModel.Car;
import DomainModel.Moped;
import ORM.CarDAO;
import ORM.MopedDAO;

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
        scanner.close();

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
        scanner.close();

        MopedDAO mopedDAO = new MopedDAO();
        mopedDAO.insertMoped(plate, model, daily_price,displacement);
    }

    public void viewCars() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        ArrayList<Car> cars = carDAO.selectAllCars();
        int i = 1;
        for(Car car:cars){
           System.out.printf("%d) " + car.getInfo(), i);
           System.out.println(car.getAssistance().getInfo());
           i++;
        }
    }

    public void viewMopeds() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        ArrayList<Moped> mopeds = mopedDAO.selectAllMopeds();
        int i = 1;
        for(Moped moped:mopeds){
            System.out.printf("%d) " + moped.getInfo() +"\n", i);
            i++;
        }
    }
}
