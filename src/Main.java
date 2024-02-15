import BusinessLogic.*;
import ORM.CarDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //TEST

        AdminController ac = new AdminController();
        ac.viewMopeds();


        /*LoginController uc = new LoginController();
        Scanner scanner = new Scanner(System.in);
        int x;
        do {
            System.out.println("Digita 0 per registrarti o 1 per effettuare il login: ");
            x = scanner.nextInt();
            scanner.nextLine();
            if(x==0){
                uc.register();
            }else if(x==1){
                uc.login();
            }else {
                System.out.println("Scelta non valida");
            }
        }while(x!=0 && x!=1);*/
    }
}
