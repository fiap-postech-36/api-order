package br.com.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.facade.ProductFacade;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.output.ProductOutput;
import br.com.order.application.usecase.product.*;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductFacadeTest {

    @Mock
    private DeleteProductUseCase deleteProductUseCase;

    @Mock
    private GetByIdProductUseCase getByIdProductUseCase;

    @Mock
    private CreateProductUseCase createProductUseCase;

    @Mock
    private EditProductUseCase editProductUseCase;

    @Mock
    private FilterProductUseCase filterProductUseCase;

    @InjectMocks
    private ProductFacade productFacade;

    private ProductInput productInput;

    @BeforeEach
    void setUp() {
        productInput = new ProductInput(
                1L,
                "Produto de Teste",
                "Descrição do produto de teste",
                "http://example.com/imagem-do-produto.jpg",
                new BigDecimal("99.99"),
                Category.ACOMPANHAMENTO
        );
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_Success() {
        Product product = new Product(1L, "Test Product", "Product description", "http://example.com/product.jpg", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        when(createProductUseCase.execute(productInput)).thenReturn(Optional.of(product));

        ProductOutput result = productFacade.create(productInput);

        assertNotNull(result);
        assertEquals("Product description", result.description());
        verify(createProductUseCase, times(1)).execute(productInput);
    }

    @Test
    void testCreateProduct_Failure() {
        ProductInput productInput = new ProductInput(
                1L, // ID do produto
                "Produto de Teste", // Nome do produto
                "Descrição do produto de teste", // Descrição do produto
                "http://example.com/imagem-do-produto.jpg", // URL da imagem
                new BigDecimal("99.99"), // Preço do produto
                Category.ACOMPANHAMENTO // Categoria do produto
        );
        when(createProductUseCase.execute(productInput)).thenReturn(Optional.empty());

        ProductOutput result = productFacade.create(productInput);

        assertNull(result);
        verify(createProductUseCase, times(1)).execute(productInput);
    }

    @Test
    void testUpdateProduct_Success() {
        Product updatedProduct = new Product(1L, "Updated Product", "Updated description", "http://example.com/updated_product.jpg", new BigDecimal("150.0"), Category.ACOMPANHAMENTO);
        when(editProductUseCase.execute(Mockito.any())).thenReturn(Optional.of(updatedProduct));

        ProductOutput result = productFacade.update(productInput);

        assertNotNull(result);
        assertEquals("Updated description", result.description());
        verify(editProductUseCase, times(1)).execute(productInput);
    }

    @Test
    void testUpdateProduct_Failure() {
        when(editProductUseCase.execute(Mockito.any())).thenReturn(Optional.empty());

        ProductOutput result = productFacade.update(productInput);

        assertNull(result);
        verify(editProductUseCase, times(1)).execute(productInput);
    }

    @Test
    void testFilterProducts_NoResults() {
        FilterInput filterInput = new FilterInput(Map.of("category", "NON_EXISTENT_CATEGORY"));
        when(filterProductUseCase.execute(filterInput)).thenReturn(Optional.of(Page.empty()));

        Page<ProductOutput> result = productFacade.filter(filterInput);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(filterProductUseCase, times(1)).execute(filterInput);
    }

    @Test
    void testGetProductById_Exception() {
        Long id = 999L;
        when(getByIdProductUseCase.execute(id)).thenThrow(NoResourceFoundException.class);

        assertThrows(NoResourceFoundException.class, () -> productFacade.get(id));
        verify(getByIdProductUseCase, times(1)).execute(id);
    }

    @Test
    void testDeleteProduct_Success() {
        Long productId = 1L;

        productFacade.delete(productId);

        verify(deleteProductUseCase, times(1)).execute(productId);
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

