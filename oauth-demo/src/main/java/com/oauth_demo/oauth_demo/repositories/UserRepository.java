package com.oauth_demo.oauth_demo.repositories;

import com.oauth_demo.oauth_demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderSubject(String provider, String providerSubject);
}
