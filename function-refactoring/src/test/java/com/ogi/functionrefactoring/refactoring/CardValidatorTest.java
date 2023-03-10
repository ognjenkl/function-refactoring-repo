package com.ogi.functionrefactoring.refactoring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardValidatorTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void givenCardValidator_whenValidateCard_thenCardValid() {
        String expected = """
                Is Alice's card valid?\r
                true\r
                CardValidator.Customer(name=Alice, phone=2341, card=CardValidator.Card(number=1249190007575069, expMonth=1, expYear=2024), valid=true)""";

        CardValidator.main(null);

        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}