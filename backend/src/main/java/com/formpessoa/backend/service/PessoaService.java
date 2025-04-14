package com.formpessoa.backend.service;

import com.formpessoa.backend.dto.PessoaDTO;
import com.formpessoa.backend.model.Pessoa;
import com.formpessoa.backend.repository.PessoaRepository;
import com.formpessoa.backend.validation.PessoaMessageValidation;
import com.formpessoa.backend.validation.PessoaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaValidation pessoaValidation;

    public List<PessoaDTO> listar() {
        List<Pessoa> pessoas = pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<PessoaDTO> dtos = new ArrayList<>();
        if (pessoas.size() > 0){
            for (Pessoa pessoa : pessoas){
                dtos.add(new PessoaDTO(pessoa.getId(),
                                        pessoa.getNome(),
                                        pessoa.getCpf(),
                                        pessoa.getTelefone(),
                                        pessoa.getEmail()));

            }
        }
        return dtos;
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
        PessoaDTO d = new PessoaDTO(null,
                                    p.getNome(),
                                    p.getCpf(),
                                    p.getTelefone(),
                                    p.getEmail());
        return d;
    }

    public PessoaDTO buscarPorId(Long id) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaMessageValidation("Pessoa com ID " + id + " não encontrada."));
        PessoaDTO dto = new PessoaDTO(pessoaExistente.getId(),
                                        pessoaExistente.getNome(),
                                        pessoaExistente.getCpf(),
                                        pessoaExistente.getEmail(),
                                        pessoaExistente.getEmail());
        return dto;
    }

    public void excluir(Long id) {
        buscarPorId(id);
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, PessoaDTO dto) {
        PessoaDTO dtoExiste = buscarPorId(id);

        if (pessoaValidation.validarCampos(dto))
            throw new PessoaMessageValidation("Todos os campos são obrigatórios");

        Pessoa pessoa = new Pessoa();
        pessoa.setId(dtoExiste.id());
        pessoa.setCpf(dtoExiste.cpf());
        pessoa.setEmail(dtoExiste.email());
        pessoa.setNome(dtoExiste.nome());
        pessoa.setTelefone(dtoExiste.telefone());
        return pessoaRepository.save(pessoa);
    }

}

