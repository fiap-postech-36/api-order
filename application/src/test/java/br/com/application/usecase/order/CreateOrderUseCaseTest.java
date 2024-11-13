package br.com.order.application.usecase.order;

import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.mapper.OrderInputOutputMapper;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Test
    public void shouldCreateOrderSuccessfully() {
        // Arrange
        OrderInput orderInput = new OrderInput(1L, anyList());
        Order mappedOrder = new Order(1L, OrderStatus.CREATED, LocalDateTime.now(), LocalDateTime.now(), anyList());
        mappedOrder.seInitialStatus();

        // Usamos apenas um matcher e removemos qualquer chamada duplicada
        when(orderGateway.save(any(Order.class))).thenReturn(Optional.of(mappedOrder));

        // Act
        Optional<Order> result = createOrderUseCase.execute(orderInput);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getStatus()).isEqualTo(mappedOrder.getStatus());
        verify(orderGateway, times(1)).save(any(Order.class));
    }
}
