package com.formpessoa.backend.service;

import com.formpessoa.backend.dto.PessoaDTO;
import com.formpessoa.backend.model.Pessoa;
import com.formpessoa.backend.repository.PessoaRepository;
import com.formpessoa.backend.validation.PessoaMessageValidation;
import com.formpessoa.backend.validation.PessoaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaValidation pessoaValidation;

    public List<Pessoa> listar() {
        return pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public PessoaDTO salvar(PessoaDTO dto) {
        if (pessoaValidation.validarCampos(dto))
            throw new PessoaMessageValidation("Todos os campos são obrigatórios");

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.cpf());
        pessoa.setEmail(dto.email());
        pessoa.setNome(dto.nome());
        pessoa.setTelefone(dto.telefone());

        Pessoa p = pessoaRepository.save(pessoa);
        PessoaDTO d = new PessoaDTO(p.getNome(),
                                    p.getCpf(),
                                    p.getTelefone(),
                                    p.getEmail());
        return d;
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaMessageValidation("Pessoa com ID " + id + " não encontrada."));
        return Optional.ofNullable(pessoaExistente);
    }

    public void excluir(Long id) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaMessageValidation("Pessoa com ID " + id + " não encontrada."));
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, PessoaDTO dto) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaMessageValidation("Pessoa com ID " + id + " não encontrada."));

        if (pessoaValidation.validarCampos(dto))
            throw new PessoaMessageValidation("Todos os campos são obrigatórios");

        pessoaExistente.setCpf(dto.cpf());
        pessoaExistente.setEmail(dto.email());
        pessoaExistente.setNome(dto.nome());
        pessoaExistente.setTelefone(dto.telefone());
        return pessoaRepository.save(pessoaExistente);
    }

}

