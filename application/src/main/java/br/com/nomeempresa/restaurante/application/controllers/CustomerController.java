package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.exception.CustomerAlreadyExistsException;
import br.com.nomeempresa.restaurante.application.facade.CustomerFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.CustomerInputOutputMapper;
import br.com.nomeempresa.restaurante.application.inout.input.CustomerInput;
import br.com.nomeempresa.restaurante.application.inout.output.CustomerOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Customer;
import br.com.nomeempresa.restaurante.domain.gateway.CustomerGateway;
import br.com.nomeempresa.restaurante.application.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<CustomerOutput> saveCustomer(@RequestBody @Valid CustomerInput customerInput, UriComponentsBuilder uriBuilder) {
        var customer = customerFacade.create(customerInput);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.id()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @PutMapping
    public ResponseEntity<CustomerOutput> updateCustomer(@RequestBody @Valid CustomerInput customerInput) {
        return ResponseEntity.ok(customerFacade.update(customerInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id", required = false) Long id) {
        customerFacade.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CustomerOutput>> findAllCustomers(@RequestParam(required = false) final Map<String, String> filter) {
        return ResponseEntity.ok(customerFacade.filter(new FilterInput(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOutput> findCustomerById(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(customerFacade.get(id));
    }

}