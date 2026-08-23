package com.schoolproject.management.controllers;


import com.schoolproject.management.entities.Student;
import com.schoolproject.management.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;

@WebMvcTest   // IoC Container startup
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void getStudentByIdTest() throws Exception {
        // arrange
        Student student = Student.builder()
                .id("4444e006-9074-4cac-8c43-5603b91c3d70")
                .age(17)
                .CGPA(BigDecimal.valueOf(8.6))
                .rollNumber(12)
                .name("Amit")
                .isMonitor(false)
                .build();

        // act
        when(studentService.getOneById(anyString())).thenReturn(student);

        // act + assert
        mockMvc.perform(get("/students/4444e006-9074-4cac-8c43-5603b91c3d70")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$.message").value("Student fetched successfully"));

        verify(studentService).getOneById(anyString());
        verify(studentService, never()).listStudents();
    }

}
