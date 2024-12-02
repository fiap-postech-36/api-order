package br.com.order.application.integration;

import br.com.order.application.infra.RabbitMQConfig;
import br.com.order.application.usecase.order.OrderRecivedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final OrderRecivedUseCase orderRecivedUseCase;

    @RabbitListener(queues = RabbitMQConfig.KITCHEN_ORDER_QUEUE_NAME)
    public void receiveMessage(String orderId) {
        int orderIdInt = Integer.parseInt(orderId);
        orderRecivedUseCase.execute(orderIdInt);
    }
}
