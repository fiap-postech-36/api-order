package br.com.nomeempresa.restaurante.application.usecase.customer;

import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Customer;
import br.com.nomeempresa.restaurante.domain.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterCustomerUseCase implements UseCase<FilterInput, Page<Customer>> {

    private final CustomerGateway customerGateway;
    @Override
    public Optional<Page<Customer>> execute(final FilterInput filterInput) {
        final List<Customer> customers = (List<Customer>) customerGateway.filter(filterInput.params().get("name"), filterInput.params().get("cpf"));
        return Optional.of(new PageImpl<>(customers));
    }
}
