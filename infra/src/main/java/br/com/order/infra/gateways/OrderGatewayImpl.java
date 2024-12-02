package br.com.order.infra.gateways;

import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.gateway.OrderGateway;
import br.com.order.infra.mapper.OrderMapper;
import br.com.order.infra.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper = OrderMapper.INSTANCE;

    @Override
    public Optional<Order> save(final Order order) {
        final var orderEntity = mapper.orderToOrderEntity(order);
        return Optional.ofNullable(mapper.orderEntityToOrder(orderRepository.save(orderEntity)));
    }

    @Override
    public Optional<Order> update(final Order order) {
        return Optional.empty();
    }

    @Override
    public void remove(final Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(final Long id) {
        return Optional.ofNullable(mapper.orderEntityToOrder(orderRepository.findById(id).orElse(null)));
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::orderEntityToOrder)
                .toList();
    }
}
