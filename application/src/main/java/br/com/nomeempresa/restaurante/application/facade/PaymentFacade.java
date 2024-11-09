package br.com.nomeempresa.restaurante.application.facade;

import br.com.nomeempresa.restaurante.application.exception.NoResourceFoundException;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentInput;
import br.com.nomeempresa.restaurante.application.inout.input.PaymentUpdateInput;
import br.com.nomeempresa.restaurante.application.inout.mapper.OrderInputOutputMapper;
import br.com.nomeempresa.restaurante.application.inout.mapper.PaymentInputOutputMapper;
import br.com.nomeempresa.restaurante.application.inout.output.CheckoutResponse;
import br.com.nomeempresa.restaurante.application.inout.output.OrderOutput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentBalanceOutput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentOutput;
import br.com.nomeempresa.restaurante.application.usecase.order.CreateOrderUseCase;
import br.com.nomeempresa.restaurante.application.usecase.order.DeleteOrderUseCase;
import br.com.nomeempresa.restaurante.application.usecase.order.EditOrderUseCase;
import br.com.nomeempresa.restaurante.application.usecase.order.FilterOrderUseCase;
import br.com.nomeempresa.restaurante.application.usecase.order.GetByIdOrderUseCase;
import br.com.nomeempresa.restaurante.application.usecase.payment.*;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Payment;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.QrCode;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.StatusPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final CheckoutPaymentUseCase checkoutPaymentUseCase;
    private final FilterPaymentUseCase filterPaymentUseCase;
    private final GetByIdPaymentUseCase getByIdPaymentUseCase;

    public PaymentOutput create(final PaymentInput paymentInput) {
        final var customerOutPut = createPaymentUseCase.execute(paymentInput);
        return PaymentInputOutputMapper.INSTANCE.paymentToPaymentResponse(customerOutPut.orElse(null));
    }

    public CheckoutResponse checkout(final PaymentUpdateInput checkoutInput) {
        return checkoutPaymentUseCase.execute(checkoutInput).orElse(null);
    }

    public Page<PaymentBalanceOutput> filter(final FilterInput filterInput) {
        final var page = filterPaymentUseCase.execute(filterInput).orElse(Page.empty());
        final var content = page.getContent().stream()
            .map(PaymentInputOutputMapper.INSTANCE::paymentToPaymentBalanceOutput)
            .toList();

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public PaymentBalanceOutput get(final Long id) {
        return PaymentInputOutputMapper.INSTANCE.paymentToPaymentBalanceOutput(getByIdPaymentUseCase.execute(id)
            .orElseThrow(NoResourceFoundException::new));
    }

}
