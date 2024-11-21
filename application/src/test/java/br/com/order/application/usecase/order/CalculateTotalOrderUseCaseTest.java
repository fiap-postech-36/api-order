package br.com.order.application.usecase.order;

import br.com.order.application.inout.output.OrderOutput;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CalculateTotalOrderUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private CalculateTotalOrderUseCase calculateTotalOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_EmptyOrder() {
        OrderOutput orderOutput = mock(OrderOutput.class);
        Product product1 = new Product(1L, "Product 1", "", "", BigDecimal.TEN, Category.ACOMPANHAMENTO);
        Product product2 = new Product(1L, "Product 2", "", "", BigDecimal.TEN, Category.ACOMPANHAMENTO);
        when(orderOutput.items()).thenReturn(List.of(product1, product2));
        when(productGateway.findById(1L)).thenReturn(Optional.of(product1));
        when(productGateway.findById(2L)).thenReturn(Optional.of(product2));

        Optional<BigDecimal> result = calculateTotalOrderUseCase.execute(orderOutput);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal(20), result.get());
        verify(orderOutput).items();
        verify(productGateway, times(2)).findById(any());
    }
}