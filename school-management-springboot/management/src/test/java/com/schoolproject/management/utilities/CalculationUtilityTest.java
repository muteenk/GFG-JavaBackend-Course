package com.schoolproject.management.utilities;

import com.schoolproject.management.utilties.CalculationUtility;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Scanner;

public class CalculationUtilityTest {

//    private static Scanner counter;
//
//    @BeforeAll
//    public static void init() {
//        counter = new Scanner(System.in);
//    }
//
//    @AfterAll
//    public static void cleanup() {
//        counter.close();
//    }

//    @BeforeEach
//    @AfterEach

    @Disabled
    @Test
    public void baseTestForAddingTwoNumber() {
        int a = 10, b = 20;  // arrange
        int c = 11, d = 34;

        int result = CalculationUtility.addNumbers(a, b);
        int expectedResult = 30;

        assertEquals(expectedResult, result);
        assertEquals(45, CalculationUtility.addNumbers(c,d));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,6"
    })
    public void baseParameterizedTestForAddingTwoNumber(int a, int b, int expected) {
        assertEquals(expected, CalculationUtility.addNumbers(a, b));
    }

    @Disabled
    @Test
    public void exceptionTestForDividingTwoNumbers() {
//        String s = "Hello";
//        String s2 = "Hello";
//        assertEquals(s, s2);
//        assertSame(s, s2);

        assertThrows(Exception.class, () -> CalculationUtility.divideNumbers(10, 0));
    }

}
