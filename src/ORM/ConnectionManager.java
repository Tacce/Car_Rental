package ORM;
import java.sql.*;

public class ConnectionManager {

    private static final String url = "jdbc:postgresql://localhost:5432/rental_db";
    private static final String username = "rental_user";
    private static final String password = "swe2024";
    private static Connection con = null;

    public ConnectionManager(){}

    static public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        if (con == null)
            con = DriverManager.getConnection(url, username, password);
        return con;
    }
}