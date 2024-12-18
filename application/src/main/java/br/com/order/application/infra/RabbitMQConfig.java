package br.com.order.application.infra;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE_NAME = "order_direct_exchange";
    public static final String ORDER_PAYMENT_QUEUE_NAME = "order_payment_direct_queue";
    public static final String ORDER_PAYMENT_KEY_NAME = "order_payment_direct_key";
    public static final String ORDER_KITCHEN_QUEUE_NAME = "order_kitchen_direct_queue";
    public static final String ORDER_KITCHEN_KEY_NAME = "order_kitchen_direct_key";
    public static final String KITCHEN_ORDER_QUEUE_NAME = "kitchen_order_direct_queue";

    @Bean
    public Queue queue() {
        return new Queue(ORDER_PAYMENT_QUEUE_NAME);
    }

    @Bean
    public Queue kitchenQueue() {
        return new Queue(ORDER_KITCHEN_QUEUE_NAME);
    }

    @Bean
    public Queue recivedOrderQueue() {
        return new Queue(KITCHEN_ORDER_QUEUE_NAME);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ORDER_PAYMENT_KEY_NAME);
    }

    @Bean
    public Binding kitchenBinding(Queue kitchenQueue, DirectExchange exchange) {
        return BindingBuilder.bind(kitchenQueue).to(exchange).with(ORDER_KITCHEN_KEY_NAME);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
