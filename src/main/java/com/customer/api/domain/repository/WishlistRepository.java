package com.customer.api.domain.repository;

import com.customer.api.domain.WishlistProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WishlistRepository {

    Mono<WishlistProduct> save(UUID clientId, String productId);

    Flux<WishlistProduct> findByCustomerId(UUID customerId);

    Mono<WishlistProduct> findByCustomerIdAndProductId(UUID customerId, String productId);

    Mono<Void> deleteById(UUID id);

}
