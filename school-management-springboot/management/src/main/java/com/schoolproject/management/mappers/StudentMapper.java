package com.schoolproject.management.mappers;

import com.schoolproject.management.entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setGrade(rs.getInt("grade"));
        student.setAge(rs.getInt("age"));
        student.setRollNumber(rs.getInt("roll_no"));
        return student;
    }
}
