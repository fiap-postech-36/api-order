package br.com.order.domain.gateway;

import br.com.order.domain.core.domain.Category;
import br.com.order.domain.core.domain.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductGateway {

    Optional<Product> save(final Product product);

    Optional<Product> update(final Product product);

    void delete(final Long id);

    Optional<Product> findById(final Long id);

    Collection<Product> findByCategory(final Category category);

    Collection<Product> filterByCategoryAndName(final String category, final String name);

    Collection<Product> findAll();
}
