package br.com.order.infra.gateways;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.ProductGateway;
import br.com.order.infra.mapper.ProductMapper;
import br.com.order.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository productRepository;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Override
    public Optional<Product> save(final Product product) {
        final var productEntity = mapper.productToProductEntity(product);
        return Optional.ofNullable(mapper.productEntityToProduct(productRepository.save(productEntity)));
    }

    @Override
    public Optional<Product> update(final Product product) {
        return save(product);
    }

    @Override
    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(final Long id) {
        return Optional.ofNullable(mapper.productEntityToProduct(productRepository.findById(id).orElse(null)));
    }

    @Override
    public Collection<Product> findByCategory(final Category category) {
        return productRepository.findByCategory(category).stream()
            .map(mapper::productEntityToProduct)
            .toList();
    }

    @Override
    public Collection<Product> filterByCategoryAndName(final String category, final String name) {
        return productRepository.filter(name, category).stream()
            .map(mapper::productEntityToProduct)
            .toList();
    }

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll().stream()
            .map(mapper::productEntityToProduct)
            .toList();
    }
}
