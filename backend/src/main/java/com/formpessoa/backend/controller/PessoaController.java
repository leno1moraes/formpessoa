package com.formpessoa.backend.controller;

import com.formpessoa.backend.dto.PessoaDTO;
import com.formpessoa.backend.model.Pessoa;
import com.formpessoa.backend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaDTO> listar() {
        return pessoaService.listar();
    }

    @PostMapping
    public PessoaDTO salvar(@RequestBody PessoaDTO dto) {
        return pessoaService.salvar(dto);
    }

    @GetMapping("/{id}")
    public PessoaDTO buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody PessoaDTO dto){
        return pessoaService.atualizar(id, dto);
    }
}

