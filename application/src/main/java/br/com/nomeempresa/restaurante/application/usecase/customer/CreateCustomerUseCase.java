package br.com.nomeempresa.restaurante.application.usecase.customer;

import br.com.nomeempresa.restaurante.application.exception.CustomerAlreadyExistsException;
import br.com.nomeempresa.restaurante.application.inout.input.CustomerInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.CustomerInputOutputMapper;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Customer;
import br.com.nomeempresa.restaurante.domain.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase implements UseCase<CustomerInput, Customer> {

    private final CustomerGateway customerGateway;
    @Override
    public Optional<Customer> execute(final CustomerInput customerInput) {
        if(customerGateway.findByCpf(customerInput.cpf()).isPresent()) {
            throw new CustomerAlreadyExistsException("Usuário ja existe");
        }
        return customerGateway.save(CustomerInputOutputMapper.INSTANCE.customerRequestToCustomer(customerInput));
    }
}
