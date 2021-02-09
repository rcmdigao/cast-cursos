package br.com.cast.avaliacao.controller;


import br.com.cast.avaliacao.dto.CursosDTO;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Cursos;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.repository.CursoRepository;

import br.com.cast.avaliacao.service.CursoService;
import br.com.cast.avaliacao.util.BigDecimalConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@RestController
@Api(value = "Api - Cursos")
/* Mapeando a url básica*/
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursosController {

    @Autowired
    private CursoService service;

    @Autowired
    //private CursoService service;
    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;
    private final BigDecimalConvert bigDecimalConvert;

    /* Pesquisar cursos */
    @ApiOperation(value = "Listar ou Pesquisar cursos cadastrados")
    @GetMapping
    public List<Cursos> pesquisar(@RequestParam(value = "descricao", required = false, defaultValue = "") String descricao){
        return cursoRepository.findByNomeCurso("%"+ descricao + "%");
    }


    /* Cadastro */
    @ApiOperation(value = "Cadastrar novo curso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cursos salvar( @RequestBody @Valid CursosDTO dto ){


        Integer categoria = dto.getCategoria();
        LocalDate dataInicio = LocalDate.parse(dto.getDataInicio(), DateTimeFormatter.ofPattern ( "dd/MM/yyyy"));
        LocalDate dataTermino = LocalDate.parse(dto.getDataTermino(), DateTimeFormatter.ofPattern ( "dd/MM/yyyy"));

        /* Buscando a categoria*/
        Categoria catRepo = categoriaRepository
                .findById(categoria)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente."));


        // Validacao de periodo de data, Data final não pode ser menor do que a data Inicial
        long noOfDaysBetween = DAYS.between(dataInicio, dataTermino);
        if (noOfDaysBetween <= 0) System.out.println("Quantidade: " + noOfDaysBetween + "Não pode cadasttrar com data menores");


        Cursos curso = new Cursos();
        curso.setDescricao(dto.getDescricao());
        curso.setDataInicio(dataInicio);
        curso.setDataTermino(dataTermino);
        curso.setCategoria(catRepo);
        curso.setValor(bigDecimalConvert.converter(dto.getValor()));
        curso.setQtdAlunos(dto.getQtdAlunos());
        return service.insert(curso);

    }


    /* Delete */
    @ApiOperation(value = "Excluir cursos cadastrados")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
