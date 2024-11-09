package br.com.nomeempresa.restaurante.application.usecase.order;

import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
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
