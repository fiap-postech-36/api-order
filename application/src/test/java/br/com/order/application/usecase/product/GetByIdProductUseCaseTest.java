package br.com.order.application.usecase.product;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Inicializa os mocks para o JUnit 5
class GetByIdProductUseCaseTest {

    @Mock
    private ProductGateway productGateway; // Mock do ProductGateway

    @InjectMocks
    private GetByIdProductUseCase getByIdProductUseCase; // O use case que estamos testando

    @Test
    void testExecute_shouldReturnProduct() {
        // Arranjo: criando um produto mockado
        Product product = new Product(1L, "Product A", "Description A", "Url A", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        when(productGateway.findById(1L)).thenReturn(Optional.of(product)); // Mockando o retorno do gateway

        // Ação: chamando o método execute
        Optional<Product> result = getByIdProductUseCase.execute(1L);

        // Afirmação: verificando se o resultado não é vazio e o produto retornado é o esperado
        assertTrue(result.isPresent(), "O produto não foi encontrado.");
        assertEquals("Product A", result.get().getName(), "O nome do produto não está correto.");
        assertEquals(Category.ACOMPANHAMENTO, result.get().getCategory(), "A categoria do produto não está correta.");

        // Verificando se o método findById foi chamado com o ID correto
        verify(productGateway, times(1)).findById(1L);
    }

    @Test
    void testExecute_shouldThrowExceptionWhenProductNotFound() {
        // Arranjo: mockando a ausência do produto
        when(productGateway.findById(1L)).thenReturn(Optional.empty());

        // Ação e Afirmação: verificando se o método lança uma exceção quando o produto não é encontrado
        assertThrows(RuntimeException.class, () -> getByIdProductUseCase.execute(1L), "Esperado que uma exceção seja lançada.");

        // Verificando se o método findById foi chamado com o ID correto
        verify(productGateway, times(1)).findById(1L);
    }
}
