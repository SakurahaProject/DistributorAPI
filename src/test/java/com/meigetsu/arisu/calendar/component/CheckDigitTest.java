package com.meigetsu.arisu.calendar.component;

import org.junit.jupiter.api.Test;

public class CheckDigitTest {
    @Test
    public void testCalculateCheckDigit() {
        String input = "123456789";
        int expected = 7; // Replace with the expected check digit
        int actual = CheckDigit.calcModulus10Weight21(input);
        assert actual == expected;
    }
}
