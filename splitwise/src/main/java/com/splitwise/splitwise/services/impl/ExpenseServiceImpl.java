package com.splitwise.splitwise.services.impl;

import com.splitwise.splitwise.entites.ExpenseSplit;
import com.splitwise.splitwise.utilities.ExpenseUtility;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.splitwise.splitwise.dtos.request.CreateExpenseRequest;
import com.splitwise.splitwise.entites.Expense;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.exceptions.ResourceDoesNotExist;
import com.splitwise.splitwise.repositories.ExpenseRepository;
import com.splitwise.splitwise.repositories.GroupRepository;
import com.splitwise.splitwise.repositories.UserRepository;
import com.splitwise.splitwise.services.ExpenseService;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public Expense createExpense(String userId, String groupId, CreateExpenseRequest createExpenseRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceDoesNotExist("User with id: '" + userId + "', not found !"));

        SplitGroup group = groupRepository.findByIdWithMembers(groupId)
                .orElseThrow(() -> new ResourceDoesNotExist("Group with id: '" + groupId + "', not found !"));

        if (!groupRepository.existsByIdAndUsersContaining(groupId, user)) {
            throw new ResourceDoesNotExist("User with id: '" + userId + "', is not a member of this group !");
        }

        BigInteger amount = ExpenseUtility.covertToPaisa(createExpenseRequest.amountInRupees());
        Expense expense = Expense.builder()
                .description(createExpenseRequest.description())
                .amount(amount)
                .paidBy(user)
                .group(group)
                .build();

        expenseRepository.save(expense);
        splitAmountEqually(expense, group, amount);
        return expense;
    }

    private void splitAmountEqually(Expense expense, SplitGroup group, BigInteger amount) {
        Set<User> groupMembers = group.getUsers();
        BigInteger[] splits = amount.divideAndRemainder(BigInteger.valueOf(groupMembers.size()));

        BigInteger share = splits[0];
        BigInteger remainder = splits[1];

        List<ExpenseSplit> expenseSplits = new ArrayList<>();
        for (User member : groupMembers) {
            BigInteger shareAmount = share;
            if (remainder.signum() > 0) {
                remainder = remainder.subtract(BigInteger.ONE);
                shareAmount = shareAmount.add(BigInteger.ONE);
            }
            expenseSplits.add(
                    ExpenseSplit.builder()
                            .expense(expense)
                            .user(member)
                            .amount(shareAmount)
                            .build()
            );
        }

        expense.getSplits().addAll(expenseSplits);
        expenseRepository.save(expense);
    }


}
