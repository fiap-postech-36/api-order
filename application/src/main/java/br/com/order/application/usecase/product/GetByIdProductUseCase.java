package br.com.order.application.usecase.product;

import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
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
