package com.customer.api.infrastructure.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface WishlistProductDAO extends ReactiveCrudRepository<WishlistProductEntity, UUID> {

    Flux<WishlistProductEntity> findByCustomerId(UUID clientId);

}
