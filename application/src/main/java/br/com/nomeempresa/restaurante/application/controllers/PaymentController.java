package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.facade.PaymentFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentUpdateInput;
import br.com.nomeempresa.restaurante.application.inout.output.CheckoutResponse;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentBalanceOutput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentOutput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping
    public ResponseEntity<PaymentOutput> generatePayment(@RequestBody @Valid PaymentInput request) {
        return ResponseEntity.ok(paymentFacade.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentBalanceOutput> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentFacade.get(id));
    }
    @GetMapping
    public ResponseEntity<Page<PaymentBalanceOutput>> getListPayments(@RequestParam(required = false) final Map<String, String> filter) {
        return ResponseEntity.ok(paymentFacade.filter(new FilterInput(filter)));
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody @Valid PaymentUpdateInput request) {
        return ResponseEntity.ok().body(paymentFacade.checkout(request));
    }

}
