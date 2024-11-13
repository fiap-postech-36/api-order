package br.com.order.application.usecase.product;

import br.com.order.application.inout.input.FilterInput;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Garante que o Mockito seja inicializado corretamente
class FilterProductUseCaseTest {

    @Mock
    private ProductGateway productGateway; // Mock do ProductGateway

    @InjectMocks
    private FilterProductUseCase filterProductUseCase; // Injeção do use case

    @Test
    void testExecute_shouldReturnFilteredProducts() {
        // Arranjo: preparando os dados de filtro e os produtos mockados
        Map<String, String> filters = new HashMap<>();
        filters.put("name", "Product A");
        filters.put("category", "Category 1");

        FilterInput filterInput = new FilterInput(filters);

        // Mockando o comportamento do productGateway para retornar uma lista de produtos filtrados
        List<Product> filteredProducts = List.of(new Product(1L, "Product A", "Description A", "Url A", new BigDecimal("100.0"), Category.ACOMPANHAMENTO));
        when(productGateway.filterByCategoryAndName("Category 1", "Product A")).thenReturn(filteredProducts);

        // Ação: chamando o método execute
        Optional<Page<Product>> result = filterProductUseCase.execute(filterInput);

        // Afirmação: verificando se o resultado não é vazio e contém os produtos esperados
        assertTrue(result.isPresent(), "O resultado não deve ser vazio.");
        Page<Product> pageResult = result.get();
        assertEquals(1, pageResult.getTotalElements(), "O número de elementos não corresponde ao esperado.");
        assertEquals("Product A", pageResult.getContent().get(0).getName(), "O nome do produto não corresponde ao esperado.");
        assertEquals(Category.ACOMPANHAMENTO, pageResult.getContent().get(0).getCategory(), "A categoria do produto não corresponde ao esperado.");

        // Verificando se o método do productGateway foi chamado com os parâmetros corretos
        verify(productGateway, times(1)).filterByCategoryAndName("Category 1", "Product A");
    }
}
