package br.com.application.usecase.order;

import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.usecase.order.FilterOrderUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FilterOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private FilterOrderUseCase filterOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        FilterInput filterInput = new FilterInput(Map.of("status", "PENDING"));
        Order order1 = new Order(2L, LocalDateTime.now(), LocalDateTime.now(),  List.of());
        Order order2 = new Order(3L, LocalDateTime.now(), LocalDateTime.now(),  List.of());
        List<Order> orderList = List.of(order1, order2);
        when(orderGateway.findAll()).thenReturn(orderList);

        // Act
        Optional<Page<Order>> result = filterOrderUseCase.execute(filterInput);

        // Assert
        verify(orderGateway).findAll(); // Verifica se o findByPriority foi chamado
        assertTrue(result.isPresent()); // Verifica se o resultado está presente
        assertEquals(new PageImpl<>(orderList), result.get()); // Verifica se o conteúdo do Page é o esperado
        assertEquals(2, result.get().getContent().size()); // Verifica o tamanho da lista de pedidos no Page
    }
}
