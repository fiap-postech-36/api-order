package br.com.order.application.inout.output;

import java.math.BigDecimal;

public record OrderRabbitOutput(Long id, BigDecimal price) {
}
