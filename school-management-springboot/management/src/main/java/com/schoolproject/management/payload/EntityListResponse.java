package com.schoolproject.management.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityListResponse<T> {
    private String message;
    private HttpStatus status;
    private boolean success;
    private List<T> data;
}
