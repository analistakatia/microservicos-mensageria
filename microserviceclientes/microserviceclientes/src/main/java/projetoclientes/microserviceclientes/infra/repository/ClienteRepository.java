package projetoclientes.microserviceclientes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoclientes.microserviceclientes.domain.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
