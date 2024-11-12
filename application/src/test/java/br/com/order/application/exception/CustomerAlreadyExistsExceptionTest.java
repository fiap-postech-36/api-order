package br.com.order.application.exception;

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

    @Test
    void shouldReturnCorrectMessageWhenExceptionIsThrown() {
        // Arrange
        String expectedMessage = "Customer already exists";

        // Act
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
