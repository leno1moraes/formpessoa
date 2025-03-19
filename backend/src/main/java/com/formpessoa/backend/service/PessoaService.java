package com.formpessoa.backend.service;

import com.formpessoa.backend.model.Pessoa;
import com.formpessoa.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void excluir(Long id) {
        pessoaRepository.deleteById(id);
    }
}

