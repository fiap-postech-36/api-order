package br.com.nomeempresa.restaurante.application.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super();
    }
    public ResourceNotFound(String message) {
        super(message);
    }
}
