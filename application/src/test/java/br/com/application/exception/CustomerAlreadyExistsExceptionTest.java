package br.com.application.exception;

import br.com.order.application.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerAlreadyExistsExceptionTest {

    @Test
    void testCustomerAlreadyExistsExceptionMessage() {
        String message = "Customer already exists";
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testCustomerAlreadyExistsExceptionThrows() {
        String message = "Customer already exists";

        assertThrows(CustomerAlreadyExistsException.class, () -> {
            throw new CustomerAlreadyExistsException(message);
        });
    }
}
