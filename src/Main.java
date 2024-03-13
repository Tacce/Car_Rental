import BusinessLogic.*;
import DomainModel.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        LoginController lc = new LoginController();
        String x;
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Registrati
                    1 Effettua login come utente
                    2 Effettua login come admin
                    -1 ESCI""");
            x = scanner.nextLine();
            if(x.equals("0")){
                lc.register();
            }else if(x.equals("1")) {
                User user = lc.login();
                if(user != null){
                    handleUserAction(user);
                }
            }else if(x.equals("2") && lc.adminLogin()) {
                System.out.println("ADMIN entrato");
                handleAdminAction();
            }else if(x.equals("-1")){
                break;
            }else {
                System.out.println("Scelta non valida");
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
                    uc.viewAvailableVehicles();
                    break;
                case "1":
                    uc.RentCar();
                    break;
                case "2":
                    uc.RentMoped();
                    break;
                case "3":
                    Rental rental = uc.selectRental();
                    if(rental != null){
                        handleRentalActions(rental);
                    }
                    break;
                case "4":
                    uc.resetPassword();
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
                    rc.modifyNDays();
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
                    ac.viewAllVehicles();
                    break;
                case "1":
                    ac.addCar();
                    break;
                case "2":
                    ac.addMoped();
                    break;
                case "3":
                    ac.removeCar();
                    break;
                case "4":
                    ac.removeMoped();
                    break;
                case "5":
                    ac.viewAllUsers();
                    break;
                case "6":
                    ac.viewAllRentals();
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