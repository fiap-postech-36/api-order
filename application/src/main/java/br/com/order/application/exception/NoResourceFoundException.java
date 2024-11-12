package br.com.order.application.exception;

import org.springframework.http.HttpStatus;

public class NoResourceFoundException extends ApiErrorException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    private final String message;

    public NoResourceFoundException() {
        super();
        this.message = HTTP_STATUS.getReasonPhrase();
    }


    @Override
    public String getMessage() {
        return message;
    }
}
