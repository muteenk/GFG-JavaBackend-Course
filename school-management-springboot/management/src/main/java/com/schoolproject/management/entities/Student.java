package com.schoolproject.management.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.UUID) // auto generate uuid
    private String id;

    @Column(
            nullable = false
    )
    private String name;

    private Integer grade;  // the class that the student is studying in

    private Integer rollNumber;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private StudentStatus status;

    @Convert(converter = StudentMonitorConverter.class)
    private Boolean isMonitor; // Yes or No

    @Column(
            name="created_at",
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(precision = 5, scale = 2)
    private BigDecimal CGPA;

    @OneToMany(mappedBy = "student")
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "student_course",
            joinColumns =
            @JoinColumn(name = "student_id"),
            inverseJoinColumns =
            @JoinColumn(name = "course_id"),
            uniqueConstraints =
            @UniqueConstraint(
                    columnNames = {
                            "student_id",
                            "course_id"
                    }
            )
    )
    private Set<Course> courses = new HashSet<>();
}