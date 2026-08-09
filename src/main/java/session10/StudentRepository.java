package session10;

import java.sql.*;
import java.util.List;

public class StudentRepository {
    private String url = "";
    private String username = "";
    private String password = "";

    public StudentRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void createUser(String name, int grade, int rollNo, int age) {
        String sql = "INSERT INTO student(name, grade, roll_no, age) VALUES(?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setInt(2, grade);
            statement.setInt(3, rollNo);
            statement.setInt(4, age);

            boolean data = statement.execute(sql);
            if (data) {
                ResultSet result = statement.getResultSet();
            } else {
                System.out.println("ROWS AFFECTED: " + statement.getUpdateCount());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllUsers(){
        String sql = "SELECT * FROM student;";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql);) {
            List<Student> studs = Student.rowMapper(result);
            return studs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
