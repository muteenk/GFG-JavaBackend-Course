package com.schoolproject.management.services.impl;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Student;
import com.schoolproject.management.repositories.StudentRepository;
import com.schoolproject.management.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(CreateStudentRequest studentRequest) {

        System.out.println("Before creating a student: WE have reached here"); // point cut

        Student student = new Student();
        student.setName(studentRequest.name());
        student.setAge(studentRequest.age());
        student.setGrade(studentRequest.grade());
        student.setRollNumber(studentRequest.rollNumber());

        // here

        return studentRepository.saveStudent(student);

        // here
    }

    @Override
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }
}
