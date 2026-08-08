package com.schoolproject.management.exceptions;

import com.schoolproject.management.entities.Student;
import com.schoolproject.management.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<String, Object>> handleValidationError(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, Object> responseData = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((err) -> {
            responseData.put(err.getField(), err.getDefaultMessage());
        });

        APIResponse<String, Object> response = new APIResponse<>();
        response.setStatus(status);
        response.setData(responseData);
        response.setMessage("Invalid data provided");
        response.setSuccess(false);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<APIResponse<String, Object>> handleServerError(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        logger.error(ex.getMessage());
        ex.printStackTrace();

        APIResponse<String, Object> response = new APIResponse<>();
        response.setStatus(status);
        response.setData(new HashMap<>());
        response.setMessage("Internal server error");
        response.setSuccess(false);
        return new ResponseEntity<>(response, status);
    }

}
