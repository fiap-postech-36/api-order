package br.com.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.facade.ProductFacade;
import br.com.order.application.usecase.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
    void testGetProductById_NotFound() {
        Long id = 1L;
        when(getByIdProductUseCase.execute(id)).thenReturn(Optional.empty());

        assertThrows(NoResourceFoundException.class, () -> productFacade.get(id));
        verify(getByIdProductUseCase, times(1)).execute(id);
    }
}

