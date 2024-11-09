package br.com.nomeempresa.restaurante.application.usecase.order;

import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
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
