package br.com.order.infra.entity;

import br.com.order.domain.core.domain.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductEntityTest {

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        productEntity = new ProductEntity();
    }

    @Test
    void testGettersAndSetters() {
        productEntity.setId(1L);
        productEntity.setName("Test Product");
        productEntity.setDescription("This is a test description for a product.");
        productEntity.setUrlImage("http://example.com/image.jpg");
        productEntity.setPrice(BigDecimal.valueOf(19.99));
        productEntity.setCategory(Category.SOBREMESA);

        assertEquals(1L, productEntity.getId());
        assertEquals("Test Product", productEntity.getName());
        assertEquals("This is a test description for a product.", productEntity.getDescription());
        assertEquals("http://example.com/image.jpg", productEntity.getUrlImage());
        assertEquals(BigDecimal.valueOf(19.99), productEntity.getPrice());
        assertEquals(Category.SOBREMESA, productEntity.getCategory());
    }

    @Test
    void testNoArgsConstructor() {
        ProductEntity newProductEntity = new ProductEntity();

        assertNull(newProductEntity.getId());
        assertNull(newProductEntity.getName());
        assertNull(newProductEntity.getDescription());
        assertNull(newProductEntity.getUrlImage());
        assertNull(newProductEntity.getPrice());
        assertNull(newProductEntity.getCategory());
    }

    @Test
    void testAllArgsConstructor() {
        ProductEntity newProductEntity = new ProductEntity(
                1L,
                "Test Product",
                "This is a test description for a product.",
                "http://example.com/image.jpg",
                BigDecimal.valueOf(19.99),
                Category.SOBREMESA
        );

        assertEquals(1L, newProductEntity.getId());
        assertEquals("Test Product", newProductEntity.getName());
        assertEquals("This is a test description for a product.", newProductEntity.getDescription());
        assertEquals("http://example.com/image.jpg", newProductEntity.getUrlImage());
        assertEquals(BigDecimal.valueOf(19.99), newProductEntity.getPrice());
        assertEquals(Category.SOBREMESA, newProductEntity.getCategory());
    }
}
