package br.com.order.application.usecase.order;


import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.Order;
import br.com.order.domain.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterOrderUseCase implements UseCase<FilterInput, Page<Order>> {

    private final OrderGateway orderGateway;

    @Override
    public Optional<Page<Order>> execute(final FilterInput filterInput) {
        final List<Order> orders = (List<Order>) orderGateway.findByPriority();
        return Optional.of(new PageImpl<>(orders));
    }
}
