package com.splitwise.splitwise.controllers;

import com.splitwise.splitwise.dtos.request.UserLoginRequest;
import com.splitwise.splitwise.dtos.request.UserSignupRequest;
import com.splitwise.splitwise.dtos.response.CommonUserResponse;
import com.splitwise.splitwise.entites.User;
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
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<CommonUserResponse>> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        User newUser = userService.createUser(userSignupRequest);
        CommonUserResponse responseDto = new CommonUserResponse(
                newUser.getId(), newUser.getName(), newUser.getEmail()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiResponse.success("User signed up successfully !", responseDto)
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<CommonUserResponse>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);
        CommonUserResponse responseDto = new CommonUserResponse(
                user.getId(), user.getName(), user.getEmail()
        );
        return ResponseEntity.ok(
                ApiResponse.success("Login successful", responseDto)
        );
    }
}
