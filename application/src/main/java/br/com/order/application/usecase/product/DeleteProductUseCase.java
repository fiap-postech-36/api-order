package br.com.order.application.usecase.product;

import br.com.order.application.usecase.UseCase;
import br.com.order.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase implements UseCase<Long, Void> {

    private final ProductGateway productGateway;

    @Override
    public Optional<Void> execute(final Long id) {
        productGateway.delete(id);
        return Optional.empty();
    }
}
