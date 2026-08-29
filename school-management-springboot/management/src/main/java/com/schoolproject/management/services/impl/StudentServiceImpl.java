package com.schoolproject.management.services.impl;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Address;
import com.schoolproject.management.entities.Student;
import com.schoolproject.management.repositories.StudentJPARepo;
import com.schoolproject.management.repositories.StudentRepository;
import com.schoolproject.management.services.StudentService;
import com.schoolproject.management.services.utilities.RedisServiceUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisServiceUtility redisServiceUtility;

    @Override
    @Transactional
    @CacheEvict(value = "students", allEntries = true)
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
//        redisServiceUtility.remove("student-list");  // CacheEviction
        return studentRepository.save(student);
    }

    @Override
    @Cacheable(value = "students")
    public List<Student> listStudents() {
//        String cacheKey = "student-list";
//        List<Student> cachedStudents = redisServiceUtility.get(
//                cacheKey, new TypeReference<List<Student>>() {}
//        );
//        if (cachedStudents != null) return cachedStudents;
        List<Student> students =  studentRepository.findAll();
//        redisServiceUtility.set(cacheKey, students, 300L);
        return students;
    }

    @Override
    @Cacheable(value = "students", key = "#result.id")
    public Student getOneById(String id) {
//        String cacheKey = "user:"+id;
//        Student cachedStudent = redisServiceUtility.get(cacheKey, Student.class);
//        if (cachedStudent != null) return cachedStudent;
        Student student = studentRepository.findById(id);
//        if (student != null) redisServiceUtility.set(cacheKey, student);
        return student;
    }
}
