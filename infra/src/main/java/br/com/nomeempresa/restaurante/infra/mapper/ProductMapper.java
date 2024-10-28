package br.com.nomeempresa.restaurante.infra.mapper;

import br.com.nomeempresa.restaurante.infra.entity.ProductEntity;
import br.com.order.domain.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productEntityToProduct(final ProductEntity productEntity);

    ProductEntity productToProductEntity(final Product product);

}
