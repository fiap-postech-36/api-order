package br.com.nomeempresa.restaurante.application.usecase.payment;

import br.com.nomeempresa.restaurante.application.exception.ResourceNotFound;
import br.com.nomeempresa.restaurante.application.gateway.IntegrationLinkPaymentGateway;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.PaymentInputOutputMapper;
import br.com.nomeempresa.restaurante.application.usecase.UseCase;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Payment;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.QrCode;
import br.com.nomeempresa.restaurante.domain.gateway.OrderGateway;
import br.com.nomeempresa.restaurante.domain.gateway.PaymentGateway;
import br.com.nomeempresa.restaurante.infra.feign.presenter.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatePaymentUseCase implements UseCase<PaymentInput, Payment> {

    private final IntegrationLinkPaymentGateway integrationLinkPaymentGateway;

    private final PaymentGateway paymentGateway;

    private final OrderGateway orderGateway;

    @Override
    public Optional<Payment> execute(final PaymentInput paymentInput) {
        Payment payment = PaymentInputOutputMapper.INSTANCE.paymentRequestToPayment(paymentInput);

        Order order = orderGateway.findById(paymentInput.order()).orElseThrow(ResourceNotFound::new);

        payment.setAmount(order.calculateTotal());

        payment.setStatusPending();
        Optional<Payment> paymentGenerated = paymentGateway.save(payment);

        paymentGenerated.ifPresent(pay -> {
            QrCode qrCode = integrationLinkPaymentGateway.generatedQrCode(
                PaymentRequest.builder()
                    .description("order payment")
                    .paymentMethodId("pix")
                    .transactionAmount(payment.getAmount())
                    .build());

            paymentGenerated.get().setQrCode(qrCode.getQrCode());

        });

        return paymentGenerated;
    }
}
