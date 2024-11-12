package br.com.order.application.controllers;

import br.com.order.application.facade.ProductFacade;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.output.ProductOutput;
import br.com.order.domain.core.domain.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        ProductInput productInput = new ProductInput(1L, "Product Name", "Description", "http://url.com", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);
        ProductOutput productOutput = new ProductOutput(1L, "Product Name", "Description", "http://url.com", new BigDecimal("100.0"), Category.ACOMPANHAMENTO);

        when(productFacade.create(any(ProductInput.class))).thenReturn(productOutput);

        ResponseEntity<ProductOutput> response = productController.saveProduct(productInput);

        assertEquals(ResponseEntity.ok(productOutput), response);
        verify(productFacade, times(1)).create(productInput);
    }

    @Test
    void testDeleteProduct() {
        Long idProduct = 1L;

        doNothing().when(productFacade).delete(idProduct);

        ResponseEntity<Void> response = productController.deleteProduct(idProduct);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(productFacade, times(1)).delete(idProduct);
    }

    @Test
    void testUpdateProduct() {
        Long id = 1L;
        ProductInput productInput = new ProductInput(id, "Updated Name", "Updated Description", "http://updatedurl.com", new BigDecimal("100.0"), Category.SOBREMESA);
        ProductOutput productOutput = new ProductOutput(id, "Updated Name", "Updated Description", "http://updatedurl.com", new BigDecimal("100.0"), Category.SOBREMESA);

        when(productFacade.update(any(ProductInput.class))).thenReturn(productOutput);

        ResponseEntity<ProductOutput> response = productController.updateProduct(id, productInput);

        assertEquals(ResponseEntity.ok(productOutput), response);
        verify(productFacade, times(1)).update(any(ProductInput.class));
    }

    @Test
    void testFindById() throws URISyntaxException {
        Long id = 1L;
        ProductOutput productOutput = new ProductOutput(id, "Product Name", "Description", "https://url.com", new BigDecimal("100.0"), Category.SOBREMESA);

        when(productFacade.get(id)).thenReturn(productOutput);

        ResponseEntity<ProductOutput> response = productController.findByID(id);

        assertEquals(ResponseEntity.ok(productOutput), response);
        verify(productFacade, times(1)).get(id);
    }

    @Test
    void testFindAll() {
        Map<String, String> filter = Collections.emptyMap();
        Page<ProductOutput> productOutputPage = new PageImpl<>(Collections.singletonList(
                new ProductOutput(1L, "Product Name", "Description", "https://url.com", new BigDecimal("100.0"), Category.SOBREMESA)
        ));

        when(productFacade.filter(any(FilterInput.class))).thenReturn(productOutputPage);

        Page<ProductOutput> response = productController.findAll(filter);

        assertEquals(productOutputPage, response);
        verify(productFacade, times(1)).filter(any(FilterInput.class));
    }
}

