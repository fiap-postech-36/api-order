package br.com.nomeempresa.restaurante.application.inout.output;

import br.com.nomeempresa.restaurante.domain.core.domain.entities.StatusPayment;

import java.math.BigDecimal;

public record PaymentOutput(

        Long id,

        BigDecimal amount,

        String client,

        String qrCode,

        StatusPayment status){}
