package br.com.nomeempresa.restaurante.application.usecase.order;

import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.OrderInputOutputMapper;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
import br.com.nomeempresa.restaurante.infra.mapper.OrderMapper;
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
