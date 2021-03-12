package com.client.api.infrastructure.repository.mysql;

import com.client.api.domain.Client;
import com.client.api.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientDAO repository;

    @Override
    public Mono<Client> save(Client client) {
        return null;
    }

    @Override
    public Mono<Client> get(UUID id) {
        return null;
    }

    @Override
    public Mono<Boolean> existsByEmail(final String email) {
        return repository.findByEmail(email)
                .flatMap(clientEntity -> {
                    if (Objects.nonNull(clientEntity)) {
                        return Mono.just(Boolean.TRUE);
                    } else {
                        return Mono.just(Boolean.FALSE);
                    }
                });
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return null;
    }
}
