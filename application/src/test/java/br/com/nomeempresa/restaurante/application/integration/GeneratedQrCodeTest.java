package br.com.nomeempresa.restaurante.application.integration;

import br.com.nomeempresa.restaurante.application.exception.MercadoPagoIntegrationException;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.PointOfInteraction;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.QrCode;
import br.com.nomeempresa.restaurante.infra.feign.client.MercadoPagoQrCodeClient;
import br.com.nomeempresa.restaurante.infra.feign.presenter.request.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeneratedQrCodeTest {

    @Mock
    private MercadoPagoQrCodeClient mercadoPagoQrCodeClient; // Mock do cliente MercadoPago

    @InjectMocks
    private GeneratedQrCode generatedQrCode; // A classe que será testada

    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks manualmente
        MockitoAnnotations.initMocks(this);

        // Inicializa o objeto PaymentRequest para os testes
        paymentRequest = new PaymentRequest();
        paymentRequest.setDescription("Teste");
        paymentRequest.setTransactionAmount(BigDecimal.valueOf(10.0));
        paymentRequest.setPaymentMethodId("pix");
    }

    @Test
    void testGeneratedQrCode_Success() {
        // Prepara o retorno esperado do mock
        QrCode expectedQrCode = new QrCode("status", new PointOfInteraction());
        when(mercadoPagoQrCodeClient.generatedQrCode(paymentRequest)).thenReturn(expectedQrCode);

        // Chama o método a ser testado
        QrCode result = generatedQrCode.generatedQrCode(paymentRequest);

        // Verifica o comportamento esperado
        assertNotNull(result);
        assertEquals(expectedQrCode, result);
        verify(mercadoPagoQrCodeClient, times(1)).generatedQrCode(paymentRequest); // Verifica se o método foi chamado uma vez
    }

    @Test
    void testGeneratedQrCode_Failure() {
        // Configura o mock para lançar uma exceção
        when(mercadoPagoQrCodeClient.generatedQrCode(paymentRequest)).thenThrow(new RuntimeException("Erro no MercadoPago"));

        // Verifica se a exceção é lançada corretamente
        MercadoPagoIntegrationException thrown = assertThrows(MercadoPagoIntegrationException.class, () -> {
            generatedQrCode.generatedQrCode(paymentRequest);
        });

        // Verifica se a exceção tem a mensagem correta
        assertEquals("Integração com mercado pago falhou", thrown.getMessage());
        verify(mercadoPagoQrCodeClient, times(1)).generatedQrCode(paymentRequest); // Verifica se o método foi chamado uma vez
    }
}
