package br.com.order.application.usecase.order;

import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetByIdOrderUseCase implements UseCase<Long, Order> {

    private final OrderGateway orderGateway;

    @Override
    public Optional<Order> execute(final Long id) {
        return Optional.ofNullable(orderGateway.findById(id).orElseThrow(RuntimeException::new));
    }
}
