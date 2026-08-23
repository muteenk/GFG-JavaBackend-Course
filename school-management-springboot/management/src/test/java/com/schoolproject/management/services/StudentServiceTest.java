package com.schoolproject.management.services;

import com.schoolproject.management.entities.Student;
import static org.junit.jupiter.api.Assertions.*;

import com.schoolproject.management.repositories.StudentRepository;
import com.schoolproject.management.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService = new StudentServiceImpl();

    @Test
    void getOneByIdTest() {
        // arrange
        Student fakeStudent = Student.builder()
                .id("ljfsdfhskdhflkahdlfhladjhf")
                .age(17)
                .CGPA(BigDecimal.valueOf(8.6))
                .rollNumber(12)
                .name("Amit")
                .isMonitor(false)
                .build();

        // act
        when(studentRepository.findById(anyString())).thenReturn(fakeStudent);

        Student student = studentService.getOneById("4444e006-9074-4cac-8c43-5603b91c3d70");

        // assertion
        assertEquals("Amit", student.getName());

        verify(studentRepository).findById(anyString());
    }

}
