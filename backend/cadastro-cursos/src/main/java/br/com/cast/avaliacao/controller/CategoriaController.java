package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.dto.CategoriaDTO;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Api - Categorias")
/* Mapeando a url básica*/
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    /* Listar todas */
    @ApiOperation(value = "Listando todas as categorias cadastradas")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream()
                .map(obj -> new CategoriaDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    /* Buscar por id */
    @ApiOperation(value = "Buscando uma categoria por ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria objCategoria = service.find(id);
        return ResponseEntity.ok().body(objCategoria);
    }

    /* Insert */
    @ApiOperation(value = "Cadastrando uma nova categoria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody @Valid Categoria objCategoria){
        objCategoria  = service.insert(objCategoria);
        /*Pegando a URI criada*/
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /* Update */
    @ApiOperation(value = "Atualizando uma categoria")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> insert(@RequestBody @Valid Categoria objCategoria, @PathVariable Integer id){
        objCategoria.setId(id);
        objCategoria = service.update(objCategoria);
        return ResponseEntity.noContent().build();
    }


    /* Delete */
    @ApiOperation(value = "Excluindo categoria")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
