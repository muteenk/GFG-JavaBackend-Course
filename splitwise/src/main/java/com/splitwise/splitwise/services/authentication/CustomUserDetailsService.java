package com.splitwise.splitwise.services.authentication;

import com.splitwise.splitwise.exceptions.ResourceDoesNotExist;
import com.splitwise.splitwise.repositories.UserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String id) throws ResourceDoesNotExist {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceDoesNotExist("User not found with id: " + id)
        );
    }
}
