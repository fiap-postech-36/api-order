package br.com.nomeempresa.restaurante.application.inout.output;

import br.com.nomeempresa.restaurante.domain.core.domain.entities.OrderStatus;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderOutput(
    Long id,
    OrderStatus status,
    LocalDateTime finishedAt,
    List<Product> items) {
}
