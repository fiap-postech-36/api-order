package br.com.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.facade.OrderFacade;
import br.com.order.application.inout.output.OrderOutput;
import br.com.order.application.inout.output.OrderRabbitOutput;
import br.com.order.application.usecase.order.*;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
import br.com.order.domain.core.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderFacadeTest {

    @InjectMocks
    private OrderFacade orderFacade;

    @Mock
    private CreateOrderUseCase createOrderUseCase;
    @Mock
    private EditOrderUseCase editOrderUseCase;
    @Mock
    private OrderStatusUseCase orderStatusUseCase;
    @Mock
    private FilterOrderUseCase filterOrderUseCase;
    @Mock
    private DeleteOrderUseCase deleteOrderUseCase;
    @Mock
    private GetByIdOrderUseCase getByIdOrderUseCase;
    @Mock
    private CalculateTotalOrderUseCase calculateTotalOrderUseCase;
    @Mock
    private RabbitTemplate rabbitTemplate;

    private Long orderId;
    private Order order;

    @BeforeEach
    void setUp() {
        orderId = 1L;

        order = Order.builder()
                .id(orderId)
                .finishedAt(LocalDateTime.now())
                .receivedAt(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .products(Arrays.asList(Product.builder()
                                .category(Category.ACOMPANHAMENTO)
                                .description("X-TUDO")
                                .price(BigDecimal.valueOf(10))
                        .build()))
                .build();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        when(createOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));
        when(calculateTotalOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(BigDecimal.TEN));

        OrderOutput result = orderFacade.create(Mockito.any());

        assertNotNull(result);
        verify(createOrderUseCase, times(1)).execute(Mockito.any());
        verify(calculateTotalOrderUseCase, times(1)).execute(Mockito.any());
        verify(rabbitTemplate, times(1)).convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(OrderRabbitOutput.class));
    }

    @Test
    void testUpdateOrder() {
        when(editOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));

        OrderOutput result = orderFacade.update(Mockito.any());

        assertNotNull(result);
        verify(editOrderUseCase, times(1)).execute(Mockito.any());
    }

    @Test
    void testUpdateStatusOrder() {
        when(getByIdOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));
        when(orderStatusUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));

        OrderOutput result = orderFacade.updateStatusOrder(orderId);

        assertNotNull(result);
        verify(getByIdOrderUseCase, times(1)).execute(orderId);
        verify(orderStatusUseCase, times(1)).execute(order);
    }

    @Test
    void testDeleteOrder() {
        orderFacade.delete(orderId);
        verify(deleteOrderUseCase, times(1)).execute(orderId);
    }

    @Test
    void testGetOrder() {
        when(getByIdOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));

        OrderOutput result = orderFacade.get(orderId);

        assertNotNull(result);
        verify(getByIdOrderUseCase, times(1)).execute(orderId);
    }

    @Test
    void testGetOrderById() {
        when(getByIdOrderUseCase.execute(Mockito.any())).thenReturn(Optional.of(order));

        Order result = orderFacade.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(order, result);
        verify(getByIdOrderUseCase, times(1)).execute(orderId);
    }

    @Test
    void testGetOrderById_NotFound() {
        when(getByIdOrderUseCase.execute(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NoResourceFoundException.class, () -> orderFacade.getOrderById(orderId));
        verify(getByIdOrderUseCase, times(1)).execute(orderId);
    }
}
