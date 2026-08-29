package com.schoolproject.management.repositories.impl;

import com.schoolproject.management.entities.Student;
import com.schoolproject.management.mappers.StudentMapper;
import com.schoolproject.management.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

//    @Override
    public List<Student> findAll() {
        String jpql = "SELECT u FROM Student u";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        return query.getResultList();
    }


    @Override
    public Student findById(String id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student updateStudent(Student student, String id) {
        Student student1 = this.findById(id);
        student1.setName(student.getName());
        student1.setGrade(student.getGrade());
        return student1;
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(student);
    }
}
