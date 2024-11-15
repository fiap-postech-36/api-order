package br.com.order.domain.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Product implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Category category;

}
