package br.com.application.usecase.order;

import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.mapper.OrderInputOutputMapper;
import br.com.order.application.usecase.order.CreateOrderUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_whenOrderIsCreatedSuccessfully() {
        // Arrange
        OrderInput orderInput = new OrderInput(3L, Collections.singletonList(1L));
        Order order = OrderInputOutputMapper.INSTANCE.orderRequestToOrder(orderInput);
        order.seInitialStatus();

        when(orderGateway.save(any(Order.class))).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = createOrderUseCase.execute(orderInput);

        // Assert
        verify(orderGateway).save(any(Order.class)); // Verifica se o save foi chamado
        assertEquals(Optional.of(order), result); // Verifica se o retorno é o esperado
    }
}

