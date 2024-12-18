package br.com.order.infra.mapper;

import br.com.order.infra.entity.ProductEntity;
import br.com.order.infra.entity.OrderEntity;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderEntityToOrder(final OrderEntity orderEntity);
    @Mapping(source = "products", target = "products", qualifiedByName = "mapProdutosToProdutoItems")
    OrderEntity orderToOrderEntity(final Order order);

    @Named("mapProdutosToProdutoItems")
    default List<ProductEntity> mapProdutosToProdutoItems(List<Product> produtos) {
        return produtos.stream()
            .map(this::mapProdutoToProdutoItem)
            .collect(Collectors.toList());
    }

    default ProductEntity mapProdutoToProdutoItem(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getUrlImage(),
                product.getPrice(),
                product.getCategory());
    }

}
