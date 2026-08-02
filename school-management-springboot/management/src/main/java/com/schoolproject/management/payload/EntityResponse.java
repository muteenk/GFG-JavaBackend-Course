package com.schoolproject.management.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityResponse<T> {
    private String message;
    private HttpStatus status;
    private boolean success;
    private T data;
}
