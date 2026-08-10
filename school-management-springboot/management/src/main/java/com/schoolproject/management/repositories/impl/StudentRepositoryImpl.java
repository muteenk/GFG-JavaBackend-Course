package com.schoolproject.management.repositories.impl;

import com.schoolproject.management.entities.Student;
import com.schoolproject.management.mappers.StudentMapper;
import com.schoolproject.management.repositories.StudentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private StudentMapper studentMapper = new StudentMapper();

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student saveStudent(Student student) {
        String sql = """
                INSERT INTO student(name, grade, roll_no, age) VALUES(?, ?, ?, ?);
                """;
        jdbcTemplate.update(
                sql,
                student.getName(),
                student.getGrade(),
                student.getRollNumber(),
                student.getAge()
        );
        return student;
    }

    @Override
    public List<Student> findAll() {
        String sql = """
                SELECT * FROM student LIMIT 50;
                """;
        return jdbcTemplate.query(sql, studentMapper);
    }

    @Override
    public Student findById(Integer id) {
        String sql = """
                SELECT * FROM student WHERE id = ?;
                """;
        return jdbcTemplate.queryForObject(sql, studentMapper, id);
    }
}
