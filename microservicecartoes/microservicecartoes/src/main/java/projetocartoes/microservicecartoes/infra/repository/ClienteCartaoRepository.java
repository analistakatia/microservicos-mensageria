package projetocartoes.microservicecartoes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetocartoes.microservicecartoes.domain.ClienteCartao;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository <ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
