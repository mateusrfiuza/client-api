package com.customer.api.infrastructure.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CustomerDAO extends ReactiveCrudRepository<CustomerEntity, UUID> {

    Mono<CustomerEntity> findByEmail(String email);

}
