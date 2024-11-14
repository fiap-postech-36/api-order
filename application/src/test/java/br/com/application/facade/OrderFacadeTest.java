package br.com.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.facade.OrderFacade;
import br.com.order.application.usecase.order.DeleteOrderUseCase;
import br.com.order.application.usecase.order.GetByIdOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderFacadeTest {

    @Mock
    private DeleteOrderUseCase deleteOrderUseCase;

    @Mock
    private GetByIdOrderUseCase getByIdOrderUseCase;

    @InjectMocks
    private OrderFacade orderFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteOrder_Success() {
        Long orderId = 1L;

        orderFacade.delete(orderId);

        verify(deleteOrderUseCase, times(1)).execute(orderId);
    }

    @Test
    void testGetOrderById_NotFound() {
        Long orderId = 1L;
        when(getByIdOrderUseCase.execute(orderId)).thenReturn(Optional.empty());
        assertThrows(NoResourceFoundException.class, () -> orderFacade.getOrderById(orderId));
    }


}