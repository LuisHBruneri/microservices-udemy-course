package io.github.luishbruneri.msclientes.application;

import io.github.luishbruneri.msclientes.domain.Cliente;
import io.github.luishbruneri.msclientes.infra.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public void save(Cliente cliente) {
        repository.save(cliente);
    }

    public Optional<Cliente> findByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}


