package br.com.order.domain.application.core.domain.entities;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductConstructor() {
        // Criando uma instância de Product
        Product product = new Product(1L, "Coke", "Refrigerante", "https://image.com", BigDecimal.valueOf(5.99), Category.BEBIDA);

        // Testando a criação correta do objeto
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Coke", product.getName());
        assertEquals("Refrigerante", product.getDescription());
        assertEquals("https://image.com", product.getUrlImage());
        assertEquals(BigDecimal.valueOf(5.99), product.getPrice());
        assertEquals(Category.BEBIDA, product.getCategory());
    }

    @Test
    void testProductHashCode() {
        // Testando o método hashCode
        Product product1 = new Product(1L, "Coke", "Refrigerante", "http://image.com", BigDecimal.valueOf(5.99), Category.BEBIDA);
        Product product2 = new Product(1L, "Coke", "Refrigerante", "http://image.com", BigDecimal.valueOf(5.99), Category.BEBIDA);

        assertEquals(product1.hashCode(), product2.hashCode()); // Como são iguais, os hashCodes também devem ser iguais
    }

    @Test
    void testProductNotEqualToNull() {
        // Testando que um produto não deve ser igual a null
        Product product = new Product(1L, "Coke", "Refrigerante", "http://image.com", BigDecimal.valueOf(5.99), Category.BEBIDA);
        assertNotEquals(product, null);
    }

    @Test
    void testProductNotEqualToDifferentClass() {
        // Testando que um produto não deve ser igual a objetos de classe diferente
        Product product = new Product(1L, "Coke", "Refrigerante", "http://image.com", BigDecimal.valueOf(5.99), Category.BEBIDA);
        assertNotEquals(product, "Some String");
    }
}
