package br.com.order.domain.core.exception;

import java.io.Serializable;

public class CoreExceptionRuntime extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public CoreExceptionRuntime(String msg)   {
        super(msg);
    }
}