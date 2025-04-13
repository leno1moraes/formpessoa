package com.formpessoa.backend.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PessoaMessageValidation.class)
    public ResponseEntity<Map<String, String>> handlePessoaMessageValidation(PessoaMessageValidation ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("msg-error", ex.getMessage());
        return ResponseEntity.badRequest().body(erro);
    }
}
