package br.com.order.application.usecase.order;

import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusUseCase implements UseCase<Order, Order> {

    private final OrderGateway orderGateway;

    @Override
    public Optional<Order> execute(Order order) {
        order.nextStepOrder();
        return orderGateway.save(order);
    }
}
