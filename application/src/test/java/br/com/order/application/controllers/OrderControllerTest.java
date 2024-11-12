package br.com.order.application.controllers;

import br.com.order.application.facade.OrderFacade;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.OrderInput;
import br.com.order.application.inout.output.OrderOutput;
import br.com.order.domain.core.domain.entities.Order;
import br.com.order.domain.core.domain.entities.OrderStatus;
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
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        Page<OrderOutput> orderOutputPage = new PageImpl<>(Collections.singletonList(new OrderOutput(1L, OrderStatus.CREATED, LocalDateTime.now(), Collections.emptyList())));
        when(orderFacade.filter(any(FilterInput.class))).thenReturn(orderOutputPage);

        ResponseEntity<Page<OrderOutput>> response = orderController.findAll(eq(Map.of("status", "PENDING")));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderOutputPage, response.getBody());
        verify(orderFacade, times(1)).filter(any(FilterInput.class));
    }

    @Test
    void testCreate() {
        OrderInput orderInput = new OrderInput(2L, Collections.singletonList(1L));
        OrderOutput orderOutput = new OrderOutput(1L, OrderStatus.CREATED, LocalDateTime.now(), Collections.emptyList());
        when(orderFacade.create(eq(orderInput))).thenReturn(orderOutput);

        ResponseEntity<OrderOutput> response = orderController.create(orderInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderOutput, response.getBody());
        verify(orderFacade, times(1)).create(eq(orderInput));
    }

    @Test
    void testDelete() {
        Long orderId = 1L;
        doNothing().when(orderFacade).delete(eq(orderId));

        ResponseEntity<Order> response = orderController.delete(orderId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderFacade, times(1)).delete(eq(orderId));
    }

    @Test
    void testGetById() {
        Long orderId = 1L;
        OrderOutput orderOutput = new OrderOutput(1L, OrderStatus.CREATED, LocalDateTime.now(), Collections.emptyList());
        when(orderFacade.get(eq(orderId))).thenReturn(orderOutput);

        ResponseEntity<OrderOutput> response = orderController.getById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderOutput, response.getBody());
        verify(orderFacade, times(1)).get(eq(orderId));
    }

    @Test
    void testEdit() {
        Long orderId = 1L;
        OrderInput orderInput = new OrderInput(3L, Collections.singletonList(1L));
        OrderOutput orderOutput = new OrderOutput(1L, OrderStatus.CREATED, LocalDateTime.now(), Collections.emptyList());
        when(orderFacade.update(any(OrderInput.class))).thenReturn(orderOutput);

        ResponseEntity<OrderOutput> response = orderController.edit(orderId, orderInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderOutput, response.getBody());
        verify(orderFacade, times(1)).update(any(OrderInput.class));
    }

    @Test
    void testUpdateStatus() {
        Long orderId = 1L;
        OrderOutput orderOutput = new OrderOutput(1L, OrderStatus.READY, LocalDateTime.now(), Collections.emptyList());
        when(orderFacade.updateStatusOrder(eq(orderId))).thenReturn(orderOutput);

        ResponseEntity<OrderOutput> response = orderController.updateStatus(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderOutput, response.getBody());
        verify(orderFacade, times(1)).updateStatusOrder(eq(orderId));
    }

}