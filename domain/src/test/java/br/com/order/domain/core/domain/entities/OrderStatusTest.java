package br.com.order.domain.core.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderStatusTest {

    @Test
    void testGetFromOrder() {
        // Testando a recuperação dos Status por ordem
        assertEquals(OrderStatus.CANCELED, OrderStatus.getFromOrder(-1));
        assertEquals(OrderStatus.CREATED, OrderStatus.getFromOrder(0));
        assertEquals(OrderStatus.RECEIVED, OrderStatus.getFromOrder(1));
        assertEquals(OrderStatus.IN_PREPARATION, OrderStatus.getFromOrder(2));
        assertEquals(OrderStatus.READY, OrderStatus.getFromOrder(3));
        assertEquals(OrderStatus.FINISHED, OrderStatus.getFromOrder(4));

        // Testando o comportamento quando a ordem não existe
        assertNull(OrderStatus.getFromOrder(10));
    }

    @Test
    void testGetNext() {
        // Testando a transição para o próximo status
        assertEquals(OrderStatus.RECEIVED, OrderStatus.CREATED.getNext());
        assertEquals(OrderStatus.IN_PREPARATION, OrderStatus.RECEIVED.getNext());
        assertEquals(OrderStatus.READY, OrderStatus.IN_PREPARATION.getNext());
        assertEquals(OrderStatus.FINISHED, OrderStatus.READY.getNext());

        // Testando a transição do último status (FINISHED)
        assertNull(OrderStatus.FINISHED.getNext());
    }
}
