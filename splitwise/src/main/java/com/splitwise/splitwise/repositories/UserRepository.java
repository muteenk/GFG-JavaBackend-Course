package com.splitwise.splitwise.repositories;

import com.splitwise.splitwise.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // <action>By<property><direction><operator>
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByEmailIn(List<String> emails);
}
