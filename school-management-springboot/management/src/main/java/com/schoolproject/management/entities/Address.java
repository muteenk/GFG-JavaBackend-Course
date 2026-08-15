package com.schoolproject.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(
            nullable = false
    )
    private String address1;

    private String address2;

    @Column(
            nullable = false
    )
    private String state;

    @Column(
            nullable = false
    )
    private String city;

    @Column(
            nullable = false
    )
    private String pincode;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;
}
