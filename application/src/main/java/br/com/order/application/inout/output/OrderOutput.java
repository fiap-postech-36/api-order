package br.com.order.application.inout.output;

import br.com.order.domain.core.domain.entities.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderOutput(Long id, LocalDateTime finishedAt, List<Product> items) {
}
