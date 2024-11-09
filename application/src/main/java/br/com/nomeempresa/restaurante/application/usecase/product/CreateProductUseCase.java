package br.com.nomeempresa.restaurante.application.usecase.product;

import br.com.nomeempresa.restaurante.application.inout.input.ProductInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.ProductInputOutputMapper;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;
import br.com.nomeempresa.restaurante.domain.gateway.ProductGateway;
import br.com.nomeempresa.restaurante.infra.mapper.ProductMapper;
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
