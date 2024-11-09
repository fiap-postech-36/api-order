package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.facade.PaymentFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentUpdateInput;
import br.com.nomeempresa.restaurante.application.inout.output.CheckoutResponse;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentBalanceOutput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.StatusPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @Mock
    private PaymentFacade paymentFacade;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneratePayment() {
        PaymentInput paymentInput = new PaymentInput(1L);
        PaymentOutput paymentOutput = new PaymentOutput(2L, new BigDecimal("49.00"), "Client", "QrCode", StatusPayment.PENDING);

        when(paymentFacade.create(paymentInput)).thenReturn(paymentOutput);

        ResponseEntity<PaymentOutput> response = paymentController.generatePayment(paymentInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentOutput, response.getBody());
        verify(paymentFacade, times(1)).create(paymentInput);
    }

    @Test
    void testGetPayment() {
        Long paymentId = 1L;
        PaymentBalanceOutput paymentBalanceOutput = new PaymentBalanceOutput(4L, new BigDecimal("49.00"), "Client", StatusPayment.PENDING);
        when(paymentFacade.get(paymentId)).thenReturn(paymentBalanceOutput);

        ResponseEntity<PaymentBalanceOutput> response = paymentController.getPayment(paymentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentBalanceOutput, response.getBody());
        verify(paymentFacade, times(1)).get(paymentId);
    }

    @Test
    void testGetListPayments() {
        Page<PaymentBalanceOutput> page = new PageImpl<>(List.of(new PaymentBalanceOutput(5L, new BigDecimal("49.00"), "Client", StatusPayment.PENDING)));

        when(paymentFacade.filter(any(FilterInput.class))).thenReturn(page);

        ResponseEntity<Page<PaymentBalanceOutput>> response = paymentController.getListPayments(Map.of());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
        verify(paymentFacade, times(1)).filter(any(FilterInput.class));
    }

    @Test
    void testCheckout() {
        PaymentUpdateInput paymentUpdateInput = new PaymentUpdateInput(9L, 1L);
        CheckoutResponse checkoutResponse = new CheckoutResponse(9L, StatusPayment.PENDING);

        when(paymentFacade.checkout(paymentUpdateInput)).thenReturn(checkoutResponse);

        ResponseEntity<CheckoutResponse> response = paymentController.checkout(paymentUpdateInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(checkoutResponse, response.getBody());
        verify(paymentFacade, times(1)).checkout(paymentUpdateInput);
    }
}
