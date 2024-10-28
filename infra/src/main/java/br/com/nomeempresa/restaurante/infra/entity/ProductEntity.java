package br.com.nomeempresa.restaurante.infra.entity;

import br.com.nomeempresa.restaurante.infra.types.CategoryType;
import br.com.order.domain.core.domain.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Table(name = "product")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @Column( name = "name",nullable = false,unique=true,length=50)
    private String name;

    @Column( name = "description",nullable = false,length=500)
    private String description;

    @Column( name = "url_image",nullable = false,length=200)
    private String urlImage;

    @Column( name = "price",nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Type(CategoryType.class)
    @Column( name = "category",nullable = false)
    private Category category;

}
