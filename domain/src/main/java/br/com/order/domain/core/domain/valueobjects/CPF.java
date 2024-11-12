package br.com.order.domain.core.domain.valueobjects;

import java.io.Serializable;

public class CPF implements Serializable {
    private final String cpf;

    public CPF(String cpf){
        this.cpf = cpf;
    }

    public String getValue() {
        return cpf;
    }
}