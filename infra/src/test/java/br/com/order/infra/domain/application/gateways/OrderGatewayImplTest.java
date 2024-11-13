package br.com.order.infra.domain.application.gateways;

import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
import br.com.order.infra.entity.OrderEntity;
import br.com.order.infra.gateways.OrderGatewayImpl;
import br.com.order.infra.mapper.OrderMapper;
import br.com.order.infra.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrderGatewayImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderGatewayImpl orderGateway;

    private Order order;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order(3L, OrderStatus.RECEIVED, LocalDateTime.now(), LocalDateTime.now(), List.of());
        orderEntity = new OrderEntity();
        orderEntity.setStatus(OrderStatus.RECEIVED);
    }

    @Test
    void testRemove() {
        Long orderId = 1L;
        doNothing().when(orderRepository).deleteById(orderId);
        orderGateway.remove(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testFindById() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(mapper.orderEntityToOrder(orderEntity)).thenReturn(order);

        Optional<Order> foundOrder = orderGateway.findById(orderId);

        assertTrue(foundOrder.isPresent());
    }

    @Test
    void testFindByStatus() {
        String status = "RECEIVED";
        when(orderRepository.findByStatus(status)).thenReturn(List.of(orderEntity));
        when(mapper.orderEntityToOrder(orderEntity)).thenReturn(order);

        Collection<Order> orders = orderGateway.findByStatus(status);

        assertEquals(1, orders.size());
    }

    @Test
    void testFindAll() {
        when(orderRepository.findAll()).thenReturn(List.of(orderEntity));
        when(mapper.orderEntityToOrder(orderEntity)).thenReturn(order);

        Collection<Order> orders = orderGateway.findAll();

        assertEquals(1, orders.size());
    }

    @Test
    void testFindByPriority() {
        List<OrderStatus> statuses = List.of(OrderStatus.IN_PREPARATION, OrderStatus.RECEIVED, OrderStatus.READY);
        when(orderRepository.getByPriority(statuses)).thenReturn(List.of(orderEntity));
        when(mapper.orderEntityToOrder(orderEntity)).thenReturn(order);

        Collection<Order> orders = orderGateway.findByPriority();

        assertEquals(1, orders.size());
    }

    @Test
    void testFilter() {
        String status = "RECEIVED";
        when(orderRepository.findByStatus(status)).thenReturn(List.of(orderEntity));
        when(mapper.orderEntityToOrder(orderEntity)).thenReturn(order);

        Collection<Order> orders = orderGateway.filter(status);

        assertEquals(1, orders.size());
    }
}
