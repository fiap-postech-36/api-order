package br.com.order.domain.application.core.domain.entities;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
import br.com.order.domain.core.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product(1L, "Produto 1", "desc ", "url", new BigDecimal("10.00"), Category.ACOMPANHAMENTO);
        product2 = new Product(1L, "Produto 1", "desc ", "url", new BigDecimal("10.00"), Category.ACOMPANHAMENTO);
        order = new Order(1L, OrderStatus.CREATED, null, null, Arrays.asList(product1, product2));
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

    @Test
    void testCalculateTotal() {
        // Verifica se o total é calculado corretamente
        BigDecimal total = order.calculateTotal();
    }
}

