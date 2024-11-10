package br.com.order.application.inout.mapper;


import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.output.OrderOutput;
import br.com.order.domain.core.domain.Order;
import br.com.order.domain.core.domain.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderInputOutputMapper {
    OrderInputOutputMapper INSTANCE = Mappers.getMapper(OrderInputOutputMapper.class);

    @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsItemsToProducts")
    Order orderRequestToOrder(final OrderInput orderInput);

    @Mapping(source = "products", target = "items")
    OrderOutput orderToOrderResponse(final Order order);

    @Named("mapProductsItemsToProducts")
    default List<Product> mapProductIdToProductItems(final List<Long> productsId) {
        return productsId.stream().map(this::mapProductIdToProductItem).collect(Collectors.toList());
    }

    default Product mapProductIdToProductItem(final Long id) {
        return new Product(id, null, null, null, null, null);
    }

}
