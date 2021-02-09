package br.com.cast.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


/* TODO Entidade Cursos*/
@Entity
/* Lombok */
@Data
/* Manter o constructor sem argumentos */
@NoArgsConstructor
/* Gerar o constructor com todas as propriedades */
@AllArgsConstructor
/* Builder */
@Builder
public class Cursos implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataInicio;

    @Column(name = "data_termino", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataTermino;

    @Column(name="qtd_alunos", nullable = false, length = 100)
    private Integer qtdAlunos;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

}
