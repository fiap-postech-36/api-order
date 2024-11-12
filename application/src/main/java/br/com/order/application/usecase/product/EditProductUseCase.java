package br.com.order.application.usecase.product;

import br.com.order.application.exception.ResourceNotFound;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.mapper.ProductInputOutputMapper;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditProductUseCase implements UseCase<ProductInput, Product> {
    private final ProductGateway productGateway;

    @Override
    public Optional<Product> execute(final ProductInput productInput) {
        productGateway.findById(productInput.id()).orElseThrow(ResourceNotFound::new);

        return productGateway.save(ProductInputOutputMapper.INSTANCE.productInputToProduct(productInput));
    }
}
