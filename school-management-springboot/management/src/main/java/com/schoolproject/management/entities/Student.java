package com.schoolproject.management.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Integer grade;  // the class that the student is studying in
    private Integer rollNumber;
    private Integer age;
}
