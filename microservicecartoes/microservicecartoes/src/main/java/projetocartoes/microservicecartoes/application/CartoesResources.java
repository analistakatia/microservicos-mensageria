package projetocartoes.microservicecartoes.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetocartoes.microservicecartoes.application.dto.CartaoSaveRequest;
import projetocartoes.microservicecartoes.application.dto.CartoesPorClienteResponse;
import projetocartoes.microservicecartoes.domain.Cartao;
import projetocartoes.microservicecartoes.domain.ClienteCartao;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
@Slf4j
public class CartoesResources {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    //Qdo não passar nenhum parametro vai cair nesse endpoint
    @GetMapping
    public String status(){
        log.info("Success Start");
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
        log.info("Registro realizado com sucesso");
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // /cartoes?renda=5000 Qdo passar algum paramentro vai car nesse endpoint
    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda){
        log.info("Consulta de renda realizada com sucesso");
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        log.info("Busca por cpf do cliente realizada com sucesso");
        List<ClienteCartao> listaClienteCartoes = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = listaClienteCartoes.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
