package com.schoolproject.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.UUID) // auto generate uuid
    private String id;

    @Column(
            nullable = false
    )
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
