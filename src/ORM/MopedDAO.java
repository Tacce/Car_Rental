package ORM;

import DomainModel.Car;
import DomainModel.Moped;
import DomainModel.RoadsideAssistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Moped> selectAllMopeds() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();

        String sql = "SELECT * FROM mopeds_view";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Moped> mopeds = new ArrayList<Moped>();

        while (rs.next()) {
            String plate = rs.getString("plate");
            String model = rs.getString("model");
            float dailyPrice = rs.getFloat("daily_price");
            boolean available = rs.getBoolean("available");
            int displacement = rs.getInt("displacement");

            Moped moped = new Moped(model, plate, dailyPrice, available, displacement);
            mopeds.add(moped);
        }
        ps.close();
        return mopeds;
    }
}
