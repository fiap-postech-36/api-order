package br.com.order.domain.core.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void testFromCodeValidCategory() {
        // Testa a conversão de códigos válidos para categorias
        assertEquals(Category.LANCHE, Category.fromCode("LANCHE"));
        assertEquals(Category.ACOMPANHAMENTO, Category.fromCode("ACOMPANHAMENTO"));
        assertEquals(Category.BEBIDA, Category.fromCode("BEBIDA"));
        assertEquals(Category.SOBREMESA, Category.fromCode("SOBREMESA"));
    }

    @Test
    void testFromCodeNullCategory() {
        // Testa a conversão com valor nulo
        assertNull(null);
    }

    @Test
    void testFromCodeInvalidCategory() {
        // Testa a conversão com uma categoria inválida, que deve lançar uma exceção
        assertThrows(IllegalArgumentException.class, () -> Category.fromCode("INVALIDA"));
    }
}

