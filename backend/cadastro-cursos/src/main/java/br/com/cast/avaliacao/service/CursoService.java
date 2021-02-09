package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Cursos;
import br.com.cast.avaliacao.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repo;

    /* Listar */
    public List<Cursos> findAll(){
        return repo.findAll();
    }


    /* Buscar por Id */
    public Cursos find(Integer id){
        Optional<Cursos> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cursos não encontrados"));
    }

    /* Cadastro */
    public Cursos insert(Cursos obj){
        obj.setId(null);
        return repo.save(obj);
    }

    /* Update */
    public Cursos update(Cursos obj){
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