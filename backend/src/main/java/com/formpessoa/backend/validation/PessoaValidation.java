package com.formpessoa.backend.validation;

import com.formpessoa.backend.dto.PessoaDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class PessoaValidation {

    public Boolean validarCampos(PessoaDTO dto){
        return Stream.of(dto.nome(), dto.cpf(), dto.email(), dto.telefone())
                .anyMatch(s -> s == null || s.isBlank() || s.isEmpty());
    }
}
