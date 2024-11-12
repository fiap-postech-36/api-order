package br.com.order.application.usecase;

import jakarta.validation.Valid;

import java.util.Optional;

/**
 I = INPUT
 O = OUTPUT
 **/
public interface UseCase<I, O> {

    Optional<O> execute(final @Valid I in) throws Exception;
}
