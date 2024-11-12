package br.com.order.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.mapper.ProductInputOutputMapper;
import br.com.order.application.inout.output.ProductOutput;
import br.com.order.application.usecase.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFacade {

    private final CreateProductUseCase createProductUseCase;
    private final EditProductUseCase editProductUseCase;
    private final FilterProductUseCase filterProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetByIdProductUseCase getByIdProductUseCase;

    public ProductOutput create(final ProductInput productInput) {
        final var productOutPut = createProductUseCase.execute(productInput);
        return ProductInputOutputMapper.INSTANCE.productToProductResponse(productOutPut.orElse(null));
    }

    public ProductOutput update(final ProductInput productInput) {
        final var productOutPut = editProductUseCase.execute(productInput);
        return ProductInputOutputMapper.INSTANCE.productToProductResponse(productOutPut.orElse(null));
    }

    public Page<ProductOutput> filter(final FilterInput filterInput) {
        final var page = filterProductUseCase.execute(filterInput).orElse(Page.empty());
        final var content = page.getContent().stream()
            .map(ProductInputOutputMapper.INSTANCE::productToProductResponse)
            .toList();

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public void delete(final Long id) {
        deleteProductUseCase.execute(id);
    }

    public ProductOutput get(final Long id) {
        return ProductInputOutputMapper.INSTANCE.productToProductResponse(getByIdProductUseCase.execute(id)
            .orElseThrow(NoResourceFoundException::new));
    }

}
