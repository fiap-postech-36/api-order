package br.com.nomeempresa.restaurante.application.gateway;

import br.com.nomeempresa.restaurante.domain.core.domain.entities.QrCode;
import br.com.nomeempresa.restaurante.infra.feign.presenter.request.PaymentRequest;

import java.util.Optional;

public interface IntegrationLinkPaymentGateway {

    QrCode generatedQrCode(PaymentRequest payment);

}
