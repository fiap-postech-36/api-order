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
    private LocalDateTime receivedAt;
    private LocalDateTime finishedAt;
    private List<Product> products;

    public void seInitialStatus() {
        this.receivedAt = LocalDateTime.now();
    }

    public void setReceived() {
        this.finishedAt = LocalDateTime.now();
    }
}