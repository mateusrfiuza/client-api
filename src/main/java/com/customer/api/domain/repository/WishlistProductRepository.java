package com.customer.api.domain.repository;

import com.customer.api.domain.WishlistProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WishlistProductRepository {

    Mono<WishlistProduct> save(UUID clientId, String productId);

    Flux<WishlistProduct> findByCustomerId(UUID id);

    Mono<Void> deleteById(UUID id);

}
