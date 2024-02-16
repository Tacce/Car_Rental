package ORM;

import DomainModel.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentalDAO {

    public void addRental(String username, String plate, int ndays, int pm) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO rentals (user_username,vehicle_plate, ndays, payment_method_id)" +
                "VALUES ('%s', '%s', '%d', '%d')", username, plate, ndays, pm);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Registrazione prenotazione riuscita");
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());}
    }
}

