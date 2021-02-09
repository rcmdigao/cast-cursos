package br.com.cast.avaliacao.dto;

import br.com.cast.avaliacao.model.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/* Lombok */
@Data
/* Manter o constructor sem argumentos */
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private Integer id;

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;


    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        descricao = obj.getDescricao();
    }
}
