package ORM;

import DomainModel.Car;
import DomainModel.RoadsideAssistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Car> selectAllCars() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();

        String sql = "SELECT * FROM cars_view join assistance on id=assistance_id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Car> cars = new ArrayList<Car>();
        while (rs.next()) {
            String plate = rs.getString("plate");
            int nseats = rs.getInt("nseats");
            String model = rs.getString("model");
            float dailyPrice = rs.getFloat("daily_price");
            boolean available = rs.getBoolean("available");
            String name = rs.getString("name");
            String phone = rs.getString("phone");

            RoadsideAssistance ra = new RoadsideAssistance(name, phone);
            Car car = new Car(model, plate, dailyPrice, available, nseats, ra);
            cars.add(car);
        }
        ps.close();
        return cars;
    }

}
