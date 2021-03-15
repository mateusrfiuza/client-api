package com.customer.api.infrastructure.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerDAO extends ReactiveCrudRepository<CustomerEntity, UUID> {

    Mono<CustomerEntity> findByEmail(String email);

}
