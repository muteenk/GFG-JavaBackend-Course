package com.splitwise.splitwise.repositories;

import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<SplitGroup, String> {

    @Query(
        """
            SELECT g FROM SplitGroup g
            LEFT JOIN FETCH g.users u
            WHERE g.id = :groupId
        """
    )
    Optional<SplitGroup> findByIdWithMembers(String groupId);

    boolean existsByIdAndUsersContaining(String groupId, User user);
}
