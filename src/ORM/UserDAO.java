package ORM;
import DomainModel.*;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    public void addUser(String name, String surname, int age, String license, String username, String password)
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

    public User checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
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

    public ArrayList<User> selectAllUsers() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<User> users = new ArrayList<User>();
        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String license = rs.getString("license");
            int age = rs.getInt("age");

            User user = new User(name, surname, username, password, license, age);
            users.add(user);
        }
        ps.close();
        return users;
    }

    public User getUser(String username) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String password = rs.getString("password");
            String license = rs.getString("license");
            int age = rs.getInt("age");

            user = new User(name, surname, username, password, license, age);
        }
        return user;
    }

    public void removeUser(String username) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM users WHERE username = '%s'", username);
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Rimozione utente riuscita");
        }catch (SQLException e){
            System.err.println("Errore durante la rimozione dal database: " + e.getMessage());
        }
    }
}
