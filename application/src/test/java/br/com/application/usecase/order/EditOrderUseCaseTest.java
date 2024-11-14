package br.com.application.usecase.order;

import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.usecase.order.EditOrderUseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EditOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EditOrderUseCase editOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testExecute_whenOrderIsNotFound2() {
        // Arrange
        OrderInput orderInput = new OrderInput(1L, Collections.singletonList(1L));
        when(orderGateway.findById(orderInput.id())).thenReturn(Optional.empty());

        // Act
        Optional<Order> result = editOrderUseCase.execute(orderInput);

        // Assert
        verify(orderGateway).findById(orderInput.id()); // Verifica se o findById foi chamado
        verify(modelMapper, never()).map(any(Order.class), any(Order.class)); // Verifica que o mapeamento não foi chamado
        assertEquals(Optional.empty(), result); // Verifica se o resultado é Optional.empty()
    }
}
