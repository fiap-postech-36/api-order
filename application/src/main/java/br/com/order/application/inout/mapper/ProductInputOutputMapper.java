package br.com.order.application.inout.mapper;

import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.output.ProductOutput;
import br.com.order.domain.core.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductInputOutputMapper {
    ProductInputOutputMapper INSTANCE = Mappers.getMapper(ProductInputOutputMapper.class);

    ProductOutput productToProductResponse(final Product product);

    Product productInputToProduct(final ProductInput productInput);

}
