package br.com.nomeempresa.restaurante.application.inout.output;

import br.com.nomeempresa.restaurante.domain.core.domain.entities.StatusPayment;

public record CheckoutResponse(
    Long idOrder,
    StatusPayment statusPayment
) {
}
