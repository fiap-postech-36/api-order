package br.com.order.application.usecase.order;

import br.com.order.application.exception.NoResourceFoundException;
import br.com.order.application.inout.output.OrderOutput;
import br.com.order.application.usecase.UseCase;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.Product;
import br.com.order.domain.gateway.OrderGateway;
import br.com.order.domain.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalculateTotalOrderUseCase implements UseCase<OrderOutput, BigDecimal> {

    private final ProductGateway productGateway;

    @Override
    public Optional<BigDecimal> execute(OrderOutput orderOutput) {
        List<Product> products = orderOutput.items().stream().map(item -> productGateway.findById(item.getId())).flatMap(Optional::stream).toList();
        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return Optional.of(total);
    }
}
