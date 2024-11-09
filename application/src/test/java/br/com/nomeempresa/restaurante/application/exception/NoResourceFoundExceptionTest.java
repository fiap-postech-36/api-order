package br.com.nomeempresa.restaurante.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoResourceFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Resource not found!";
        NoResourceFoundException exception = new NoResourceFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrownWithMessage() {
        String errorMessage = "Resource not found!";

        Exception exception = assertThrows(NoResourceFoundException.class, () -> {
            throw new NoResourceFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionDefaultMessage() {
        NoResourceFoundException exception = new NoResourceFoundException();
        assertEquals("Not Found", exception.getMessage());
    }

    @Test
    void testExceptionCode() {
        NoResourceFoundException exception = new NoResourceFoundException();
        assertEquals(404, exception.getCode());
    }
}
