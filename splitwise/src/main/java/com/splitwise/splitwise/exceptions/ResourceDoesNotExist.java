package com.splitwise.splitwise.exceptions;

import com.splitwise.splitwise.payloads.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResourceDoesNotExist extends ApiException {
    public ResourceDoesNotExist(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
