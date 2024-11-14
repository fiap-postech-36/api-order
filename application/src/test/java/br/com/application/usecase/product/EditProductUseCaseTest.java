package br.com.application.usecase.product;

import br.com.order.application.exception.ResourceNotFound;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.mapper.ProductInputOutputMapper;
import br.com.order.application.usecase.product.EditProductUseCase;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private EditProductUseCase editProductUseCase;

    private ProductInput productInput;
    private Product existingProduct;

    @BeforeEach
    void setUp() {
        // Inicializando os objetos necessários para os testes
        productInput = new ProductInput(1L, "Updated Product", "Updated Description", "https://updatedurl.com", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        existingProduct = new Product(1L, "Existing Product", "Existing Description", "https://existingurl.com", new BigDecimal("50.0"), Category.SOBREMESA);
    }

    @Test
    void testExecute_shouldReturnUpdatedProduct() {
        // Arranjo: mockando o comportamento do gateway para retornar um produto existente
        when(productGateway.findById(productInput.id())).thenReturn(Optional.of(existingProduct));
        when(productGateway.save(any(Product.class))).thenReturn(Optional.of(new Product(1L, "Updated Product", "Updated Description", "http://updatedurl.com", new BigDecimal("100.0"), Category.ACOMPANHAMENTO)));

        // Ação: chamando o método execute
        Optional<Product> result = editProductUseCase.execute(productInput);

        // Afirmação: verificando se o produto foi atualizado corretamente
        assertTrue(result.isPresent(), "O resultado não deve ser vazio.");
        assertEquals("Updated Product", result.get().getName(), "O nome do produto não foi atualizado corretamente.");

        // Comparando os preços convertendo o valor esperado para BigDecimal
        assertEquals(new BigDecimal("100.0"), result.get().getPrice(), "O preço do produto não foi atualizado corretamente.");
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

    @Test
    void testExecute_shouldThrowResourceNotFoundWhenProductDoesNotExist() {
        // Arranjo: mockando o comportamento do gateway para retornar um produto não encontrado
        when(productGateway.findById(productInput.id())).thenReturn(Optional.empty());

        // Ação: chamando o método execute e verificando se a exceção ResourceNotFound é lançada
        assertThrows(ResourceNotFound.class, () -> editProductUseCase.execute(productInput), "Deveria lançar ResourceNotFound quando o produto não for encontrado.");
    }
}
