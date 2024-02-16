package ORM;
import DomainModel.User;

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
            System.out.println("Registrazione effettuata");
        }catch (SQLException e){
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
        }
    }

    public User login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String passwordFromDatabase = rs.getString("password");
            if (password.equals(passwordFromDatabase)) {
                System.out.println("Login riuscito!");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String license = rs.getString("license");
                int age = rs.getInt("age");
                return new User(name, surname, username, password, license,age);
            } else {
                System.out.println("Username o password non validi.");
                return null;
            }
        } else {
            System.out.println("Username non trovato.");
            return null;
        }


    }
}
