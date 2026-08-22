package com.splitwise.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.splitwise.entites.Expense;
import com.splitwise.splitwise.repositories.projections.GroupSummaryProjection;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
    
    @Query("""
        SELECT g.id AS groupId,
            g.groupName AS groupName,
            g.description AS groupDescription,
            COALESCE((
             SELECT SUM(e.amount)
             FROM Expense e
             WHERE e.group.id = g.id AND e.paidBy.id = :userId
            ), 0) AS totalPaid,
            COALESCE((
             SELECT SUM(es.amount)
             FROM ExpenseSplit es
             WHERE es.expense.group.id = g.id AND es.user.id = :userId
            ), 0) AS totalOwed
        FROM User u
        JOIN u.groups g
        WHERE u.id = :userId
    """)
    List<GroupSummaryProjection> getGroupsSummaryForUser(@Param("userId") String userId);

}
