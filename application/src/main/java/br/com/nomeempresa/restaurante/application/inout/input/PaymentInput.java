package br.com.nomeempresa.restaurante.application.inout.input;

import jakarta.validation.constraints.NotNull;


public record PaymentInput(

        @NotNull(message = "order must be provided")

        Long order
){}
