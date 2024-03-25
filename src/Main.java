import BusinessLogic.*;
import DomainModel.*;
import ORM.MopedDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        LoginController lc = new LoginController();
        String x;
        label:
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Registrati
                    1 Effettua login come utente
                    2 Effettua login come admin
                    -1 ESCI""");
            x = scanner.nextLine();

            switch (x) {
                case "0": {

                    System.out.println("\nREGISTRAZIONE");
                    System.out.println("Inserisci il tuo nome: ");
                    String name = scanner.nextLine();
                    System.out.println("Inserisci il tuo cognome: ");
                    String surname = scanner.nextLine();
                    System.out.println("Inserisci la tua età: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il codice della tua patente: ");
                    String license = scanner.nextLine();
                    System.out.println("Inserisci il tuo username: ");
                    String username = scanner.nextLine();
                    System.out.println("Inserisci la tua password: ");
                    String password = scanner.nextLine();
                    lc.register(name, surname, age, license, username, password);
                    break;
                }
                case "1": {

                    System.out.println("\nUsername: ");
                    String username = scanner.nextLine();
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    User user = lc.login(username, password);
                    if (user != null) {
                        handleUserAction(user);
                    }
                    break;
                }
                case "2": {

                    System.out.println("\nPassword Admin: ");
                    String password = scanner.nextLine();
                    if (lc.adminLogin(password)) {
                        System.out.println("ADMIN entrato");
                        handleAdminAction();
                    }
                    break;
                }
                case "-1":
                    break label;

                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }while(true);

    }

    private static void handleUserAction(User user) throws SQLException, ClassNotFoundException {
        UserController uc = new UserController(user);
        Scanner scanner = new Scanner(System.in);
        String x;
        label:
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Visualizza veicoli disponibili
                    1 Noleggia Auto
                    2 Noleggia Motorino
                    3 Gestisci Prenotazioni
                    4 Modifica Password
                    -1 ESCI""");
            x = scanner.nextLine();
            switch (x) {
                case "0":
                    //uc.viewAvailableVehicles();
                    ArrayList<Vehicle> carsView = uc.viewAvailableCars();
                    System.out.println("\nAUTOMOBILI\n");
                    if (carsView.size()==0){
                        System.out.println("Nessun'auto disponibile");
                    }else
                        Vehicle.printVehicleArray(carsView);
                    ArrayList<Vehicle> mopedsView = uc.viewAvailableMopeds();
                    System.out.println("\nMOTORINI\n");
                    if (mopedsView.size()==0){
                        System.out.println("Nessun motorino disponibile");
                    }else
                        Vehicle.printVehicleArray(mopedsView);
                    break;

                case "1":

                    ArrayList<Vehicle> cars = uc.viewAvailableCars();
                    Vehicle.printVehicleArray(cars);
                    int n1 = cars.size();
                    if(n1==0) {
                        System.out.println("Nessun'auto disponibile");
                        break;
                    }
                    int i;
                    do{
                        System.out.println("Seleziona auto: ");
                        i = scanner.nextInt();
                        scanner.nextLine();
                    }while (i<=0 || i>n1);
                    System.out.println("\nInserisci il numero di giorni: ");
                    int ndaysCar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il metodo di pagamento (0-Contanti 1-Carta di Credito): ");
                    int pmCar = scanner.nextInt();
                    scanner.nextLine();
                    uc.rentCar(cars.get(i-1).getPlate(), ndaysCar, pmCar);
                    break;

                case "2":

                    ArrayList<Vehicle> mopeds = uc.viewAvailableMopeds();
                    Vehicle.printVehicleArray(mopeds);
                    int n2 = mopeds.size();
                    if(n2==0) {
                        System.out.println("Nessuna motorino disponibile");
                        break;
                    }
                    int j;
                    do{
                        System.out.println("Seleziona Motorino: ");
                        j = scanner.nextInt();
                        scanner.nextLine();
                    }while (j<=0 || j>n2);
                    System.out.println("\nInserisci il numero di giorni: ");
                    int ndaysMoped = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il metodo di pagamento (0-Contanti 1-Carta di Credito): ");
                    int pmMoped = scanner.nextInt();
                    scanner.nextLine();
                    uc.rentMoped(mopeds.get(j-1).getPlate(), ndaysMoped, pmMoped);
                    break;

                case "3":
                    ArrayList<Rental> rentals = uc.viewAllRentals();
                    int l = 1;
                    for(Rental rental:rentals){
                        System.out.printf("%d) " + rental.getInfo() +"\n", l);
                        l++;
                    }
                    int n = rentals.size();
                    if(n==0){
                        System.out.println("Nessuna prenotazione registrata.");
                        break;
                    }
                    int k;
                    do{
                        System.out.println("Seleziona Prenotazione: ");
                        k = scanner.nextInt();
                        scanner.nextLine();
                    }while (k<=0 || k>n);
                    handleRentalActions(rentals.get(k-1));
                    break;

                case "4":

                    System.out.println("Inserisci la tua attuale password: ");
                    String password = scanner.nextLine();
                    System.out.println("Inserisci la nuova password: ");
                    String newPassword = scanner.nextLine();
                    uc.resetPassword(password, newPassword);
                    break;

                case "-1":
                    break label;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }while(true);
    }

    private static void handleRentalActions(Rental rental) throws SQLException, ClassNotFoundException {
        RentalController rc = new RentalController(rental);
        Scanner scanner = new Scanner(System.in);
        String x;
        label:
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Mostra Dettagli Veicolo
                    1 Restituisci il Veicolo e Paga
                    2 Cancella Prenotazione
                    3 Modifica Numero Giorni di Noleggio
                    -1 ESCI""");
            x = scanner.nextLine();
            switch (x) {
                case "0":
                    System.out.println(rental.getVehicle().getInfo());
                    break;
                case "1":
                    rc.returnVehicle();
                    break label;
                case "2":
                    rc.cancelRental();
                    break label;
                case "3":

                    System.out.println("\nInserisci nuovo numero di giorni: ");
                    int newNdays = scanner.nextInt();
                    scanner.nextLine();
                    rc.modifyNDays(newNdays);
                    break;

                case "-1":
                    break label;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }while(true);
    }

    private static void handleAdminAction() throws SQLException, ClassNotFoundException {
        AdminController ac = new AdminController();
        Scanner scanner = new Scanner(System.in);
        String x;
        label:
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Visualizza lista veicoli
                    1 Aggiungi Auto
                    2 Aggiungi Motorino
                    3 Rimuovi Auto
                    4 Rimuovi Motorino
                    5 Visualizza Dati Utenti
                    6 Visualizza Noleggi
                    -1 ESCI""");
            x = scanner.nextLine();

            switch (x) {
                case "0":
                    //ac.viewAllVehicles();
                    ArrayList<Vehicle> carsView = ac.viewAllCars();
                    System.out.println("\nAUTOMOBILI\n");
                    if (carsView.size()==0){
                        System.out.println("Nessun'auto registrata");
                    }else
                        Vehicle.printVehicleArray(carsView);
                    ArrayList<Vehicle> mopedsView = ac.viewAllMopeds();
                    System.out.println("\nMOTORINI\n");
                    if (mopedsView.size()==0){
                        System.out.println("Nessun motorino registrato");
                    }else
                        Vehicle.printVehicleArray(mopedsView);
                    break;

                case "1":

                    System.out.println("\nInserisci la targa: ");
                    String carPlate = scanner.nextLine();
                    System.out.println("Inserisci il modello: ");
                    String carModel = scanner.nextLine();
                    System.out.println("Inserisci il prezzo giornaliero: ");
                    float dailyPriceCar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il numero di posti: ");
                    int nseats = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci assicurazione (0 per default): ");
                    int assintanceId = scanner.nextInt();
                    scanner.nextLine();
                    ac.addCar(carPlate, carModel, dailyPriceCar, nseats, assintanceId);
                    break;

                case "2":

                    System.out.println("\nInserisci la targa: ");
                    String mopedPlate = scanner.nextLine();
                    System.out.println("Inserisci il modello: ");
                    String mopedModel = scanner.nextLine();
                    System.out.println("Inserisci il prezzo giornaliero: ");
                    float dailyPriceMoped = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci cilindrata: ");
                    int displacement = scanner.nextInt();
                    scanner.nextLine();
                    ac.addMoped(mopedPlate, mopedModel, dailyPriceMoped, displacement);
                    break;

                case "3":

                    ArrayList<Vehicle> cars = ac.viewAllCars();
                    int n1 = cars.size();
                    if(n1==0) {
                        System.out.println("Nessun'auto registrata");
                        break;
                    }
                    Vehicle.printVehicleArray(cars);
                    int i;
                    do{
                        System.out.println("Seleziona auto: ");
                        i = scanner.nextInt();
                        scanner.nextLine();
                    }while (i<=0 || i>n1);
                    ac.removeCar(cars.get(i-1).getPlate());
                    break;

                case "4":

                    ArrayList<Vehicle> mopeds = ac.viewAllMopeds();
                    int n2 = mopeds.size();
                    if(n2==0){
                        System.out.println("Nessun motorino registrato");
                        break;
                    }
                    Vehicle.printVehicleArray(mopeds);
                    int j;
                    do{
                        System.out.println("\nSeleziona auto: ");
                        j = scanner.nextInt();
                        scanner.nextLine();
                    }while (j<=0 || j>n2);
                    ac.removeMoped(mopeds.get(j-1).getPlate());
                    break;

                case "5":

                    ArrayList<User> users = ac.viewAllUsers();;
                    if(users.size()==0){
                        System.out.println("Nessun utente registrato");
                        return;
                    }
                    int u = 1;
                    System.out.println("\nUTENTI\n");
                    for(User user:users){
                        System.out.printf("%d) " + user.getInfo() +"\n", u);
                        u++;
                    }
                    break;

                case "6":

                    ArrayList<Rental> rentals = ac.viewAllRentals();;
                    if(rentals.size()==0){
                        System.out.println("Nessuna prenotazione registrata");
                        return;
                    }
                    int r = 1;
                    for(Rental rental:rentals){
                        System.out.printf("%d) " + rental.getInfo() +"\n", r);
                        r++;
                    }
                    break;

                case "-1":
                    break label;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }while(true);
    }

}