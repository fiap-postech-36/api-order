package br.com.order.domain.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Product implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Category category;

    public Product() {
    }

}
