package br.com.nomeempresa.restaurante.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerAlreadyExistsExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Customer already exists!";
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrown() {
        String errorMessage = "Customer already exists!";

        Exception exception = assertThrows(CustomerAlreadyExistsException.class, () -> {
            throw new CustomerAlreadyExistsException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
