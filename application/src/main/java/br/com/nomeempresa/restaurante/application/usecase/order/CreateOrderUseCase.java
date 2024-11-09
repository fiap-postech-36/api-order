package br.com.nomeempresa.restaurante.application.usecase.order;

import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.OrderInputOutputMapper;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
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
