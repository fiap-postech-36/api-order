package br.com.nomeempresa.restaurante.application.inout.mapper;

import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.output.OrderOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
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
    default List<Product> mapProductIdToProductItems(final List<Long>  productsId) {
        return productsId.stream()
            .map(this::mapProductIdToProductItem)
            .collect(Collectors.toList());
    }

    default Product mapProductIdToProductItem(final Long id) {
        return new Product(
                id,
                null,
                null,
                null,
                null,
                null);
    }

}
