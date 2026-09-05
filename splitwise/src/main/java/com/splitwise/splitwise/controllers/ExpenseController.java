package com.splitwise.splitwise.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.splitwise.dtos.request.CreateExpenseRequest;
import com.splitwise.splitwise.dtos.response.CreateExpenseResponse;
import com.splitwise.splitwise.entites.Expense;
import com.splitwise.splitwise.payloads.ApiResponse;
import com.splitwise.splitwise.services.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RestController
@RequestMapping("/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/{groupId}/create")
    public ResponseEntity<ApiResponse<CreateExpenseResponse>> createExpense(
        @PathVariable String groupId,
        @RequestBody @Valid CreateExpenseRequest createExpenseRequest,
        Principal principal
    ) {
        Expense expense = expenseService.createExpense(principal.getName(), groupId, createExpenseRequest);
        CreateExpenseResponse createExpenseResponse = new CreateExpenseResponse(
            expense.getId(),
            expense.getDescription(),
            expense.getAmount()
        );
        return ResponseEntity.ok(ApiResponse.success("Expense created successfully", createExpenseResponse));
    }
}
