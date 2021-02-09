package br.com.cast.avaliacao.dto;

import br.com.cast.avaliacao.model.Cursos;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/* Lombok */
@Data
/* Manter o constructor sem argumentos */
@NoArgsConstructor
public class CursosDTO  {

    //private static final long serialVersionUID = 1l
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.dataInicio.obrigatorio}")
    private String dataInicio;

    @NotEmpty(message = "{campo.dataTermino.obrigatorio}")
    private String dataTermino;

    @NotNull(message = "{campo.valor.obrigatorio}")
    private String valor;

    @NotNull(message = "{campo.qtdAlunos.obrigatorio}")
    private Integer qtdAlunos;

    @NotNull(message = "{campo.categoria.obrigatorio}")
    private Integer categoria;

    public CursosDTO(Cursos obj) {
    descricao = obj.getDescricao();
    }


}
