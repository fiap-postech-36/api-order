package br.com.order.application.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
