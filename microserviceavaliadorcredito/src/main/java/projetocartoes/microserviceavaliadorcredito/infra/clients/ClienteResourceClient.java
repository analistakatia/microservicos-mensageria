package projetocartoes.microserviceavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projetocartoes.microserviceavaliadorcredito.application.domain.model.DadosCliente;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);
}
