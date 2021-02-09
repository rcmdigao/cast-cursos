package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    /* Listar */
    public List<Categoria> findAll(){
        return repo.findAll();
    }

    /* Buscar por Id */
    public Categoria find(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    /* Cadastro */
    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    /* Update */
    public Categoria update(Categoria obj){
        /* Pesquisando antes para ver se o id é válido */
        find(obj.getId());
        return repo.save(obj);
    }

    /* Delete */
    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            ex.printStackTrace();
        }
    }
}
