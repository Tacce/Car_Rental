package ORM;

import DomainModel.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalDAO {
    public void insertRental(String username, String plate, int ndays, int pm, int code)
            throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("INSERT INTO rentals (user_username,vehicle_plate, ndays, payment_method_id, vehicle_type)" +
                "VALUES ('%s', '%s', '%d', '%d', '%d')", username, plate, ndays, pm, code);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Registrazione prenotazione riuscita");
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());}
    }

    public ArrayList<Rental> getUserRental(User user) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM rentals WHERE user_username = '%s'", user.getUsername());
        return getRentals(sql, user);
    }

    public ArrayList<Rental> getAllRentals() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM rentals";
        return getRentals(sql, null);
    }

    private ArrayList<Rental> getRentals(String sql, User user) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Rental> rentals = new ArrayList<>();
        while (rs.next()) {
            String plate = rs.getString("vehicle_plate");
            int ndays = rs.getInt("ndays");
            int pm = rs.getInt("payment_method_id");
            int vt = rs.getInt("vehicle_type");

            PaymentStrategy paymentStrategy;
            if(pm==0){
                paymentStrategy = new Cash();
            }else paymentStrategy = new CreditCard();

            Vehicle v;
            if(vt==0){
                CarDAO carDAO = new CarDAO();
                v = carDAO.getCar(plate);
            }else{
                MopedDAO mopedDAO = new MopedDAO();
                v = mopedDAO.getMoped(plate);
            }

            if(user == null){
                UserDAO userDAO = new UserDAO();
                user = userDAO.getUser(rs.getString("user_username"));
            }

            Rental rental = new Rental(user,v,ndays,paymentStrategy);
            rentals.add(rental);
        }
        ps.close();
        return rentals;
    }

    public void removeRental(String username, String plate) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("DELETE FROM rentals WHERE user_username='%s' AND vehicle_plate='%s'",
                username, plate);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Operazione riuscita");
        }catch (SQLException e){
            System.err.println("Errore durante la rimozione dal database: " + e.getMessage());
        }
    }

    public void updateNDays(int newNDays, String username, String plate) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("UPDATE rentals SET ndays = '%d' WHERE user_username='%s' AND vehicle_plate='%s'",
                newNDays, username, plate);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Aggiornamento riuscito");
        }catch (SQLException e){
            System.err.println("Errore durante l'aggiornamento dal database: " + e.getMessage());
        }
    }
}

