package com.oauth_demo.oauth_demo.services;

import com.oauth_demo.oauth_demo.entities.User;
import com.oauth_demo.oauth_demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createOrUpdateUser(String provider, OAuth2User oauth2User) {
        // getName() returns the provider's "name attribute":
        // Google (OIDC) -> "sub", GitHub -> "id"
        String subject = oauth2User.getName();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        if (name == null) {
            name = oauth2User.getAttribute("login");
        }

        Optional<User> existingUser = userRepository
                .findByProviderAndProviderSubject(provider, subject);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
            return user;
        }

        User newUser = User.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .providerSubject(subject)
                .build();

        return userRepository.save(newUser);
    }

    public User getUserByProviderInformation(String provider, @Nullable String providerSubject) {
        return userRepository
                .findByProviderAndProviderSubject(provider, providerSubject)
                .orElseThrow(() -> new RuntimeException("User not found !"));
    }
}
