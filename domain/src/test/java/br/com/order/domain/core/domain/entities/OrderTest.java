package br.com.order.domain.core.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        Product product1 = new Product(1L, "Produto 1", "desc ", "url", new BigDecimal("10.00"), Category.ACOMPANHAMENTO);
        Product product2 = new Product(1L, "Produto 1", "desc ", "url", new BigDecimal("10.00"), Category.ACOMPANHAMENTO);
        order = new Order(1L, OrderStatus.CREATED, LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(product1, product2));
    }

    @Test
    void testSeInitialStatus() {
        // Verifica se o status foi corretamente inicializado como CREATED
        order.seInitialStatus();
        assertEquals(OrderStatus.CREATED, order.getStatus());
        assertNotNull(order.getReceivedAt()); // Verifica se a data de recebimento foi definida
    }

    @Test
    void testSetReceived() {
        // Verifica se o status é alterado para RECEIVED e finishedAt é atualizado
        order.setReceived();
        assertEquals(OrderStatus.RECEIVED, order.getStatus());
        assertNotNull(order.getFinishedAt()); // Verifica se a data de finalização foi definida
    }

    @Test
    void testNextStepOrder() {
        // Verifica a transição do status da ordem
        order.nextStepOrder();
        assertEquals(OrderStatus.RECEIVED, order.getStatus());

        // Testa a transição para o próximo status
        order.nextStepOrder();
    }

//    @Test
//    void testCalculateTotal() {
//        // Verifica se o total é calculado corretamente
//        BigDecimal total = order.calculateTotal();
//    }
}

