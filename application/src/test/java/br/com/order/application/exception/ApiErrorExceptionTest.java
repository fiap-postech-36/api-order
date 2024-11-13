package br.com.order.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorExceptionTest {

    @Test
    void testApiErrorExceptionMessage() {
        ApiErrorException exception = new ApiErrorException() {
            @Override
            public String getMessage() {
                return "Custom API error message";
            }
        };

        assertEquals("Custom API error message", exception.getMessage());
    }
}