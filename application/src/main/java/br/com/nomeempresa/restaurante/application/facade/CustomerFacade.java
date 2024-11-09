package br.com.nomeempresa.restaurante.application.facade;

import br.com.nomeempresa.restaurante.application.exception.NoResourceFoundException;
import br.com.nomeempresa.restaurante.application.inout.input.CustomerInput;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.CustomerInputOutputMapper;
import br.com.nomeempresa.restaurante.application.inout.mapper.OrderInputOutputMapper;
import br.com.nomeempresa.restaurante.application.inout.output.CustomerOutput;
import br.com.nomeempresa.restaurante.application.usecase.customer.CreateCustomerUseCase;
import br.com.nomeempresa.restaurante.application.usecase.customer.DeleteCustomerUseCase;
import br.com.nomeempresa.restaurante.application.usecase.customer.EditCustomerUseCase;
import br.com.nomeempresa.restaurante.application.usecase.customer.FilterCustomerUseCase;
import br.com.nomeempresa.restaurante.application.usecase.customer.GetByIdCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFacade {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final EditCustomerUseCase editCustomerUseCase;
    private final FilterCustomerUseCase filterCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final GetByIdCustomerUseCase getByIdCustomerUseCase;

    public CustomerOutput create(final CustomerInput customerInput) {
        final var customerOutPut = createCustomerUseCase.execute(customerInput);
        return CustomerInputOutputMapper.INSTANCE.customerToCustomerResponse(customerOutPut.orElse(null));
    }

    public CustomerOutput update(final CustomerInput customerInput) {
        final var customerOutPut = editCustomerUseCase.execute(customerInput);
        return CustomerInputOutputMapper.INSTANCE.customerToCustomerResponse(customerOutPut.orElse(null));
    }

    public Page<CustomerOutput> filter(final FilterInput filterInput) {
        final var page = filterCustomerUseCase.execute(filterInput).orElse(Page.empty());
        final var content = page.getContent().stream()
            .map(CustomerInputOutputMapper.INSTANCE::customerToCustomerResponse)
            .toList();

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public void delete(final Long id) {
        deleteCustomerUseCase.execute(id);
    }

    public CustomerOutput get(final Long id) {
        return CustomerInputOutputMapper.INSTANCE.customerToCustomerResponse(getByIdCustomerUseCase.execute(id)
            .orElseThrow(NoResourceFoundException::new));
    }

}
