package com.schoolproject.management.repositories.impl;

import com.schoolproject.management.entities.Student;
import com.schoolproject.management.repositories.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private Map<Integer, Student> studentMap = new HashMap<>();

    @Override
    public Student saveStudent(Student student) {
        Integer id = studentMap.size() + 1;
        student.setId(id);
        studentMap.put(id, student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Integer, Student> entry: studentMap.entrySet()){
            studentList.add(entry.getValue());
        }
        return studentList;
    }
}
