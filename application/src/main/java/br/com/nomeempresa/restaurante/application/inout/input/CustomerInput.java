package br.com.nomeempresa.restaurante.application.inout.input;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerInput(
        Long id,
        String name,
        @CPF String cpf,
        @Email String email){
}