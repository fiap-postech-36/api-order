package br.com.order.usecase.product;

import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.usecase.product.CreateProductUseCase;
import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class CreateProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    private ProductInput productInput;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configuração de exemplo para o ProductInput e Product
        productInput = new ProductInput(2L, "Test Product", "descricao do produto", "url da img", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        product = new Product(3L, "Test Product", "descricao do produto","url da img", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);

    }

    @Test
    void testExecute_shouldReturnProduct() {
        // Arranjo: mockando o comportamento do gateway
        when(productGateway.save(Mockito.any(Product.class))).thenReturn(Optional.of(product));

        // Ação: chamando o método execute
        Optional<Product> result = createProductUseCase.execute(productInput);

        // Afirmação: verificando se o resultado é o esperado
        assertTrue(result.isPresent(), "O resultado não deve ser vazio.");
        assertTrue(result.get().getId() > 0, "O ID do produto deve ser maior que 0.");
        Assertions.assertEquals("Test Product", result.get().getName(), "O nome do produto não está correto.");
    }
}
