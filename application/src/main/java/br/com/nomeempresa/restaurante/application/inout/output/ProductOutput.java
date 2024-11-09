package br.com.nomeempresa.restaurante.application.inout.output;


import br.com.nomeempresa.restaurante.domain.core.domain.entities.Category;

import java.math.BigDecimal;

public record ProductOutput(
        Long id,
        String name,
        String description,
        String urlImage,
        BigDecimal price,
        Category category){

}
