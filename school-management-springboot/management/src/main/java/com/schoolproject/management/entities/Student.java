package com.schoolproject.management.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Integer grade;  // the class that the student is studying in
    private Integer rollNumber;
}
