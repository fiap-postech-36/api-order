package br.com.order.application.usecase.order;


import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.mapper.OrderInputOutputMapper;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditOrderUseCase implements UseCase<OrderInput, Order> {

    private final OrderGateway orderGateway;
    private final ModelMapper modelMapper;
    @Override
    public Optional<Order> execute(final OrderInput orderInput) {
        final var order = orderGateway.findById(orderInput.id());
        final var orderEdit = OrderInputOutputMapper.INSTANCE.orderRequestToOrder(orderInput);
        modelMapper.map(orderEdit, order);
        return order;
    }
}
