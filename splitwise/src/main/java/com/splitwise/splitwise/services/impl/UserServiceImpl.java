package com.splitwise.splitwise.services.impl;

import com.splitwise.splitwise.dtos.request.UserLoginRequest;
import com.splitwise.splitwise.dtos.request.UserSignupRequest;
import com.splitwise.splitwise.dtos.response.CommonUserResponse;
import com.splitwise.splitwise.dtos.response.JWTUserResponse;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.exceptions.InvalidCredentialsException;
import com.splitwise.splitwise.exceptions.ResourceDoesNotExist;
import com.splitwise.splitwise.exceptions.ResourceExistsException;
import com.splitwise.splitwise.repositories.UserRepository;
import com.splitwise.splitwise.services.UserService;
import com.splitwise.splitwise.services.utility.RedisService;
import com.splitwise.splitwise.utilities.JWTUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public JWTUserResponse createUser(UserSignupRequest userSignupRequest) {
        if (userRepository.existsByEmail(userSignupRequest.email())) {
            throw new ResourceExistsException(
                    "User with provided email '" +
                    userSignupRequest.email() +
                    "' already exists" );
        }

        User user = User.builder()
                .name(userSignupRequest.name())
                .email(userSignupRequest.email())
                .password(passwordEncoder.encode(userSignupRequest.password()))
                .build();
        userRepository.save(user);

        return new JWTUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                jwtUtility.generateJWTToken(user.getId())
        );
    }

    @Override
    public JWTUserResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getId(),
                        userLoginRequest.password()
                )
        );

        return new JWTUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                jwtUtility.generateJWTToken(user.getId())
        );
    }

    @Override
    public CommonUserResponse getUserById(String id) {
        String cacheKey = "user-response:"+id;
        CommonUserResponse response = redisService.get(cacheKey, CommonUserResponse.class);
        if (response != null) return response;
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceDoesNotExist("User does not exist with id: " + id)
        );
        response = new CommonUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        redisService.set(cacheKey, response);
        return response;
    }
}
