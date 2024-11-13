package br.com.order.application.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApiErrorMessageTest {

    @Test
    void testApiErrorMessageConstructorAndGetters() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, Collections.singletonList("Error"));

        assertEquals(HttpStatus.BAD_REQUEST, apiErrorMessage.getStatus());
        assertEquals(1, apiErrorMessage.getErrors().size());
        assertEquals("Error", apiErrorMessage.getErrors().get(0));
    }

    @Test
    void testApiErrorMessageWithArgsConstructor() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);

        assertEquals(HttpStatus.BAD_REQUEST, apiErrorMessage.getStatus());
        assertEquals(2, apiErrorMessage.getErrors().size());
        Assertions.assertTrue(apiErrorMessage.getErrors().contains("Error 1"));
        Assertions.assertTrue(apiErrorMessage.getErrors().contains("Error 2"));
    }

    @Test
    void testApiErrorMessageNoArgsConstructor() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        assertNull(apiErrorMessage.getStatus());
        assertNull(apiErrorMessage.getErrors());
    }

    @Test
    void testSettersAndGetters() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();

        apiErrorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiErrorMessage.setErrors(List.of("Internal Error"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiErrorMessage.getStatus());
        assertEquals(1, apiErrorMessage.getErrors().size());
        assertEquals("Internal Error", apiErrorMessage.getErrors().get(0));
    }
}
