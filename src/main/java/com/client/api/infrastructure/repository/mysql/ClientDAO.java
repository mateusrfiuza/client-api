package com.client.api.infrastructure.repository.mysql;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ClientDAO extends ReactiveCrudRepository<ClientEntity, UUID> {


    Mono<ClientEntity> findByEmail(String email);

}
