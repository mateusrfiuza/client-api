package com.client.api.domain.repository;

import com.client.api.domain.Client;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClientRepository {

    Mono<Client> save(Client client);

    Mono<Client> get(UUID id);

    Mono<Boolean> existsByEmail(String email);

    Mono<Void> delete(UUID id);





}
