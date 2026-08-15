package com.schoolproject.management.repositories;

import com.schoolproject.management.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJPARepo extends JpaRepository<Student, String> {
}
