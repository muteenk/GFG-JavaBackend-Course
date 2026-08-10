package com.schoolproject.management.services;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(CreateStudentRequest studentRequest);

    List<Student> listStudents();

    Student getOneById(Integer id);
}
