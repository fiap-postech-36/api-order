package br.com.order.domain.gateway;

import br.com.order.domain.core.domain.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderGatewayTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private OrderGatewayTest orderGatewayTest;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order(2L, LocalDateTime.now(), LocalDateTime.now(), List.of());
    }

    @Test
    void testSave() {
        when(orderGateway.save(order)).thenReturn(Optional.of(order));

        Optional<Order> savedOrder = orderGateway.save(order);

        assertTrue(savedOrder.isPresent());
        assertEquals(order, savedOrder.get());
        verify(orderGateway, times(1)).save(order);
    }

    @Test
    void testUpdate() {
        when(orderGateway.update(order)).thenReturn(Optional.of(order));

        Optional<Order> updatedOrder = orderGateway.update(order);

        assertTrue(updatedOrder.isPresent());
        assertEquals(order, updatedOrder.get());
        verify(orderGateway, times(1)).update(order);
    }

    @Test
    void testRemove() {
        doNothing().when(orderGateway).remove(1L);

        orderGateway.remove(1L);

        verify(orderGateway, times(1)).remove(1L);
    }

    @Test
    void testFindById() {
        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderGateway.findById(1L);

        assertTrue(foundOrder.isPresent());
        assertEquals(order, foundOrder.get());
        verify(orderGateway, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(orderGateway.findAll()).thenReturn(Collections.singletonList(order));

        Collection<Order> orders = orderGateway.findAll();

        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        verify(orderGateway, times(1)).findAll();
    }
}

