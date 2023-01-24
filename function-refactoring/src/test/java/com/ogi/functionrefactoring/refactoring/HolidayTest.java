package com.ogi.functionrefactoring.refactoring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class HolidayTest {

    private final PrintStream standerdOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @ParameterizedTest
    @CsvSource({
            "true,Paying out a holiday. Holidays left: 20",
            "false,Have fun on your holiday. Don't forget to check your emails!"
    })
    void givenHoliday_whenCalculateHoliday_thenReturnHoliday(String input, String expected) {
        String[] args = new String[1];
        args[0] = input;
        Holiday.main(args);

        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    void tearDown() {
        System.setOut(standerdOut);
    }
}