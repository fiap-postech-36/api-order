package br.com.application.usecase.order;

import br.com.order.application.usecase.order.OrderStatusUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderStatusUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private OrderStatusUseCase orderStatusUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        Order order = mock(Order.class);
        when(orderGateway.save(order)).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = orderStatusUseCase.execute(order);

        // Assert
        verify(order).nextStepOrder(); // Verifica se o nextStepOrder foi chamado
        verify(orderGateway).save(order); // Verifica se o save foi chamado no gateway
        assertEquals(Optional.of(order), result); // Verifica se o resultado é o esperado
    }
}
