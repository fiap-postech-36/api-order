package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.facade.ProductFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.ProductInput;
import br.com.nomeempresa.restaurante.application.inout.output.ProductOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductFacade productFacade;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        ProductInput productInput = new ProductInput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null);
        ProductOutput productOutput = new ProductOutput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null);

        when(productFacade.create(productInput)).thenReturn(productOutput);

        ResponseEntity<ProductOutput> response = productController.saveProduct(productInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productOutput, response.getBody());
        verify(productFacade, times(1)).create(productInput);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productFacade, times(1)).delete(productId);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductInput productInput = new ProductInput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null);
        ProductOutput updatedProductOutput = new ProductOutput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null);

        when(productFacade.update(any(ProductInput.class))).thenReturn(updatedProductOutput);

        ResponseEntity<ProductOutput> response = productController.updateProduct(productId, productInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProductOutput, response.getBody());
        verify(productFacade, times(1)).update(any(ProductInput.class));
    }

    @Test
    void testFindById() throws URISyntaxException {
        Long productId = 1L;
        ProductOutput productOutput = new ProductOutput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null);

        when(productFacade.get(productId)).thenReturn(productOutput);

        ResponseEntity<ProductOutput> response = productController.findByID(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productOutput, response.getBody());
        verify(productFacade, times(1)).get(productId);
    }

    @Test
    void testFindAll() {
        Page<ProductOutput> page = new PageImpl<>(List.of(new ProductOutput(1L, "name", "description", "urlImage", new BigDecimal("10.0"), null)));

        when(productFacade.filter(any(FilterInput.class))).thenReturn(page);

        Page<ProductOutput> response = productController.findAll(Map.of());

        assertEquals(page, response);
        verify(productFacade, times(1)).filter(any(FilterInput.class));
    }
}
