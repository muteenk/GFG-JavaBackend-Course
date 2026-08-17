package com.splitwise.splitwise.services.impl;

import com.splitwise.splitwise.dtos.UserLoginRequest;
import com.splitwise.splitwise.dtos.UserSignupRequest;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.exceptions.InvalidCredentialsException;
import com.splitwise.splitwise.exceptions.ResourceExistsException;
import com.splitwise.splitwise.repositories.UserRepository;
import com.splitwise.splitwise.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(UserSignupRequest userSignupRequest) {
        if (userRepository.existsByEmail(userSignupRequest.email())) {
            throw new ResourceExistsException(
                    "User with provided email '" +
                    userSignupRequest.email() +
                    "' already exists" );
        }

        User user = User.builder()
                .name(userSignupRequest.name())
                .email(userSignupRequest.email())
                .password(userSignupRequest.password())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!user.getPassword().equals(userLoginRequest.password())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return user;
    }
}
