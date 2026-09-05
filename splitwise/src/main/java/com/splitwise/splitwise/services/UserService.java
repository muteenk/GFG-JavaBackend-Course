package com.splitwise.splitwise.services;

import com.splitwise.splitwise.dtos.request.UserLoginRequest;
import com.splitwise.splitwise.dtos.request.UserSignupRequest;
import com.splitwise.splitwise.dtos.response.CommonUserResponse;
import com.splitwise.splitwise.dtos.response.JWTUserResponse;
import com.splitwise.splitwise.entites.User;

public interface UserService {
    JWTUserResponse createUser(UserSignupRequest userSignupRequest);

    JWTUserResponse login(UserLoginRequest userLoginRequest);

    CommonUserResponse getUserById(String id);
}
