package br.com.order.application.inout.output;

import br.com.order.domain.core.domain.OrderStatus;
import br.com.order.domain.core.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderOutput(Long id, OrderStatus status, LocalDateTime finishedAt, List<Product> items) {
}
