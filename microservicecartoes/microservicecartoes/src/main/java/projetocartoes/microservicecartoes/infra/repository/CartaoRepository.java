package projetocartoes.microservicecartoes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetocartoes.microservicecartoes.domain.Cartao;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}

