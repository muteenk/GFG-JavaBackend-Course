package com.schoolproject.management.services;

import static org.junit.jupiter.api.Assertions.*;

import com.schoolproject.management.services.impl.CalculationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class CalculationServiceTest {

    private CalculationService calculationService = new CalculationServiceImpl();

    @Disabled
    @Test
    void addTwoNumbersTest() {
        int result = calculationService.addNumbers(1, 2, 3, 4);
        int expected = 10;
        assertEquals(expected, result);
    }

}
