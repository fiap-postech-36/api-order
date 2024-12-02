package br.com.order.infra.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderEntityTest {

    private OrderEntity orderEntity;
    private ProductEntity product1;
    private ProductEntity product2;

    @BeforeEach
    void setUp() {
        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setName("Product 1");

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setName("Product 2");

        orderEntity = new OrderEntity();
    }

    @Test
    void testGettersAndSetters() {
        orderEntity.setId(1L);
        LocalDateTime receivedAt = LocalDateTime.now();
        LocalDateTime finishedAt = LocalDateTime.now().plusHours(1);
        orderEntity.setReceivedAt(receivedAt);
        orderEntity.setFinishedAt(finishedAt);
        orderEntity.setProducts(List.of(product1, product2));

        assertEquals(1L, orderEntity.getId());
        assertEquals(receivedAt, orderEntity.getReceivedAt());
        assertEquals(finishedAt, orderEntity.getFinishedAt());
        assertEquals(2, orderEntity.getProducts().size());
        assertEquals(product1, orderEntity.getProducts().get(0));
        assertEquals(product2, orderEntity.getProducts().get(1));
    }

    @Test
    void testNoArgsConstructor() {
        OrderEntity newOrderEntity = new OrderEntity();

        assertNull(newOrderEntity.getId());
        assertNull(newOrderEntity.getReceivedAt());
        assertNull(newOrderEntity.getFinishedAt());
        assertNull(newOrderEntity.getProducts());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime receivedAt = LocalDateTime.now();
        LocalDateTime finishedAt = LocalDateTime.now().plusHours(1);
        List<ProductEntity> products = List.of(product1, product2);

        OrderEntity newOrderEntity = new OrderEntity(1L, receivedAt, finishedAt, products);

        assertEquals(1L, newOrderEntity.getId());
        assertEquals(receivedAt, newOrderEntity.getReceivedAt());
        assertEquals(finishedAt, newOrderEntity.getFinishedAt());
        assertEquals(2, newOrderEntity.getProducts().size());
        assertEquals(product1, newOrderEntity.getProducts().get(0));
        assertEquals(product2, newOrderEntity.getProducts().get(1));
    }
}
