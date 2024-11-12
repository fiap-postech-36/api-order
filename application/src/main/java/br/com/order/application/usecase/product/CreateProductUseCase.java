package br.com.order.application.usecase.product;

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
public class CreateProductUseCase implements UseCase<ProductInput, Product> {

    private final ProductGateway productGateway;

    @Override
    public Optional<Product> execute(final ProductInput productInput) {
        return productGateway.save(ProductInputOutputMapper.INSTANCE.productInputToProduct(productInput));
    }
}
