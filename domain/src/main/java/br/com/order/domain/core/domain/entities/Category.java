package br.com.order.domain.core.domain.entities;

import lombok.Getter;

@Getter
public enum Category {
    LANCHE("LANCHE"),ACOMPANHAMENTO("ACOMPANHAMENTO"),BEBIDA("BEBIDA"),SOBREMESA("SOBREMESA");

    private final String code;

    Category(String category) {
        code = category;
    }

    public static Category fromCode(String category) {
        if (category == null) {
            return null;
        }
        return switch (category) {
            case "LANCHE" -> Category.LANCHE;
            case "ACOMPANHAMENTO" ->  Category.ACOMPANHAMENTO;
            case "BEBIDA" ->  Category.BEBIDA;
            case "SOBREMESA" ->  Category.SOBREMESA;
            default -> throw new IllegalArgumentException("Categoria inválida");
        };
    }
}
