package br.com.order.application.inout.input;


import java.util.List;

public record OrderInput(Long id, List<Long> products) {
}
