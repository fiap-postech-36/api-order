package br.com.order.application.usecase.order;

import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderRecivedUseCase implements UseCase<Integer, Order> {

    private final OrderGateway orderGateway;

    @Override
    public Optional<Order> execute(Integer orderId) {
        Order order = orderGateway.findById(Long.valueOf(orderId)).orElse(null);
        if (order == null) {
            return Optional.empty();
        }
        order.setReceived();
        return orderGateway.save(order);
    }
}
