package br.com.nomeempresa.restaurante.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MercadoPagoIntegrationExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Mercado Pago integration failed!";
        MercadoPagoIntegrationException exception = new MercadoPagoIntegrationException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrown() {
        String errorMessage = "Mercado Pago integration failed!";

        Exception exception = assertThrows(MercadoPagoIntegrationException.class, () -> {
            throw new MercadoPagoIntegrationException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
