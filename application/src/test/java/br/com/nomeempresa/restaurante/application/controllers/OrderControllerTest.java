package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.facade.OrderFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.output.OrderOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderFacade orderFacade;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Page<OrderOutput> page = new PageImpl<>(List.of(new OrderOutput(1L, OrderStatus.IN_PREPARATION, LocalDateTime.now(), null)));
        when(orderFacade.filter(any(FilterInput.class))).thenReturn(page);

        ResponseEntity<Page<OrderOutput>> response = orderController.findAll(Map.of());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
        verify(orderFacade, times(1)).filter(any(FilterInput.class));
    }

    @Test
    void testCreate() {
        OrderInput input = new OrderInput(1L, null);
        OrderOutput output = new OrderOutput(1L, OrderStatus.IN_PREPARATION, LocalDateTime.now(), null);
        when(orderFacade.create(input)).thenReturn(output);

        ResponseEntity<OrderOutput> response = orderController.create(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(orderFacade, times(1)).create(input);
    }

    @Test
    void testDelete() {
        Long orderId = 1L;

        ResponseEntity<Order> response = orderController.delete(orderId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderFacade, times(1)).delete(orderId);
    }

    @Test
    void testGetById() {
        Long orderId = 1L;
        OrderOutput output = new OrderOutput(1L, OrderStatus.IN_PREPARATION, LocalDateTime.now(), any());
        when(orderFacade.get(orderId)).thenReturn(output);

        ResponseEntity<OrderOutput> response = orderController.getById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(orderFacade, times(1)).get(orderId);
    }

    @Test
    void testEdit() {
        Long orderId = 1L;
        OrderInput input = new OrderInput(orderId, null);
        OrderOutput output = new OrderOutput(1L, OrderStatus.IN_PREPARATION, LocalDateTime.now(), null);
        when(orderFacade.update(any(OrderInput.class))).thenReturn(output);

        ResponseEntity<OrderOutput> response = orderController.edit(orderId, input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(orderFacade, times(1)).update(input);
    }

    @Test
    void testUpdateStatus() {
        Long orderId = 1L;
        OrderOutput output = new OrderOutput(2L, OrderStatus.IN_PREPARATION, LocalDateTime.now(), any());
        when(orderFacade.updateStatusOrder(orderId)).thenReturn(output);

        ResponseEntity<OrderOutput> response = orderController.updateStatus(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
        verify(orderFacade, times(1)).updateStatusOrder(orderId);
    }
}
