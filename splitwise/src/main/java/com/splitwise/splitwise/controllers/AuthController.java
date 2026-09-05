package com.splitwise.splitwise.controllers;

import com.splitwise.splitwise.dtos.request.UserLoginRequest;
import com.splitwise.splitwise.dtos.request.UserSignupRequest;
import com.splitwise.splitwise.dtos.response.JWTUserResponse;
import com.splitwise.splitwise.payloads.ApiResponse;
import com.splitwise.splitwise.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JWTUserResponse>> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        JWTUserResponse userResponse = userService.createUser(userSignupRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success("User signed up successfully !", userResponse)
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JWTUserResponse>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        JWTUserResponse userResponse = userService.login(userLoginRequest);
        return ResponseEntity.ok(
                ApiResponse.success("Login successful", userResponse)
        );
    }

}
