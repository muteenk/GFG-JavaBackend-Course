package com.schoolproject.management.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<K, V> {
    private String message;
    private HttpStatus status;
    private boolean success;
    private Map<K,V> data;
}
