package br.com.nomeempresa.restaurante.application.usecase.payment;

import br.com.nomeempresa.restaurante.application.inout.input.PaymentUpdateInput;
import br.com.nomeempresa.restaurante.application.inout.output.CheckoutResponse;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Payment;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
import br.com.nomeempresa.restaurante.domain.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckoutPaymentUseCase implements UseCase<PaymentUpdateInput, CheckoutResponse> {

    private final PaymentGateway paymentGateway;

    private final OrderGateway orderGateway;

    @Override
    public Optional<CheckoutResponse> execute(final PaymentUpdateInput paymentCheckoutInput) {
        Payment payment = paymentGateway.findById(paymentCheckoutInput.idPayment())
            .orElseThrow(RuntimeException::new);
        payment.setStatusPaid();
        paymentGateway.save(payment);

        Order order = orderGateway.findById(paymentCheckoutInput.order())
            .orElseThrow(RuntimeException::new);
        order.setReceived();
        orderGateway.save(order);

        return Optional.of(new CheckoutResponse(order.getId(), payment.getStatus()));
    }
}
