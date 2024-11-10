package br.com.order.application.usecase.product;


import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.mapper.ProductInputOutputMapper;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.Product;
import br.com.order.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditProductUseCase implements UseCase<ProductInput, Product> {
    private final ProductGateway productGateway;
    private final ModelMapper modelMapper;

    @Override
    public Optional<Product> execute(final ProductInput productInput) throws Exception {
        productGateway.findById(productInput.id()).orElseThrow(Exception::new);
        return productGateway.save(ProductInputOutputMapper.INSTANCE.productInputToProduct(productInput));
    }
}
