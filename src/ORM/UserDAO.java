package ORM;
import java.sql.*;

public class UserDAO {

    public void register(String name, String surname, int age, String license, String username, String password)
            throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO users (username, password, name, surname, age, license) VALUES "+
                        "('%s', '%s', '%s', '%s', %d, '%s')", username, password, name, surname, age, license);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
        }
    }

    public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("SELECT password FROM users WHERE username = '%s'", username);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String passwordFromDatabase = rs.getString("password");
            if (password.equals(passwordFromDatabase)) {
                System.out.println("Login riuscito!");
                return true;
            } else {
                System.out.println("Username o password non validi.");
                return false;
            }
        } else {
            System.out.println("Username non trovato.");
            return false;
        }


    }
}
