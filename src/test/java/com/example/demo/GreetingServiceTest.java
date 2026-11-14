package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greetsByName() {
        assertEquals("Hello, Asha!", service.greet("Asha"));
    }

    @Test
    void trimsWhitespace() {
        assertEquals("Hello, Asha!", service.greet("  Asha  "));
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () -> service.greet("  "));
    }

    @Test
    void rejectsNullName() {
        assertThrows(IllegalArgumentException.class, () -> service.greet(null));
    }
}
