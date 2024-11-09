package br.com.nomeempresa.restaurante.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundTest {

    @Test
    void testExceptionMessageWithArgument() {
        String errorMessage = "Custom resource not found!";
        ResourceNotFound exception = new ResourceNotFound(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionMessageWithoutArgument() {
        ResourceNotFound exception = new ResourceNotFound();
        assertNull(exception.getMessage());
    }

    @Test
    void testExceptionThrownWithMessage() {
        String errorMessage = "Custom resource not found!";

        Exception exception = assertThrows(ResourceNotFound.class, () -> {
            throw new ResourceNotFound(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
