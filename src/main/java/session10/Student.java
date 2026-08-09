package session10;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private Integer grade;
    private Integer rollNo;
    private Integer age;

    public Student(Long id, String name, Integer grade, Integer rollNo, Integer age) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.rollNo = rollNo;
        this.age = age;
    }

    public static List<Student> rowMapper(ResultSet result) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (result.next()) {
            Student student = new Student(
                    result.getLong(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getInt(4),
                    result.getInt(5)
            );
            students.add(student);
        }

        return students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
