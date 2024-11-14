package br.com.application.usecase.order;

import br.com.order.application.usecase.order.GetByIdOrderUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetByIdOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private GetByIdOrderUseCase getByIdOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_whenOrderExists() {
        // Arrange
        Long id = 1L;
        Order order = new Order(2L, OrderStatus.CREATED, LocalDateTime.now(), LocalDateTime.now(),  List.of());
        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = getByIdOrderUseCase.execute(id);

        // Assert
        verify(orderGateway).findById(id); // Verifica se o findById foi chamado
        assertTrue(result.isPresent()); // Verifica se o resultado está presente
        assertEquals(order, result.get()); // Verifica se o resultado é o esperado
    }

    @Test
    void testExecute_whenOrderDoesNotExist() {
        // Arrange
        Long id = 1L;
        when(orderGateway.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> getByIdOrderUseCase.execute(id)); // Verifica se a exceção é lançada
        verify(orderGateway).findById(id); // Verifica se o findById foi chamado
    }
}
