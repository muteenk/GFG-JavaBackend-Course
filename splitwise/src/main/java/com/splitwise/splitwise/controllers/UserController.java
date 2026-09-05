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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<CommonUserResponse>> getAuthenticatedUserProfile(
            Principal principal
    ) {
        CommonUserResponse response = userService.getUserById(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("User fetched", response));
    }


}
