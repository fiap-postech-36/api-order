package br.com.order.application.inout.input;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record FilterInput(@NotNull Map<String, String> params) {
}
