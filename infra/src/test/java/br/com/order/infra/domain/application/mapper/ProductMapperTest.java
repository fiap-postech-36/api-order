package br.com.order.infra.domain.application.mapper;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.infra.entity.ProductEntity;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.infra.mapper.ProductMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Test
    void testProductEntityToProduct() {
        // Cria um ProductEntity de teste
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Product Test");
        productEntity.setPrice(new BigDecimal("100.0"));
        productEntity.setDescription("Description Test");

        // Mapeia para Product
        Product product = mapper.productEntityToProduct(productEntity);

        // Valida o mapeamento
        assertNotNull(product);
        assertEquals(productEntity.getId(), product.getId());
        assertEquals(productEntity.getName(), product.getName());
        assertEquals(productEntity.getPrice(), product.getPrice());
        assertEquals(productEntity.getDescription(), product.getDescription());
    }

    @Test
    void testProductToProductEntity() {
        // Cria um Product de teste
        Product product = new Product(3L, "Product Test", "lanche", "url", new BigDecimal("100.0"), Category.LANCHE);

        // Mapeia para ProductEntity
        ProductEntity productEntity = mapper.productToProductEntity(product);

        // Valida o mapeamento
        assertNotNull(productEntity);
        assertEquals(product.getId(), productEntity.getId());
        assertEquals(product.getName(), productEntity.getName());
        assertEquals(product.getPrice(), productEntity.getPrice());
        assertEquals(product.getDescription(), productEntity.getDescription());
    }
}
