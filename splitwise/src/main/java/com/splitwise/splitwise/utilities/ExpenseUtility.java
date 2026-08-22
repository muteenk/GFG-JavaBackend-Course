package com.splitwise.splitwise.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ExpenseUtility {

    public static BigInteger covertToPaisa(BigDecimal amountInRupees) {
        return amountInRupees.multiply(BigDecimal.valueOf(100)).toBigIntegerExact();
    }

    public static BigDecimal covertToRupees(BigInteger amountInPaisa) {
        return new BigDecimal(amountInPaisa).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
