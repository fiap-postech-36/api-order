package br.com.order.infra.mapper;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.infra.entity.OrderEntity;
import br.com.order.infra.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderMapperTest {

    private OrderMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(OrderMapper.class);
    }

    @Test
    void testOrderEntityToOrder() {
        // Configuração do OrderEntity para teste
        ProductEntity productEntity = new ProductEntity(1L, "Product A", "Description A", "urlA", BigDecimal.valueOf(10.0), Category.ACOMPANHAMENTO);
        OrderEntity orderEntity = new OrderEntity(1L, LocalDateTime.now(), LocalDateTime.now(), List.of(productEntity));

        // Execução do mapeamento
        Order order = mapper.orderEntityToOrder(orderEntity);

        // Verificações
        assertNotNull(order);
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getProducts().size(), order.getProducts().size());
        assertEquals(orderEntity.getProducts().get(0).getName(), order.getProducts().get(0).getName());
    }

    @Test
    void testOrderToOrderEntity() {
        // Configuração do Order para teste
        Product product = new Product(1L, "Product A", "Description A", "urlA", BigDecimal.valueOf(10.0), Category.ACOMPANHAMENTO);
        Order order = new Order(1L, LocalDateTime.now(), LocalDateTime.now(), List.of(product));

        // Execução do mapeamento
        OrderEntity orderEntity = mapper.orderToOrderEntity(order);

        // Verificações
        assertNotNull(orderEntity);
        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getProducts().size(), orderEntity.getProducts().size());
        assertEquals(order.getProducts().get(0).getName(), orderEntity.getProducts().get(0).getName());
    }

    @Test
    void testMapProdutosToProdutoItems() {
        // Configuração dos produtos
        Product product = new Product(1L, "Product A", "Description A", "urlA", BigDecimal.valueOf(10.0), Category.ACOMPANHAMENTO);

        // Execução do mapeamento
        List<ProductEntity> productEntities = mapper.mapProdutosToProdutoItems(List.of(product));

        // Verificações
        assertNotNull(productEntities);
        assertEquals(1, productEntities.size());
        assertEquals(product.getName(), productEntities.get(0).getName());
    }
}
