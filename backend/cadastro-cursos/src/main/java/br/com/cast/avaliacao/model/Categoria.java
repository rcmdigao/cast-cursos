package br.com.cast.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* TODO Entidade Categoria*/
@Entity
/* Lombok */
@Data
/* Manter o constructor sem argumentos */
@NoArgsConstructor
/* Gerar o constructor com todas as propriedades */
@AllArgsConstructor
/* Builder de Categoria */
@Builder

public class Categoria implements Serializable{

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String descricao;
}
