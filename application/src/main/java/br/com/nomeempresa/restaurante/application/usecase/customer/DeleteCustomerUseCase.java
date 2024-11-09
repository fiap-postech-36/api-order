package br.com.nomeempresa.restaurante.application.usecase.customer;

import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCase implements UseCase<Long, Void> {

    private final CustomerGateway customerGateway;
    @Override
    public Optional<Void> execute(final Long id) {
        customerGateway.delete(id);
        return Optional.empty();
    }
}
