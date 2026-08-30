package com.schoolproject.management.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student implements UserDetails {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.UUID) // auto generate uuid
    private String id;

    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;

    @Enumerated(value = EnumType.STRING)
    private StudentRole role = StudentRole.ROLE_USER;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (this.role != null) ? List.of(new SimpleGrantedAuthority(role.name())) : List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}