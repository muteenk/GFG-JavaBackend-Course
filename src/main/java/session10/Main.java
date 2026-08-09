package session10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    static void main() {
        // <Drivername>://<Host>:<Port>/<DatabaseName>
        String url = "jdbc:mysql://127.0.0.1:3306/gfg_practice";
        String username = "root";
        String password = "blackhat";

        StudentRepository studRepo = new StudentRepository(url, username, password);

//        studRepo.createUser("rohan", 2, 21, 8);
        List<Student> studs = studRepo.getAllUsers();
        for (Student student : studs) {
            System.out.println(student.getName());
        }
    }
}
