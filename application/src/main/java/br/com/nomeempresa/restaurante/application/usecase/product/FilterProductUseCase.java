package br.com.nomeempresa.restaurante.application.usecase.product;

import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Category;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Product;
import br.com.nomeempresa.restaurante.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterProductUseCase implements UseCase<FilterInput, Page<Product>> {

    private final ProductGateway productGateway;

    @Override
    public Optional<Page<Product>> execute(final FilterInput filterParamOpt) {
        final var filters = filterParamOpt.params();
        final var name = Objects.nonNull(filters) ? filters.get("name") : null;
        final var category = Objects.nonNull(filters) ? filters.get("category") : null;
        final List<Product> productsFiltered = (List<Product>) productGateway.filterByCategoryAndName(category, name);

        return Optional.of(new PageImpl<>(productsFiltered));
    }
}
