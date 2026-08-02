package com.schoolproject.management.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Integer id;
    private Integer studentId;
    private Float mathScore;
    private Float englishScore;
    private Float scienceScore;
}
