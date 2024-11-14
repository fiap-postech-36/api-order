package br.com.application.usecase.order;

import br.com.order.application.usecase.order.DeleteOrderUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private DeleteOrderUseCase deleteOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_returnsEmptyOptional() {
        // Act
        Optional<Order> result = deleteOrderUseCase.execute(1L);

        // Assert
        assertEquals(Optional.empty(), result); // Verifica se o retorno é Optional.empty()
    }
}

