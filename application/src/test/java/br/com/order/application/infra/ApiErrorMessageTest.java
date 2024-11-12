package br.com.order.application.infra;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

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
    void testApiErrorMessageNoArgsConstructor() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();

        assertNull(apiErrorMessage.getStatus());
        assertNull(apiErrorMessage.getErrors());
    }
}
