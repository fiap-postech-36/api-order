package br.com.order.application.facade;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.infra.RabbitMQConfig;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.mapper.OrderInputOutputMapper;
import br.com.order.application.inout.output.OrderOutput;
import br.com.order.application.inout.output.OrderRabbitOutput;
import br.com.order.application.usecase.order.*;
import br.com.order.domain.core.domain.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final EditOrderUseCase editOrderUseCase;
    private final FilterOrderUseCase filterOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final GetByIdOrderUseCase getByIdOrderUseCase;
    private final RabbitTemplate rabbitTemplate;
    private final CalculateTotalOrderUseCase calculateTotalOrderUseCase;

    public OrderOutput create(final OrderInput orderInput) {
        final var customerOutPut = createOrderUseCase.execute(orderInput);
        OrderOutput orderOutput = OrderInputOutputMapper.INSTANCE.orderToOrderResponse(customerOutPut.orElse(null));
        notificateCreatedOrder(orderOutput);
        return orderOutput;
    }

    private void notificateCreatedOrder(OrderOutput orderOutput) {
        if (orderOutput != null) {
            BigDecimal valorTotal = calculateTotalOrderUseCase.execute(orderOutput).orElseThrow(RuntimeException::new);
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE_NAME, RabbitMQConfig.ORDER_PAYMENT_KEY_NAME, new OrderRabbitOutput(orderOutput.id(), valorTotal));
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE_NAME, RabbitMQConfig.ORDER_KITCHEN_KEY_NAME, orderOutput.id());
        }
    }

    public OrderOutput update(final OrderInput orderInput) {
        final var customerOutPut = editOrderUseCase.execute(orderInput);
        return OrderInputOutputMapper.INSTANCE.orderToOrderResponse(customerOutPut.orElse(null));
    }

    public Page<OrderOutput> filter(final FilterInput filterInput) {
        final var page = filterOrderUseCase.execute(filterInput).orElse(Page.empty());
        final var content = page.getContent().stream()
                .map(OrderInputOutputMapper.INSTANCE::orderToOrderResponse)
                .toList();

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public void delete(final Long id) {
        deleteOrderUseCase.execute(id);
    }

    public OrderOutput get(final Long id) {
        return OrderInputOutputMapper.INSTANCE.orderToOrderResponse(getOrderById(id));
    }

    public Order getOrderById(final Long id) {
        return getByIdOrderUseCase.execute(id)
                .orElseThrow(NoResourceFoundException::new);
    }
}
