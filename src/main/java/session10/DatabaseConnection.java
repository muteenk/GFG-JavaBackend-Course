package session10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// POJOs - Plain old java object
public class DatabaseConnection {
    private String url = "";
    private String username = "";
    private String password = "";
    private Connection connection;

    public DatabaseConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("We're connected");
            }
        } catch (SQLException ex) {
            System.out.println("Connection Failed !");
            System.out.println(ex.getSQLState());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
