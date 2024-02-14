package BusinessLogic;

import DomainModel.Car;
import ORM.CarDAO;
import ORM.MopedDAO;

import java.sql.SQLException;
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
}
