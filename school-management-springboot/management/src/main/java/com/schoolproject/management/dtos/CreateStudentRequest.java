package com.schoolproject.management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// Data transferable object

public record CreateStudentRequest(
    @NotBlank
    String name,

    @NotNull
    @Positive(message = "age should not be less than 1")
    Integer age,

    @Positive
    @NotNull
    Integer grade,

    @Positive
    @NotNull
    Integer rollNumber
) { }
