package com.schoolproject.management.repositories;

import com.schoolproject.management.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentJPARepo extends JpaRepository<Student, String> {
    Optional<Student> findByEmail(String email);
}
