package com.client.api.domain.service;

import com.client.api.domain.Client;
import com.client.api.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Mono<Client> createClient(final Client client) {
        return repository.existsByEmail(client.getEmail())
            .flatMap(exists -> {
                if (!exists) {
                    return repository.save(client);
                } else {
                    return Mono.error(RuntimeException::new);
                }
        });
    }

}
