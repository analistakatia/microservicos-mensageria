package projetoclientes.microserviceclientes.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projetoclientes.microserviceclientes.application.representation.ClienteSaveRequest;
import projetoclientes.microserviceclientes.domain.Cliente;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public String status(){

        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest resquest){
        var cliente = resquest.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCpf(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

}
