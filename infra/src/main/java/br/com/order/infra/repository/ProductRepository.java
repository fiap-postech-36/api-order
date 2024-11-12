package br.com.order.infra.repository;

import br.com.order.infra.entity.ProductEntity;

import br.com.order.domain.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p from ProductEntity p where p.category = ?1")
    Collection<ProductEntity> findByCategory(final Category category);

    List<ProductEntity> findByIdIn(final List<Long> ids);

    @Query("select p from ProductEntity p where (:name IS NULL or p.name = :name) and (:category is null or cast(p.category as string) = :category)")
    Collection<ProductEntity> filter(final String name, final String category);
}
