package com.customer.api.dataprovider.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface WishlistProductDAO extends ReactiveCrudRepository<WishlistProductEntity, UUID> {

    Flux<WishlistProductEntity> findByCustomerId(UUID clientId);

    Mono<WishlistProductEntity> findByCustomerIdAndProductId(UUID clientId, String productId);

}
