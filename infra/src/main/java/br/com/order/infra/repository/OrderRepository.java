package br.com.order.infra.repository;


import br.com.order.infra.entity.OrderEntity;
import br.com.order.domain.core.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where (:orderStatus is null or cast (o.status as string) = :orderStatus)")
    List<OrderEntity> findByStatus(final String orderStatus);

    @Query("select o from OrderEntity o where o.status in :status order by receivedAt asc")
    List<OrderEntity> getByPriority(final List<OrderStatus> status);
}
