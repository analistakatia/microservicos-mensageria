package projetocartoes.microservicecartoes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetocartoes.microservicecartoes.domain.ClienteCartao;
import projetocartoes.microservicecartoes.infra.repository.ClienteCartaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository cartaoRepository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return cartaoRepository.findByCpf(cpf);
    }

}
