package br.com.order.domain.core.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    private Long id;
    private OrderStatus status;
    private LocalDateTime receivedAt;
    private LocalDateTime finishedAt;
    private List<Product> products;

    public void seInitialStatus() {
        this.status = OrderStatus.CREATED;
        this.receivedAt = LocalDateTime.now();
    }

    public void setReceived() {
        this.status = OrderStatus.RECEIVED;
        this.finishedAt = LocalDateTime.now();
    }

    public void addItem(final Product product) {
        if (Objects.nonNull(products)) {
            this.products.add(product);
        }
    }

    public void nextStepOrder() {
        this.status = this.status.getNext();
        this.receivedAt = LocalDateTime.now();
    }
    public BigDecimal calculateTotal() {
        return this.products.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}