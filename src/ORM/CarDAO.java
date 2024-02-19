package ORM;

import DomainModel.Car;
import DomainModel.RoadsideAssistance;
import DomainModel.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAO extends VehicleDAO{

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

    public ArrayList<Vehicle> selectAllCars() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM cars_view join assistance on id=assistance_id";
        return selectCars(sql);
    }

    public ArrayList<Vehicle> selectAvailableCars() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM cars_view join assistance on id=assistance_id WHERE available";
        return selectCars(sql);
    }

    private ArrayList<Vehicle> selectCars(String sql) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
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

    public void removeCar(String plate) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM cars_view WHERE plate = '%s'",plate);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Rimozione auto riuscita");
        }catch (SQLException e){
            System.err.println("Errore durante la rimozione dal database: " + e.getMessage());
        }
    }

    public Car getCar(String plate) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM cars_view join assistance on id=assistance_id WHERE plate='%s'", plate);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Car car = null;
        if (rs.next()) {
            int nseats = rs.getInt("nseats");
            String model = rs.getString("model");
            float dailyPrice = rs.getFloat("daily_price");
            boolean available = rs.getBoolean("available");
            String name = rs.getString("name");
            String phone = rs.getString("phone");

            RoadsideAssistance ra = new RoadsideAssistance(name, phone);
            car = new Car(model, plate, dailyPrice, available, nseats, ra);
        }
        return car;
    }
}
