package com.splitwise.splitwise.services;

import com.splitwise.splitwise.dtos.request.UserLoginRequest;
import com.splitwise.splitwise.dtos.request.UserSignupRequest;
import com.splitwise.splitwise.entites.User;

public interface UserService {
    User createUser(UserSignupRequest userSignupRequest);

    User login(UserLoginRequest userLoginRequest);
}
