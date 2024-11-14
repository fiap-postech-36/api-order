package br.com.order.infra.gateways;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.infra.entity.ProductEntity;
import br.com.order.infra.mapper.ProductMapper;
import br.com.order.infra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductGatewayImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductGatewayImpl productGateway;

    private Product product;
    private ProductEntity productEntity;
    private static final Long PRODUCT_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Product A", "Description A", "Url A", new BigDecimal("100.0"), Category.ACOMPANHAMENTO); // Configure o produto conforme necessário
        productEntity = ProductMapper.INSTANCE.productToProductEntity(product);
    }

    @Test
    void save_shouldSaveAndReturnProduct() {
        when(productRepository.save(any())).thenReturn(productEntity);

        Optional<Product> result = productGateway.save(product);

        assertTrue(result.isPresent());
        verify(productRepository).save(any());
    }

    @Test
    void update_shouldUpdateAndReturnProduct() {
        when(productRepository.save(any())).thenReturn(productEntity);

        Optional<Product> result = productGateway.update(product);

        assertTrue(result.isPresent());
        verify(productRepository).save(any());
    }

    @Test
    void delete_shouldDeleteProductById() {
        productGateway.delete(PRODUCT_ID);

        verify(productRepository).deleteById(PRODUCT_ID);
    }

    @Test
    void findById_shouldReturnProductIfFound() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(productEntity));

        Optional<Product> result = productGateway.findById(PRODUCT_ID);

        assertTrue(result.isPresent());
        verify(productRepository).findById(PRODUCT_ID);
    }

    @Test
    void filterByCategoryAndName_shouldReturnFilteredProducts() {
        String category = "Electronics";
        String name = "Phone";
        when(productRepository.filter(name, category)).thenReturn(List.of(productEntity));

        Collection<Product> result = productGateway.filterByCategoryAndName(category, name);

        assertEquals(1, result.size());
        verify(productRepository).filter(name, category);
    }

    @Test
    void findAll_shouldReturnAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(productEntity));

        Collection<Product> result = productGateway.findAll();

        assertEquals(1, result.size());
        verify(productRepository).findAll();
    }
}
