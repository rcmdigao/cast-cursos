package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CursoRepository extends JpaRepository<Cursos, Integer> {

    /* Buscando o curso por parametro*/
    @Query(" SELECT c FROM Cursos c WHERE c.descricao LIKE :descricao ")
    List<Cursos> findByNomeCurso(@Param("descricao") String descricao);
}
