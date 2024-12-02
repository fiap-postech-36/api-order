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
        order = new Order(1L, LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(product1, product2));
    }
}

