package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.exception.CustomerNotFoundException;
import br.com.nomeempresa.restaurante.application.facade.CustomerFacade;
import br.com.nomeempresa.restaurante.application.inout.input.CustomerInput;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.output.CustomerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerFacade customerFacade;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        CustomerInput input = new CustomerInput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com");
        CustomerOutput output = new CustomerOutput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com");
        when(customerFacade.create(any(CustomerInput.class))).thenReturn(output);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<CustomerOutput> response = customerController.saveCustomer(input, uriBuilder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(output, response.getBody());
        assertNotNull(response.getHeaders().getLocation());
        verify(customerFacade, times(1)).create(input);
    }

    @Test
    void testUpdateCustomer() {
        CustomerInput input = new CustomerInput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com");
        CustomerOutput output = new CustomerOutput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com");
        when(customerFacade.update(any(CustomerInput.class))).thenReturn(output);

        ResponseEntity<CustomerOutput> response = customerController.updateCustomer(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(customerFacade, times(1)).update(input);
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;

        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerFacade, times(1)).delete(customerId);
    }

    @Test
    void testFindAllCustomers() {
        Page<CustomerOutput> page = new PageImpl<>(List.of(new CustomerOutput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com")));
        when(customerFacade.filter(any(FilterInput.class))).thenReturn(page);

        ResponseEntity<Page<CustomerOutput>> response = customerController.findAllCustomers(Map.of());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
        verify(customerFacade, times(1)).filter(any(FilterInput.class));
    }

    @Test
    void testFindCustomerById() {
        Long customerId = 1L;
        CustomerOutput output = new CustomerOutput(1L, "Nome do Cliente", "123.456.789-00", "0X2tD@example.com");
        when(customerFacade.get(customerId)).thenReturn(output);

        ResponseEntity<CustomerOutput> response = customerController.findCustomerById(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(customerFacade, times(1)).get(customerId);
    }

    @Test
    void testFindCustomerById_NotFound() {
        Long customerId = 1L;
        when(customerFacade.get(customerId)).thenThrow(new CustomerNotFoundException("Customer not found"));

        try {
            customerController.findCustomerById(customerId);
        } catch (CustomerNotFoundException ex) {
            assertEquals("Customer not found", ex.getMessage());
        }
        verify(customerFacade, times(1)).get(customerId);
    }
}
