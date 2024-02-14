package ORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MopedDAO {
    public void insertMoped(String plate, String model, float dailyPrice, int displacement) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO mopeds_view (plate, model, daily_price, displacement, available)" +
                "VALUES ('%s','%s','%s','%d',true)",plate, model, String.valueOf(dailyPrice), displacement);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Inserimento motorino riuscito");
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
        }
    }
}
