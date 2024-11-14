package br.com.application.usecase.order;

import br.com.order.application.exception.ResourceNotFound;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.mapper.ProductInputOutputMapper;
import br.com.order.application.usecase.product.EditProductUseCase;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EditProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private EditProductUseCase editProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_whenProductExists() {
        // Arrange
        ProductInput productInput = new ProductInput(1L, "Product Name", "desc", "url img", new BigDecimal("10.0"), Category.ACOMPANHAMENTO);
        Product product = ProductInputOutputMapper.INSTANCE.productInputToProduct(productInput);
        when(productGateway.findById(productInput.id())).thenReturn(Optional.of(product));
        when(productGateway.save(product)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = editProductUseCase.execute(productInput);

        // Assert
        verify(productGateway).findById(productInput.id()); // Verifica se o findById foi chamado
        verify(productGateway).save(product); // Verifica se o save foi chamado
        assertEquals(Optional.of(product), result); // Verifica se o resultado é o esperado
    }

    @Test
    void testExecute_whenProductDoesNotExist() {
        // Arrange
        ProductInput productInput = new ProductInput(1L, "Product Name", "desc", "url img", new BigDecimal("10.0"), Category.ACOMPANHAMENTO);
        when(productGateway.findById(productInput.id())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFound.class, () -> editProductUseCase.execute(productInput)); // Verifica se a exceção é lançada
        verify(productGateway).findById(productInput.id()); // Verifica se o findById foi chamado
        verify(productGateway, never()).save(any(Product.class)); // Garante que o save não foi chamado
    }
}
