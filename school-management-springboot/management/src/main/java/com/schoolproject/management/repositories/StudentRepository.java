package com.schoolproject.management.repositories;

import com.schoolproject.management.entities.Student;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    Student saveStudent(Student student);
    List<Student> findAll();
    Student findById(Integer id);
}
