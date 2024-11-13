package br.com.order.application.usecase.product;

import br.com.order.application.usecase.product.DeleteProductUseCase;
import br.com.order.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) // Inicializa os mocks do Mockito
class DeleteProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private DeleteProductUseCase deleteProductUseCase; // O Mockito injeta o mock aqui

    private Long productId;

    @BeforeEach
    void setUp() {
        // Inicializando o ID do produto que será usado nos testes
        productId = 1L;
    }

    @Test
    void testExecute_shouldDeleteProduct() {
        // Ação: chamando o método execute
        Optional<Void> result = deleteProductUseCase.execute(productId);

        // Afirmação: verificar se o método delete foi chamado com o ID correto
        verify(productGateway, times(1)).delete(productId);

        // Afirmação: verificar se o retorno é Optional.empty()
        assertTrue(result.isEmpty(), "O resultado deve ser vazio após a execução.");
    }
}
