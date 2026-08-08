package com.schoolproject.management.controllers;

import com.schoolproject.management.dtos.CreateStudentRequest;
import com.schoolproject.management.entities.Student;
import com.schoolproject.management.payload.APIResponse;
import com.schoolproject.management.payload.EntityListResponse;
import com.schoolproject.management.payload.EntityResponse;
import com.schoolproject.management.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    // /students/create -> (

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<String, Student>> createStudent(@RequestBody @Valid CreateStudentRequest studentRequest) {
        Map<String, Student> dataResponseBody = new HashMap<>();
        Student student = studentService.createStudent(studentRequest);
        dataResponseBody.put("student", student);

        int x = 10/0;

        HttpStatus status = HttpStatus.CREATED;
        APIResponse<String, Student> response = APIResponse.<String, Student>builder()
                .message("Created Successfully")
                .status(status)
                .success(true)
                .data(dataResponseBody)
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
