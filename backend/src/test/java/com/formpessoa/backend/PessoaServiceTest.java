package com.formpessoa.backend;

import com.formpessoa.backend.dto.PessoaDTO;
import com.formpessoa.backend.model.Pessoa;
import com.formpessoa.backend.repository.PessoaRepository;
import com.formpessoa.backend.service.PessoaService;
import com.formpessoa.backend.validation.PessoaMessageValidation;
import com.formpessoa.backend.validation.PessoaValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaService pessoaServiceTestComSucesso;

    @Mock
    private PessoaValidation pessoaValidation;

    @Test
    public void validarCamposComErro(){
        PessoaDTO dtoerros = new PessoaDTO(null,
                "",
                "000.000.000-00",
                "(00)00000-0000",
                "email@email.com");
        Mockito.when(pessoaValidation.validarCampos(dtoerros)).thenReturn(true);

        Assertions.assertThrows(PessoaMessageValidation.class, () -> {
            pessoaService.salvar(dtoerros);
        });
    }

    @Test
    public void validarCamposComSucesso(){
        PessoaDTO dtosucessantes = new PessoaDTO(null,
                "fulano de tal",
                "000.000.000-00",
                "(00)00000-0000",
                "email@email.com");

        PessoaDTO dtosucessdepois = dtosucessantes;
        Mockito.when(pessoaServiceTestComSucesso.salvar(dtosucessantes)).thenReturn(dtosucessdepois);
        Assertions.assertEquals(dtosucessdepois, dtosucessantes);
    }
}
