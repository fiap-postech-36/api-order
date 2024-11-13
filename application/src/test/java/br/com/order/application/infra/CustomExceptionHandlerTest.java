package br.com.order.application.infra;

import br.com.order.application.exception.CustomerAlreadyExistsException;
import br.com.order.application.exception.CustomerNotFoundException;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler exceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionHandler = new CustomExceptionHandler();
        webRequest = mock(WebRequest.class);
    }

    @Test
    void testHandleBadRequest_withIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<Object> response = exceptionHandler.handleBadRequest(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", ((ApiErrorMessage) Objects.requireNonNull(response.getBody())).getErrors().get(0));
    }

    @Test
    void testHandleBadRequest_withValidationException() {
        ValidationException ex = new ValidationException("Validation error");

        ResponseEntity<Object> response = exceptionHandler.handleBadRequest(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation error", ((ApiErrorMessage) response.getBody()).getErrors().get(0));
    }

    @Test
    void testHandleBadRequest_withCustomerAlreadyExistsException() {
        CustomerAlreadyExistsException ex = new CustomerAlreadyExistsException("Customer already exists");

        ResponseEntity<Object> response = exceptionHandler.handleBadRequest(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Customer already exists", ((ApiErrorMessage) response.getBody()).getErrors().get(0));
    }

    @Test
    void testHandleInternal_withRuntimeException() {
        RuntimeException ex = new RuntimeException("Internal error");

        ResponseEntity<Object> response = exceptionHandler.handleInternal(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", ((ApiErrorMessage) response.getBody()).getErrors().get(0));
    }

    @Test
    void testHandleNotFound_withNoSuchElementException() {
        NoSuchElementException ex = new NoSuchElementException("Element not found");

        ResponseEntity<Object> response = exceptionHandler.handleNotFound(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Element not found", ((ApiErrorMessage) response.getBody()).getErrors().get(0));
    }

    @Test
    void testHandleNotFound_withCustomerNotFoundException() {
        CustomerNotFoundException ex = new CustomerNotFoundException("Customer not found");

        ResponseEntity<Object> response = exceptionHandler.handleNotFound(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found", ((ApiErrorMessage) response.getBody()).getErrors().get(0));
    }
}
