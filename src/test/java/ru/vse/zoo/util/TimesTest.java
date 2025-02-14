package ru.vse.zoo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimesTest {
    @Test
    public void validate_format_and_parse_Instant() {
        String expected = "01.02.2025 19:18:17";
        assertEquals(expected, Times.format(Times.parseInstant(expected)));
    }
}
