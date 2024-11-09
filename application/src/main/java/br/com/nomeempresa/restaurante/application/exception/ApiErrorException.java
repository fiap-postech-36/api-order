package br.com.nomeempresa.restaurante.application.exception;

public abstract class ApiErrorException extends RuntimeException {

    public abstract int getCode();
    public abstract String getMessage();

}
