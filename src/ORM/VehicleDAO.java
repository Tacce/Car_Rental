package ORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleDAO {
    public void setAvailable(String plate, boolean av) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("UPDATE Vehicles SET available = %b WHERE plate = '%s'",av, plate);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            System.err.println("Errore durante l'aggiornamento del database: " + e.getMessage());
        }
    }
}
