package com.formpessoa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O CPF não pode estar em branco")
    @NotNull
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;
}