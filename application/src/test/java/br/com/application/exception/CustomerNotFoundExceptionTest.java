package br.com.application.exception;

import br.com.order.application.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerNotFoundExceptionTest {

    @Test
    void testCustomerNotFoundExceptionMessage() {
        String message = "Customer not found";
        CustomerNotFoundException exception = new CustomerNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testCustomerNotFoundExceptionThrows() {
        String message = "Customer not found";

        assertThrows(CustomerNotFoundException.class, () -> {
            throw new CustomerNotFoundException(message);
        });
    }
}
