import BusinessLogic.*;
import DomainModel.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //TEST
        Scanner scanner = new Scanner(System.in);
        LoginController lc = new LoginController();
        int x;
        do {
            System.out.println("""
                    COMANDI:
                    0 Registrati
                    1 Effettua login come utente
                    2 Effettua login come admin
                    -1 ESCI""");
            x = scanner.nextInt();
            scanner.nextLine();
            if(x == 0){
                lc.register();
            }else if(x == 1) {
                User user = lc.login();
                if(user != null){
                    handleUserAction(user);
                }
            }else if(x == 2 && lc.adminLogin()) {
                System.out.println("ADMIN entrato");
                handleAdminAction();
            }else if(x == -1){
                break;
            }else {
                System.out.println("Scelta non valida");
            }
        }while(true);

    }

    private static void handleUserAction(User user) throws SQLException, ClassNotFoundException {
        UserController uc = new UserController(user);
        Scanner scanner = new Scanner(System.in);
        int x;
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Visualizza veicoli disponibili
                    1 Noleggia Auto
                    2 Noleggia Motorino
                    -1 ESCI""");
            x = scanner.nextInt();
            scanner.nextLine();
            if(x == 0){
                uc.viewAvailableVehicles();
            }else if(x == 1) {
                uc.RentCar();
            }else if(x == 2) {
                uc.RentMoped();
            }else if(x == -1){
                break;
            }else {
                System.out.println("Scelta non valida");
            }
        }while(true);
    }

    private static void handleAdminAction() throws SQLException, ClassNotFoundException {
        AdminController ac = new AdminController();
        Scanner scanner = new Scanner(System.in);
        int x;
        do {
            System.out.println("""
                    \nCOMANDI:
                    0 Visualizza lista veicoli
                    1 Aggiungi Auto
                    2 Aggiungi Motorino
                    -1 ESCI""");
            x = scanner.nextInt();
            scanner.nextLine();
            if(x == 0){
                ac.viewAllVehicles();
            }else if(x == 1) {
                ac.addCar();
            }else if(x == 2){
                ac.addCar();
            }else if(x == -1){
                break;
            }else {
                System.out.println("Scelta non valida");
            }
        }while(true);
    }

}
