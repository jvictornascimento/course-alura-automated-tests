package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class AbrigoServiceTest {

    @InjectMocks
    AbrigoService service;
    @Mock
     AbrigoRepository abrigoRepository;

    @Mock
     PetRepository petRepository;
    @Mock
    Abrigo abrigo;
    @Mock
    CadastroAbrigoDto dto;

    @Test
    void deveriaCadastrarUmAbrigo(){
        // arrage
        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.telefone()))
                .willReturn(false);

        //act
        service.cadatrar(dto);

        //assert
        then(abrigoRepository).should().save(new Abrigo(dto));


    }
    @Test
    void deveriaNegarUmNovoCadastroDeUmAbrigo(){
//

        //assert + act
        assertThrows(ValidacaoException.class, () ->
        { when(abrigoRepository.existsByNomeOrTelefoneOrEmail
                (dto.nome(), dto.telefone(), dto.telefone()))
                .thenReturn(true);

        service.cadatrar(dto);
        });
    }
    @Test
    void deveriChamarListarDeTodosOsPetsDoAbrigo(){

        service.listar();

        then(abrigoRepository).should().findAll();
    }

   @Test
    void deveriaChmarListadePetsDoAbrigoAtravesDoNome(){
        //Arrange
        String nome = "Miau";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));
       //Act
       service.listarPetsDoAbrigo(nome);
       //Assert
        then(petRepository).should().findByAbrigo(abrigo);
   }



}