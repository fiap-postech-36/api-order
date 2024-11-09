package br.com.nomeempresa.restaurante.application.integration;

import br.com.nomeempresa.restaurante.application.exception.MercadoPagoIntegrationException;
import br.com.nomeempresa.restaurante.application.gateway.IntegrationLinkPaymentGateway;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.QrCode;
import br.com.nomeempresa.restaurante.infra.feign.client.MercadoPagoQrCodeClient;
import br.com.nomeempresa.restaurante.infra.feign.presenter.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneratedQrCode implements IntegrationLinkPaymentGateway {

    private final MercadoPagoQrCodeClient client;

    @Override
    public QrCode generatedQrCode(PaymentRequest request) {
        try {
            return client.generatedQrCode(request);
        } catch (Exception e) {
            throw new MercadoPagoIntegrationException("Integração com mercado pago falhou");
        }
    }
}
