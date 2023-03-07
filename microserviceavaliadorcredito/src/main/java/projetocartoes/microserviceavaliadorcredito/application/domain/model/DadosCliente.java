package projetocartoes.microserviceavaliadorcredito.application.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;
}
