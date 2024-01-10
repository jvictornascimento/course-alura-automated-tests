package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)

class TutorServiceTest {

    @InjectMocks
    TutorService service;

    @Mock
    TutorRepository repository;

    @Mock
    CadastroTutorDto dto;
    @Mock
    AtualizacaoTutorDto atualizacaoTutorDto;
    @Mock
    Tutor tutor;


    @Test
    void deveriaCadastrarUmTutor(){
        //arrange

        //act
        service.cadastrar(dto);

        //assert
        then(repository).should().save(new Tutor(dto));


    }

    @Test
    void deveriaAtualizarUmTutor(){
        //arrange
        given(repository.getReferenceById(atualizacaoTutorDto.id())).willReturn(tutor);
        //act
        service.atualizar(atualizacaoTutorDto);

        //assert
        then(tutor).should().atualizarDados(atualizacaoTutorDto);

    }

}