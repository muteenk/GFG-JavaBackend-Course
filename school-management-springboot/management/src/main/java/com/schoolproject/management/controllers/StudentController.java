package com.schoolproject.management.controllers;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Student;
import com.schoolproject.management.payload.EntityListResponse;
import com.schoolproject.management.payload.EntityResponse;
import com.schoolproject.management.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<EntityResponse<Student>> createStudent(@RequestBody @Valid CreateStudentRequest studentRequest) {
        Student student = studentService.createStudent(studentRequest);

        HttpStatus status = HttpStatus.CREATED;
        EntityResponse<Student> response = EntityResponse.<Student>builder()
                .message("Created Successfully")
                .status(status)
                .success(true)
                .data(student)
                .build();
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/list")
    public ResponseEntity<EntityListResponse<Student>> listStudent() {
        List<Student> student = studentService.listStudents();

        HttpStatus status = HttpStatus.OK;
        EntityListResponse<Student> response = EntityListResponse.<Student>builder()
                .message("List fetched")
                .status(status)
                .success(true)
                .data(student)
                .build();
        return new ResponseEntity<>(response, status);
    }

}
