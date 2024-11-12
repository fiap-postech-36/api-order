package br.com.order.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Customer not found!";
        CustomerNotFoundException exception = new CustomerNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrown() {
        String errorMessage = "Customer not found!";

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            throw new CustomerNotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
