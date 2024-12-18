package br.com.order.application.usecase.order;

import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.mapper.OrderInputOutputMapper;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase implements UseCase<OrderInput, Order> {

    private final OrderGateway orderGateway;

    @Override
    public Optional<Order> execute(final OrderInput productInput) {
        final var order = OrderInputOutputMapper.INSTANCE.orderRequestToOrder(productInput);
        order.seInitialStatus();
        return orderGateway.save(order);
    }
}
