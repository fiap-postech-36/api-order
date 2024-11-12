package br.com.order.application.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoResourceFoundExceptionTest {

    @Test
    void testNoResourceFoundExceptionMessage() {
        NoResourceFoundException exception = new NoResourceFoundException();

        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
    }
}
