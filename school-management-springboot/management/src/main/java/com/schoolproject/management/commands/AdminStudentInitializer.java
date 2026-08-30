package com.schoolproject.management.commands;

import com.schoolproject.management.entities.Student;
import com.schoolproject.management.entities.StudentRole;
import com.schoolproject.management.entities.StudentStatus;
import com.schoolproject.management.repositories.StudentJPARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AdminStudentInitializer {

    @Autowired
    private StudentJPARepo studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminStudent() {
        return args -> {
            if (studentRepository.findByEmail("admin@gmail.com").isEmpty()){
                Student student = Student.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("user123"))
                        .name("admin")
                        .role(StudentRole.ROLE_ADMIN)
                        .grade(12)
                        .rollNumber(2)
                        .age(17)
                        .status(StudentStatus.ONLINE)
                        .isMonitor(true)
                        .CGPA(BigDecimal.valueOf(8.1))
                        .build();

                studentRepository.save(student);
                System.out.println("Base User Created !");
            }
        };
    }

}
