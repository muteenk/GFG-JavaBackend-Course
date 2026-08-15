package com.schoolproject.management.services.impl;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Address;
import com.schoolproject.management.entities.Student;
import com.schoolproject.management.repositories.StudentJPARepo;
import com.schoolproject.management.repositories.StudentRepository;
import com.schoolproject.management.services.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
//    private StudentJPARepo studentRepository;

    @Override
    @Transactional
    public Student createStudent(CreateStudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setAge(studentRequest.age());
        student.setGrade(studentRequest.grade());
        student.setRollNumber(studentRequest.rollNumber());
//        Address address = new Address(
//                studentRequest.address1(),
//                "",
//                studentRequest.state(),
//                studentRequest.city(),
//                studentRequest.pincode()
//        );
//        student.setCurrentAddress(address);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getOneById(String  id) {
        return studentRepository.findById(id);
    }
}
