package br.com.order.domain.core.domain.entities;

import br.com.order.domain.core.domain.valueobjects.CPF;
import br.com.order.domain.core.domain.valueobjects.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Customer implements Serializable {

    private Long id;

    private String name;

    private CPF cpf;

    private Email email;

    public Boolean isIdentified() {
        return !this.cpf.getValue().isEmpty();
    }
}