package ORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDAO {

    public void insertCar(String plate, String model, float dailyPrice, int nseats, int assintanceId)
            throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO cars_view (plate, nseats, assistance_id, model, daily_price, available)" +
                "VALUES ('%s', '%d', '%d', '%s', %s, true)",plate, nseats, assintanceId, model, String.valueOf(dailyPrice));
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Inserimento auto riuscito");
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
        }
    }
}
