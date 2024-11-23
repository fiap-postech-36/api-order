package br.com.order.application.usecase.order;

import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRecivedUseCaseTest {


    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private OrderRecivedUseCase orderRecivedUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_OrderNotFound() {
        Integer orderId = 1;
        when(orderGateway.findById(1L)).thenReturn(Optional.empty());

        Optional<Order> result = orderRecivedUseCase.execute(orderId);

        assertTrue(result.isEmpty());
        verify(orderGateway).findById(1L);
        verifyNoMoreInteractions(orderGateway);
    }

    @Test
    void testExecute_OrderFound() {
        Integer orderId = 1;
        Order order = mock(Order.class);
        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));
        when(orderGateway.save(order)).thenReturn(Optional.of(order));

        Optional<Order> result = orderRecivedUseCase.execute(orderId);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());
        verify(orderGateway).findById(1L);
        verify(order).setReceived();
        verify(orderGateway).save(order);
    }

}