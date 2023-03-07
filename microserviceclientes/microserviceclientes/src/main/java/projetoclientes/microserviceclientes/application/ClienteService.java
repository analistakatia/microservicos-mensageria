package projetoclientes.microserviceclientes.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetoclientes.microserviceclientes.domain.Cliente;
import projetoclientes.microserviceclientes.infra.repository.ClienteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente>getByCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
}
