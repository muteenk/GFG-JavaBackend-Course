package com.schoolproject.management.repositories;

import com.schoolproject.management.entities.Student;

import java.util.List;

public interface StudentRepository {
    Student saveStudent(Student student);
    List<Student> findAll();
}
