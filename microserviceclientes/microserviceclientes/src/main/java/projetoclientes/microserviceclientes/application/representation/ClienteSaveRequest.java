package projetoclientes.microserviceclientes.application.representation;

import lombok.Data;
import projetoclientes.microserviceclientes.domain.Cliente;

@Data
public class ClienteSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
