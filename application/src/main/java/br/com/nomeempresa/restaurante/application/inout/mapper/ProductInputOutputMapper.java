package br.com.nomeempresa.restaurante.application.inout.mapper;

import br.com.nomeempresa.restaurante.application.inout.input.ProductInput;
import br.com.nomeempresa.restaurante.application.inout.output.ProductOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductInputOutputMapper {
    ProductInputOutputMapper INSTANCE = Mappers.getMapper(ProductInputOutputMapper.class);

    Product productRequestToProduct(final ProductInput productInput);
    ProductOutput productToProductResponse(final Product product);

    Product productInputToProduct(final ProductInput productInput);


}
