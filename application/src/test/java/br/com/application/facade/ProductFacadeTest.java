package br.com.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.facade.ProductFacade;
import br.com.order.application.inout.output.ProductOutput;
import br.com.order.application.usecase.product.DeleteProductUseCase;
import br.com.order.application.usecase.product.GetByIdProductUseCase;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductFacadeTest {

    @Mock
    private DeleteProductUseCase deleteProductUseCase;

    @Mock
    private GetByIdProductUseCase getByIdProductUseCase;

    @InjectMocks
    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;

        productFacade.delete(id);

        verify(deleteProductUseCase, times(1)).execute(id);
    }

    @Test
    void testGetProductById_Success2() {
        Long id = 1L;
        Product product = new Product(4L, "Test Product", "descricao do produto", "url da img", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        when(getByIdProductUseCase.execute(id)).thenReturn(Optional.of(product));

        // Converta `Product` para `ProductOutput` usando o mapper, se necessário
        ProductOutput result = productFacade.get(id);

        assertNotNull(result);
        verify(getByIdProductUseCase, times(1)).execute(id);
    }

    @Test
    void testGetProductById_Success3() {
        Long id = 1L;
        Product product = new Product(4L, "Test Product", "descricao do produto", "url da img", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        when(getByIdProductUseCase.execute(id)).thenReturn(Optional.of(product));

        ProductOutput result = productFacade.get(id);

        assertNotNull(result);
        verify(getByIdProductUseCase, times(1)).execute(id);
    }

    @Test
    void testGetProductById_NotFound() {
        Long id = 1L;
        when(getByIdProductUseCase.execute(id)).thenReturn(Optional.empty());

        assertThrows(NoResourceFoundException.class, () -> productFacade.get(id));
        verify(getByIdProductUseCase, times(1)).execute(id);
    }

    @Test
    void testDelete() {
        Long productId = 1L;

        productFacade.delete(productId);

        verify(deleteProductUseCase).execute(productId);
    }

    @Test
    void testGet() {
        Long productId = 1L;
        Product productOutput = new Product(2L, "Test Product", "descricao do produto", "url da img", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);

        when(getByIdProductUseCase.execute(productId)).thenReturn(Optional.<Product>of(productOutput));

        ProductOutput result = productFacade.get(productId);

        assertNotNull(result);
        verify(getByIdProductUseCase).execute(productId);
    }

    @Test
    void testGet_NoResourceFound() {
        Long productId = 1L;

        when(getByIdProductUseCase.execute(productId)).thenReturn(Optional.empty());

        assertThrows(NoResourceFoundException.class, () -> productFacade.get(productId));
        verify(getByIdProductUseCase).execute(productId);
    }
}

