package br.com.order.application.exception;

public abstract class ApiErrorException extends RuntimeException {

    public abstract String getMessage();

}
