package br.com.nomeempresa.restaurante.application.usecase.product;

import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;
import br.com.nomeempresa.restaurante.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetByIdProductUseCase implements UseCase<Long, Product> {
    private final ProductGateway productGateway;
    @Override
    public Optional<Product> execute(Long id) {
        final var product = productGateway.findById(id).orElseThrow(RuntimeException::new);
        return Optional.ofNullable(product);
    }
}
