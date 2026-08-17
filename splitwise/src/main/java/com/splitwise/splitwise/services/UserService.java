package com.splitwise.splitwise.services;

import com.splitwise.splitwise.dtos.UserLoginRequest;
import com.splitwise.splitwise.dtos.UserSignupRequest;
import com.splitwise.splitwise.entites.User;

public interface UserService {
    User createUser(UserSignupRequest userSignupRequest);

    User login(UserLoginRequest userLoginRequest);
}
