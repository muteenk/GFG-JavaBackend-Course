package com.splitwise.splitwise.dtos.response;

import java.math.BigInteger;

public record CreateExpenseResponse (
    String id,
    String description,
    BigInteger amount
) {}
