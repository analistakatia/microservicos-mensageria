package projetocartoes.microserviceavaliadorcredito.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetocartoes.microserviceavaliadorcredito.application.domain.model.*;
import projetocartoes.microserviceavaliadorcredito.application.exception.DadosClienteNotFoundException;
import projetocartoes.microserviceavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import projetocartoes.microserviceavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
@Slf4j
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        log.info("Testando");
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = null;
        try {
            situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            log.info("Retorna dados do Cliente");
            return ResponseEntity.ok(situacaoCliente);
        }catch (DadosClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ErroComunicacaoMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            log.info("Avaliação realizada com sucesso.");
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        }catch (ErroSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
