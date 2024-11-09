package br.com.nomeempresa.restaurante.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoInputPresentTest {

    @Test
    void testExceptionMessage() {
        NoInputPresent exception = new NoInputPresent();
        assertEquals("Parametro de entrada é obrigatório", exception.getMessage());
    }

    @Test
    void testExceptionThrown() {
        Exception exception = assertThrows(NoInputPresent.class, NoInputPresent::new);
        assertEquals("Parametro de entrada é obrigatório", exception.getMessage());
    }
}
